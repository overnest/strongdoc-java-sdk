package com.strongsalt.strongdoc.sdk.api;

import java.nio.file.FileSystems;
import java.util.Map;

public class StrongDocTestConstants {
    // StrongDoc client
    public final static String HOST = "localhost";
    public final static int PORT = 9090;
    public final static String CERT_PATH = FileSystems.getDefault()
        .getPath("src", "test", "resources", "certs", "grpc.root.localhost.pem")
        .toAbsolutePath()
        .toString();

    // Subscription
    public final static String SOURCE = "Test Active";
    public final static String SOURCE_DATA = "";

    // StrongDocAccount
    public final static String ORG1_ADMIN_NAME = "Org1AdminUserName";
    public final static String ORG1_ADMIN_PASSWORD = "Org1AdminUserPassword";
    public final static String ORG1_ADMIN_EMAIL = "Org1AdminUser@somewhere.com";
    public final static String ORG1_NAME = "Org1Organization";
    public final static String ORG1_ADDRESS = "100 Java SDK Drive.";
    public final static String ORG1_USER_NAME = "Org1UserName";
    public final static String ORG1_USER_PASSWORD = "Org1UserPassword";
    public final static String ORG1_USER_EMAIL = "Org1User@somewhere.com";
    public final static String ORG2_ADMIN_NAME = "Org2AdminUserName";
    public final static String ORG2_ADMIN_PASSWORD = "Org2AdminUserPassword";
    public final static String ORG2_ADMIN_EMAIL = "Org2AdminUser@somewhere.com";
    public final static String ORG2_NAME = "Org2Organization";
    public final static String ORG2_ADDRESS = "200 Java SDK Drive.";

    // StrongDocBilling
    public final static String ORG3_ADMIN_NAME = "Org3AdminUserName";
    public final static String ORG3_ADMIN_PASSWORD = "Org3AdminUserPassword";
    public final static String ORG3_ADMIN_EMAIL = "Org3AdminUser@somewhere.com";
    public final static String ORG3_NAME = "Org3Organization";
    public final static String ORG3_ADDRESS = "300 Java SDK Drive.";

    // StrongDocDocument
    public final static String ORG4_ADMIN_NAME = "Org4AdminUserName";
    public final static String ORG4_ADMIN_PASSWORD = "Org4AdminUserPassword";
    public final static String ORG4_ADMIN_EMAIL = "Org4AdminUser@somewhere.com";
    public final static String ORG4_NAME = "Org4Organization";
    public final static String ORG4_ADDRESS = "400 Java SDK Drive.";
    public final static String ORG4_USER_NAME = "Org4UserName";
    public final static String ORG4_USER_PASSWORD = "Org4UserPassword";
    public final static String ORG4_USER_EMAIL = "Org4User@somewhere.com";

    public final static String ORG6_ADMIN_NAME = "Org6AdminUserName";
    public final static String ORG6_ADMIN_PASSWORD = "Org6AdminUserPassword";
    public final static String ORG6_ADMIN_EMAIL = "Org6AdminUser@somewhere.com";
    public final static String ORG6_NAME = "Org6Organization";
    public final static String ORG6_ADDRESS = "600 Java SDK Drive.";
    public final static String ORG6_USER_NAME = "Org6UserName";
    public final static String ORG6_USER_PASSWORD = "Org6UserPassword";
    public final static String ORG6_USER_EMAIL = "Org6User@somewhere.com";

    // StrongDocSearch
    public final static String ORG5_ADMIN_NAME = "Org5AdminUserName";
    public final static String ORG5_ADMIN_PASSWORD = "Org5AdminUserPassword";
    public final static String ORG5_ADMIN_EMAIL = "Org5AdminUser@somewhere.com";
    public final static String ORG5_NAME = "Org5Organization";
    public final static String ORG5_ADDRESS = "500 Java SDK Drive.";
    public final static String ORG5_USER_NAME = "Org5UserName";
    public final static String ORG5_USER_PASSWORD = "Org5UserPassword";
    public final static String ORG5_USER_EMAIL = "Org5User@somewhere.com";

    // Internal Server, superUser API
    public final static String LOGIN_API = "http://localhost:8081/v1/account/login";
    public final static String LOGOUT_API = "http://localhost:8081/v1/account/logout";
    public final static String REMOVE_ORG_API = "http://localhost:8081/v1/organization";
    public final static String USER_ID = "userid";
    public final static String PASSWORD = "passwd";
    public final static String TOKEN = "Token";
    public final static String AUTHENTICATION = "authorization";
    public final static String AUTHENTICATION_BEARER = "bearer";
    public final static String SUPER_USER_ID = "SUPER_USER_ID";
    public final static String SUPER_USER_PASSWORD = "SUPER_USER_PASSWORD";

    public static String getSuperUserId(){
        return getEnv(SUPER_USER_ID);
    }

    public static String getSuperUserPassword() {
        return getEnv(SUPER_USER_PASSWORD);
    }

    private static String getEnv(String key){
        Map<String, String> env = System.getenv();
        return env.getOrDefault(key, null);
    }
}
