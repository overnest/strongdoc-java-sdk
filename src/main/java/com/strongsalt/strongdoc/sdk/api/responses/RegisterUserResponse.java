package com.strongsalt.strongdoc.sdk.api.responses;

/**
 * This class holds the response from the register user API call.
 */
public class RegisterUserResponse {
    /**
     * The organization ID
     */
    private String orgID;
    /**
     * The user ID
     */
    private String userID;
    /**
     * Whether is successful
     */
    private boolean succ;

    /**
     * Constructs a RegisterOrganizationResponse
     *
     * @param succ   whether is successful
     * @param orgID  The organization ID
     * @param userID The user ID
     */
    public RegisterUserResponse(final boolean succ, final String orgID, final String userID) {
        this.succ = succ;
        this.orgID = orgID;
        this.userID = userID;
    }

    /**
     * Returns the organization ID
     *
     * @return The organization ID
     */
    public String getOrgID() {
        return orgID;
    }

    /**
     * Returns the user ID
     *
     * @return The user ID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Returns Whether is successful
     *
     * @return Whether is successful
     */
    public boolean getSuccess() {
        return succ;
    }
}
