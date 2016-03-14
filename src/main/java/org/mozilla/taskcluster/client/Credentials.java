package org.mozilla.taskcluster.client;

import java.util.Date;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;

import net.iharder.Base64;

import org.mozilla.taskcluster.client.InvalidOptionsException;

public class Credentials {
  public final static transient String algo = "HmacSHA256";
  public String accessToken = "";
  public String clientId = null;
  public Certificate certificate = null;

  //Let this just...be there
  private Credentials(){
    accessToken = null;
    clientId = null;
    certificate = null;
  }

  public Credentials(String accessToken, String clientId, Certificate certificate){
    this.accessToken = accessToken;
    this.clientId = clientId;
    this.certificate = certificate;
  }

  /*
    This method is used to generate unnamed temporary
    credentials
  */
  public static Credentials createTemporaryCredentials(String clientId, String accessToken,
  String[] scopes, Date start, Date expiry) throws InvalidOptionsException {
    //Check dates
    if(start.after(expiry)){
      //throw error
      throw new InvalidOptionsException(new Throwable("start should be before expiry"));
    }
    if((long)(expiry.getTime() - start.getTime()) > (long) 31*24*60*60*1000){
      //throw error
      throw new InvalidOptionsException(new Throwable("Cannot exceed 31 days"));
    }
    Certificate cert = new Certificate(1,start,expiry,scopes);
    //Create signature;
    cert.generateSignature(accessToken);
    String temporaryAccessToken = generateTemporaryAccessToken(accessToken, cert.seed);
    return new Credentials(temporaryAccessToken, clientId, cert);
  }

  public static Credentials createTemporaryCredentials(String clientId, String issuer,
  String accessToken, String[] scopes, Date start, Date expiry)
  throws InvalidOptionsException {
    //Check dates
    if(start.after(expiry)){
      //throw error
      throw new InvalidOptionsException(new Throwable("start should be before expiry"));
    }
    if((long)(expiry.getTime() - start.getTime()) > (long) 31*24*60*60*1000){
      //throw error
      throw new InvalidOptionsException(new Throwable("Cannot exceed 31 days"));
    }
    Certificate cert = new Certificate(1,start,expiry,scopes,clientId, issuer);
    //Create signature;
    cert.generateSignature(accessToken);
    String temporaryAccessToken = generateTemporaryAccessToken(accessToken, cert.seed);
    return new Credentials(temporaryAccessToken, clientId, cert);
  }

  private static String generateTemporaryAccessToken(String accessToken, String seed){
    try{
      SecretKeySpec keySpec = new SecretKeySpec(accessToken.getBytes("utf-8"),algo);
      Mac mac = Mac.getInstance(algo);
      mac.init(keySpec);

      return Base64.encodeBytes(mac.doFinal(seed.getBytes("utf-8")))
      .replace('+','-')
      .replace('/','_')
      .replace("=","");
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
