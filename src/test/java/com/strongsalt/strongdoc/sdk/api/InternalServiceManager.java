package com.strongsalt.strongdoc.sdk.api;

import io.netty.handler.codec.http.HttpMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to call super user API
 * perform remove organization operation
 *  - normal user cannot actually delete organization, because we will still keep payment info after received remove request
 *  - superUser removeOrg request would actually delete record in database
 */
public class InternalServiceManager {
    static CloseableHttpClient httpClient;

    public CloseableHttpClient getClient(){
        if(httpClient == null){
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    public String login(String userID, String password) throws IOException, JSONException {
        Map<String, String> map = new HashMap<>();
        map.put(StrongDocTestConstants.USER_ID, userID);
        map.put(StrongDocTestConstants.PASSWORD, password);
        JSONObject resp = sendRequest(HttpMethod.GET, null, buildURL(StrongDocTestConstants.LOGIN_API, map), true);
        return resp == null ? "" : resp.optString(StrongDocTestConstants.TOKEN, "");
    }

    public void logout(String token, String userID) throws IOException, JSONException {
        Map<String, String> map = new HashMap<>();
        map.put(StrongDocTestConstants.USER_ID, userID);
        sendRequest(HttpMethod.PUT, token, buildURL(StrongDocTestConstants.LOGOUT_API, map), false);
    }

    public void removeOrganization(String token, String orgid) throws IOException, JSONException {
        sendRequest(HttpMethod.DELETE, token, StrongDocTestConstants.REMOVE_ORG_API+"/"+orgid, false);
    }

    private void addAuthenticationHeader(HttpRequestBase requestBasease, String token) {
        requestBasease.setHeader(StrongDocTestConstants.AUTHENTICATION, StrongDocTestConstants.AUTHENTICATION_BEARER + " " + token);
    }

    private JSONObject sendRequest(HttpMethod method, String token, String url,  boolean needResponse) throws IOException, JSONException{
        HttpRequestBase request = createBaseRequest(url, method);
        if(token != null){
            addAuthenticationHeader(request, token);
        }
        HttpResponse response = getClient().execute(request);
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            String retSrc = EntityUtils.toString(responseEntity);
            if(retSrc == null || retSrc.equals("")) return null;
            if (needResponse){
                JSONObject jsonObj = new JSONObject(retSrc);
                return jsonObj;
            }
            return null;
        }
        return null;
    }

    private HttpRequestBase createBaseRequest(final String url, final HttpMethod method) {
        if (method == HttpMethod.GET) {
            return new HttpGet(url);
        }
        if (method == HttpMethod.POST) {
            return new HttpPost(url);
        }
        if (method == HttpMethod.PUT) {
            return new HttpPut(url);
        }
        if (method == HttpMethod.DELETE) {
            return new HttpDelete(url);
        }
        return null;
    }

    private String buildURL(String baseURL, Map<String, String> map) {
        if(map == null || map.size() == 0) return "";
        StringBuffer sb = new StringBuffer("?");
        for(String key : map.keySet()){
            String value = map.get(key);
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        }
        sb.delete(sb.length()-1, sb.length());
        return baseURL + sb.toString();
    }

    public void close() throws IOException {
        getClient().close();
    }
}