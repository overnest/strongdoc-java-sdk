package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient.ServiceLocation;

public class StrongDocTestConstants {
    // StrongDoc client
    public final static ServiceLocation location = ServiceLocation.LOCAL;

    // Registration
    public final static String SOURCE = "Test Active";
    public final static String SOURCE_DATA = "";
    public final static String INVITATION_CODE = "abcdef";


    // Internal Server, superUser API
    public final static String LOGIN_API = "http://localhost:8081/v1/account/login";
    public final static String LOGOUT_API = "http://localhost:8081/v1/account/logout";
    public final static String REMOVE_ORG_API = "http://localhost:8081/v1/organization";
    public final static String USER_ID = "userid";
    public final static String PASSWORD = "passwd";
    public final static String TOKEN = "Token";
    public final static String AUTHENTICATION = "authorization";
    public final static String AUTHENTICATION_BEARER = "bearer";
    public final static String SUPER_USER_ID = "account@strongsalt.com";
    public final static String SUPER_USER_PASSWORD = "c2QQyWuhNC8bvxdn";
}