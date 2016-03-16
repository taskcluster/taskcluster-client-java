package org.mozilla.taskcluster.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.ExponentialBackOff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public abstract class TaskClusterRequestHandler {

    static final Gson         gson;
    static HttpRequestFactory requestFactory;

    static {
        // TaskCluster uses UTC-based ISO8601 date format like this:
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        JsonSerializer<Date> ser = new JsonSerializer<Date>() {
            public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
                return date == null ? null : new JsonPrimitive(sdf.format(date));
            }
        };
        JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {

            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                try {
                    return json == null ? null : sdf.parse(json.getAsJsonPrimitive().getAsString());
                } catch (ParseException e) {
                    e.printStackTrace();
                    throw new JsonParseException(e);
                }
            }
        };
        gson = new GsonBuilder().registerTypeAdapter(Date.class, ser).registerTypeAdapter(Date.class, deser).create();
        HttpTransport httpTransport = new NetHttpTransport();
        requestFactory = httpTransport.createRequestFactory();
        Logger.getLogger(HttpTransport.class.getName()).setLevel(Level.ALL);
    }

    private Credentials credentials;
    private boolean     authenticate;
    private String      baseURL;

    public TaskClusterRequestHandler(Credentials credentials, String baseURL) {
        this.authenticate = true;
        this.baseURL = baseURL;
        this.credentials = credentials;
    }

    public TaskClusterRequestHandler(String baseURL) {
        this.authenticate = false;
        this.baseURL = baseURL;
        this.credentials = null;
    }

    public TaskClusterRequestHandler setBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    protected <Request, Response> CallSummary<Request, Response> apiCall(Request payload, String method, String route,
            Type responseType) throws APICallFailure {

        CallSummary<Request, Response> callSummary = new CallSummary<Request, Response>();
        callSummary.requestPayload = payload;
        if (payload != null) {
            callSummary.requestBody = gson.toJson(payload);
        } else {
            callSummary.requestBody = "";
        }
        HttpContent httpRequestContent = new ByteArrayContent("application/json", callSummary.requestBody.getBytes());

        try {
            HttpRequest request = requestFactory.buildRequest(method, new GenericUrl(baseURL + route),
                    httpRequestContent);
            if (authenticate) {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                messageDigest.update(callSummary.requestBody.getBytes());
                // We don't validate the hash, so no point in setting it...
                // String hash =
                // Base64.encodeBytes(messageDigest.digest());
                String hash = null;

                URI uri = new URI(baseURL + route);
                if (credentials != null && authenticate) {
                    String authorizationHeader = credentials.generateAuthorizationHeader(uri, method, hash);
                    HttpHeaders headers = new HttpHeaders();
                    headers.setAuthorization(authorizationHeader);
                    request.setHeaders(headers);
                }
            }
            request.setUnsuccessfulResponseHandler(
                    new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            HttpResponse httpResponse = request.execute();
            callSummary.responseBody = httpResponse.parseAsString();
            if (callSummary.responseBody != "") {
                callSummary.responsePayload = gson.fromJson(callSummary.responseBody, responseType);
            } else {
                callSummary.responsePayload = null;
            }

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (HttpResponseException e) {
            System.out.println(e.getContent());
            throw new APICallFailure(e);
        } catch (IOException e) {
            throw new APICallFailure(e);
        }
        return callSummary;
    }

    /**
     * Utility method to uri encode a parameter for using in url
     */
    protected static String uriEncode(String param) {
        try {
            return URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // This should not be possible since we specify UTF-8
            e.printStackTrace();
            System.exit(64);
        }
        return null;
    }
}
