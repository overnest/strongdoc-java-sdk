/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;


/**
 * This class holds a single organization user.
 */
public class OrgUserInfo {
    /**
     * The user name
     */
    private String username;
    /**
     * The user ID
     */
    private String userID;
    /**
     * Indicates whether the user is an administrator
     */
    private boolean isAdmin;

    /**
     * Constructs an organization user info
     *
     * @param username The user name
     * @param userID   The user ID
     * @param isAdmin  Indicates whether the user is an administrator
     */
    public OrgUserInfo(final String username, final String userID, final boolean isAdmin) {
        this.username = username;
        this.userID = userID;
        this.isAdmin = isAdmin;
    }

    /**
     * Returns the username
     *
     * @return The username
     */
    public String getUsername() {
        return username;
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
     * Returns the score of the search document result
     *
     * @return The score of the matching document
     */
    public boolean isAdmin() {
        return isAdmin;
    }
}
