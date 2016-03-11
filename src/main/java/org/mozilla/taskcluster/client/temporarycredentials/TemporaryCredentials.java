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

class Credentials {
  public String accessToken = "";
  public String clientId = null;
  public String certificate = "";
}

/*
  Temporary Credentials for taskcluster
  For creating unnamed Temporary credentials :
    @params
    clientId :String
    accessToken :String
    scopes :String[]
    start :Date
    expiry :Date
*/

public class TemporaryCredentials{
  public static final String algo = "HmacSHA256";

  public static String createCredentials(String clientId, String accessToken,
  String[] scopes, Date start, Date expiry ) throws InvalidOptionsException{
    //Check dates
    if(start.after(expiry)){
      //throw error
      throw new InvalidOptionsException(new Throwable("start should be before expiry"));
    }
    if((long)(expiry.getTime() - start.getTime()) > (long) 31*24*60*60*1000){
      //throw error
      throw new InvalidOptionsException(new Throwable("Cannot exceed 31 days"));
    }
    Credentials credentials = new Credentials();
    Certificate cert = new Certificate(1,start,expiry,scopes);
    //Create signature;
    cert.generateSignature(accessToken);

    credentials.accessToken = generateTemporaryAccessToken(accessToken, cert.seed);
    credentials.clientId = clientId;
    credentials.certificate = cert.toString();

    Gson gson = new Gson();
    return gson.toJson(credentials);
  }

  /*
    Generates the temporary access token
  */

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
