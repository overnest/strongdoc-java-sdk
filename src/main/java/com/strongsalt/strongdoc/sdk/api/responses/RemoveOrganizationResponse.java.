/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;


/**
 * This class holds the response from remove organization.
 */
public class RemoveOrganizationResponse {
    /**
     * Whether the removal was successful
     */
    private boolean success;
    /**
     * True in case the removal will happen later after org has been charged one last time
     */
    private boolean postponed;

    /**
     * Constructs a remove organization response
     *
     * @param success   Whether the removal was successful
     * @param postponed True in case the removal will happen later after org has been charged one last time
     */
    public RemoveOrganizationResponse(final boolean success, final boolean postponed) {
        this.success = success;
        this.postponed = postponed;
    }

    /**
     * Returns whether the removal was successful
     *
     * @return Whether the removal was successful
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Returns whether the removal will happen later
     *
     * @return Whether the removal will happen later
     */
    public boolean isPostponed() {
        return postponed;
    }
}
