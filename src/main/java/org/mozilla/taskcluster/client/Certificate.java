package org.mozilla.taskcluster.client;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.iharder.Base64;

/**
 * Taskcluster certificates are used in temporary credentials when
 * authenticating against Taskcluster HTTP(S) end points.
 */

public class Certificate {

    public int                     version   = 1;
    public String[]                scopes;
    public long                    start;
    public long                    expiry;
    public String                  seed;
    public String                  signature = null;
    private final transient String algo      = "HmacSHA256";
    // For named credentials
    public String                  issuer    = null;

    public Certificate(int version, Date start, Date expiry, String scopes[]) {
        this.version = version;
        this.start = start.getTime();
        this.expiry = expiry.getTime();
        this.scopes = scopes;
        signature = null;
        generateSeed();
    }

    private void generateSeed() {
        seed = slug() + slug();
    }

    private static String slug() {
        // The UUID is generated using a cryptographically strong pseudo random number
        // generator
        UUID uuid = UUID.randomUUID();
        long hi = uuid.getMostSignificantBits();
        long lo = uuid.getLeastSignificantBits();
        ByteBuffer raw = ByteBuffer.allocate(16);
        raw.putLong(hi);
        raw.putLong(lo);
        byte[] rawBytes = raw.array();
        return Base64.encodeBytes(rawBytes).replace('+', '-').replace('/', '_').substring(0, 22);
    }

    public void generateSignature(String accessToken, String tempClientId) throws InvalidOptionsException {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(accessToken.getBytes("utf-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(keySpec);

            StringBuilder builder = new StringBuilder();

            // Accumulator like stuff
            builder.append("version:" + Integer.toString(version) + "\n");
            // for named credentials
            if (tempClientId != null && issuer != null && !tempClientId.equals("") && !issuer.equals("")) {
                builder.append("clientId:" + tempClientId + "\n");
                builder.append("issuer:" + issuer + "\n");
            }
            builder.append("seed:" + seed + "\n");
            builder.append("start:" + Long.toString(start) + "\n");
            builder.append("expiry:" + Long.toString(expiry) + "\n");
            builder.append("scopes:");
            for (String scope : scopes) {
                builder.append("\n" + scope);
            }
            String temp = builder.toString();

            // Set the signature as base64 encoded string
            this.signature = Base64.encodeBytes(mac.doFinal(temp.getBytes("utf-8")));

        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidOptionsException(e);
        }
    }

    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }
}
