package org.mozilla.taskcluster.client;

import java.net.URI;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.google.gson.Gson;
import com.wealdtech.hawk.HawkClient;
import com.wealdtech.hawk.HawkCredentials;
import com.wealdtech.hawk.HawkCredentials.Algorithm;

import net.iharder.Base64;

public class Credentials {
    private transient final static String algo             = "HmacSHA256";
    public String[]                       authorizedScopes = null;
    public String                         accessToken      = "";
    public String                         clientId         = null;
    public String                         certificate      = null;
    private transient HawkClient          hawkClient;

    public void configureHawk() {
        HawkCredentials hawkCredentials = new HawkCredentials.Builder().keyId(clientId).key(accessToken)
                .algorithm(Algorithm.SHA256).build();
        hawkClient = new HawkClient.Builder().credentials(hawkCredentials).build();
    }

    // Hide default constructor
    @SuppressWarnings("unused")
    private Credentials() {
    }

    public Credentials(String clientId, String accessToken) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.certificate = null;
        configureHawk();
    }

    public Credentials(String clientId, String accessToken, String certificate) {
        this.clientId = clientId;
        this.accessToken = accessToken;
        this.certificate = certificate;
        configureHawk();
    }

    /**
     * This method is used to generate unnamed temporary credentials.
     *
     * Note that the auth service already applies a 5 minute clock skew to the
     * start and expiry times in
     * https://github.com/taskcluster/taskcluster-auth/pull/117 so no clock
     * skew is applied in this method, nor should be applied by the caller.
     */
    public Credentials createTemporaryCredentials(String[] scopes, Date start, Date expiry)
            throws InvalidOptionsException {
        return this.createTemporaryCredentials("", scopes, start, expiry);
    }

    /**
     * This method is used to generate named temporary credentials.
     *
     * Note that the auth service already applies a 5 minute clock skew to the
     * start and expiry times in
     * https://github.com/taskcluster/taskcluster-auth/pull/117 so no clock
     * skew is applied in this method, nor should be applied by the caller.
     */
    public Credentials createTemporaryCredentials(String clientId, String[] scopes, Date start, Date expiry)
            throws InvalidOptionsException {
        // Check dates
        if (start.after(expiry)) {
            // throw error
            throw new InvalidOptionsException(new Throwable("start should be before expiry"));
        }
        if ((long) (expiry.getTime() - start.getTime()) > (long) 31 * 24 * 60 * 60 * 1000) {
            // throw error
            throw new InvalidOptionsException(new Throwable("Cannot exceed 31 days"));
        }
        Certificate cert = new Certificate(1, start, expiry, scopes);
        if (clientId != null && !clientId.equals("")) {
            cert.issuer = this.clientId;
        }
        // Create signature;
        cert.generateSignature(accessToken, clientId);
        String temporaryAccessToken = generateTemporaryAccessToken(accessToken, cert.seed);
        if (clientId != null && !clientId.equals("")) {
            return new Credentials(clientId, temporaryAccessToken, cert.toString());
        }
        return new Credentials(this.clientId, temporaryAccessToken, cert.toString());
    }

    public static String generateTemporaryAccessToken(String accessToken, String seed) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(accessToken.getBytes("utf-8"), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(keySpec);

            return Base64.encodeBytes(mac.doFinal(seed.getBytes("utf-8"))).replace('+', '-').replace('/', '_')
                    .replace("=", "");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * GetExtAuthField generates the hawk ext header based on the authorizedScopes
     * and the certificate used in the case of temporary credentials. The header is
     * a base64 encoded json object with a "certificate" property set to the
     * certificate of the temporary credentials and a "authorizedScopes" property
     * set to the array of authorizedScopes, if provided. If either "certificate" or
     * "authorizedScopes" is not supplied, they will be omitted from the json
     * result. If neither are provided, an empty string is returned, rather than a
     * base64 encoded representation of "null" or "{}". Hawk interprets the empty
     * string as meaning the ext header is not needed.
     *
     * See:
     *   * https://docs.taskcluster.net/manual/apis/authorized-scopes
     *   * https://docs.taskcluster.net/manual/apis/temporary-credentials
     */
    private String GetExtAuthField() {
        ExtAuthField extAuthField = new ExtAuthField(getCertificate(), authorizedScopes);
        Gson gson = new Gson();
        String ext = gson.toJson(extAuthField);
        if (ext.equals("{}")) {
            return "";
        }
        return Base64.encodeBytes(ext.getBytes());
    }

    public String generateAuthorizationHeader(URI uri, String method, String hash) {
        return hawkClient.generateAuthorizationHeader(uri, method, hash, GetExtAuthField(), null, null);
    }

    public Certificate getCertificate() {
        Gson gson = new Gson();
        return gson.fromJson(certificate, Certificate.class);
    }

    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
