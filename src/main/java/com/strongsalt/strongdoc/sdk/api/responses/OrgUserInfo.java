/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;


/**
 * This class holds a single organization user.
 */
public class OrgUserInfo {
    /**
     * The user ID
     */
    private String userID;
    /**
     * The user's name
     */
    private String name;
    /**
     * The user's orgID
     */
    private String orgID;
    /**
     * The user's email address
     */
    private String email;
    /**
     * Indicates whether the user is an administrator
     */
    private boolean isAdmin;

    /**
     * Constructs an organization user info
     *
     * @param userID The userID
     * @param name   The user's name
     * @param orgID The user's orgID
     * @param email The user's email address
     * @param isAdmin  Indicates whether the user is an administrator
     */
    public OrgUserInfo(final String userID, final String name, final String orgID, 
        final String email, final boolean isAdmin) {
        this.name = name;
        this.userID = userID;
        this.email = email;
        this.orgID = orgID;
        this.isAdmin = isAdmin;
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
     * Returns the user's name
     *
     * @return The user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the user's orgID
     *
     * @return The user's orgID
     */
    public String getOrgID() {
        return orgID;
    }

    /**
     * Returns the user's email address
     *
     * @return The user's email address
     */
    public String getEmail() {
        return email;
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
