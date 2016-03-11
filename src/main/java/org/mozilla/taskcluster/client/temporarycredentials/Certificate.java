package org.mozilla.taskcluster.client;

import java.nio.ByteBuffer;

import java.util.Date;
import java.util.UUID;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import net.iharder.Base64;

import com.google.gson.Gson;

/*
  This class handles creation of cretificates and generation of signatures.
  Output is provided as JSON string.

  constructors:
    Default
    Certificate(int version, Date start, Date expiry, String scopes[])
    Certificate(int version, Date start, Date expiry, String scopes[], String clientId, String issuer)

  public methods:
  generateSignature(String)
    This generates the signature based on the access token
*/

public class Certificate {

  public int version = 1;
  public String[] scopes;
  public transient Date st;
  public transient Date exp;
  public long start;
  public long expiry;
  public String seed;
  public String signature = null;
  private final transient String algo = "HmacSHA256";
  //For named credentials
  public String issuer = null;
  public String clientId = null;

  //constructors
  public Certificate(){
    signature = null;
    generateSeed();
  }

  public Certificate(int version, Date start, Date expiry, String scopes[]){
    this.version = version;
    this.st = start;
    this.exp = expiry;
    this.scopes = scopes;
    issuer = null;
    signature = null;
    generateSeed();
  }

  public Certificate(int version, Date start, Date expiry,
  String scopes[], String clientId, String issuer){
    this.version = version;
    this.st = start;
    this.exp = expiry;
    this.scopes = scopes;
    this.issuer = issuer;
    this.clientId = clientId;
    signature = null;
    generateSeed();
  }

  private void generateSeed(){
    seed = slug()+slug();
  }

  public static String slug() {
      UUID uuid = UUID.randomUUID();
      long hi = uuid.getMostSignificantBits();
      long lo = uuid.getLeastSignificantBits();
      ByteBuffer raw = ByteBuffer.allocate(16);
      raw.putLong(hi);
      raw.putLong(lo);
      byte[] rawBytes = raw.array();
      return Base64.encodeBytes(rawBytes).replace('+', '-').replace('/', '_').substring(0, 22);
  }

  public void generateSignature(String accessToken)
  throws InvalidOptionsException{
    try{
      SecretKeySpec keySpec = new SecretKeySpec(accessToken.getBytes("utf-8"),algo);
      Mac mac = Mac.getInstance(algo);
      mac.init(keySpec);

      StringBuilder builder = new StringBuilder();

      //Accumulator like stuff
      builder.append("version:" + Integer.toString(version) + "\n");
      //for named credentials
      if(clientId != null && issuer != null){
        builder.append("clientId:"+clientId+"\n");
        builder.append("issuer:"+issuer+"\n");
      }
      builder.append("seed:"+seed+"\n");
      builder.append("start:"+Long.toString(st.getTime())+"\n");
      builder.append("expiry:"+Long.toString(exp.getTime())+"\n");
      builder.append("scopes:\n");
      //Appending scopes
      String prefix = "";
      for(String scope : scopes){
        builder.append(prefix);
        prefix = "\n";
        builder.append(scope);
      }
      String temp = builder.toString();

      //Set the signature as base64 encoded string
      this.signature = Base64.encodeBytes(mac.doFinal(temp.getBytes("utf-8")));

    }catch(Exception e){
      e.printStackTrace();
      throw new InvalidOptionsException(e);
    }
  }

  public String toString(){
    Gson gson = new Gson();
    this.start = st.getTime();
    this.expiry = st.getTime();
    return gson.toJson(this);
  }
}
