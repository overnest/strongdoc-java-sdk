package com.strongsalt.strongdoc.sdk.api;

import org.json.JSONException;
import java.io.IOException;
import com.strongsalt.strongdoc.sdk.api.StrongDocTestConstants;

public class StrongDocTestTeardown {
    InternalServiceManager manager;
    String token;

    public StrongDocTestTeardown() throws IOException, JSONException {
        manager = new InternalServiceManager();
        token = InternalServiceManager.getInstance().login(StrongDocTestConstants.getSuperUserId(), StrongDocTestConstants.getSuperUserPassword());
    }


    public void hardRemoveOrganization(String orgID) throws IOException, JSONException {
        manager.removeOrganization(token, orgID);
        System.out.println("removed organization "+orgID);
    }

    public void exit() throws IOException, JSONException {
        manager.logout(token, StrongDocTestConstants.getSuperUserId());
        manager.close();
    }
}
