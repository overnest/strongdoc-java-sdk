/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;
;

/**
 * This class holds information for a single document.
 */
public class DocActionHistoryInfo {
    /**
     * The document ID
     */
    private String docID;
    /**
     * The user ID
     */
    private String userID;
    /**
     * The document name
     */
    private String docName;
    /**
     * The action time of the access record
     */
    private Timestamp actionTime;
    /**
     * The action type
     */
    private String actionType;
    /**
     * The other user ID of actions such as share
     */
    private String otherUserID;

    /**
     * Constructs a document info
     *
     * @param docID   
     * @param userID
     * @param docName 
     * @param actionTime
     * @param actionType
     * @param otherUserID    
     */
    public DocActionHistoryInfo(final String docID,final String userID, final String docName, final Timestamp actionTime, final String actionType, final String otherUserID) {
        this.docID = docID;
        this.userID = userID;
        this.docName = docName;
        this.actionTime = actionTime;
        this.actionType = actionType;
        this.otherUserID = otherUserID;
    }

    public String getDocID() {
        return docID;
    }

    public String getUserID() {
        return userID;
    }

    public String getDocName() {
        return docName;
    }

    public Timestamp getActionTime() {
        return actionTime;
    }

    public String getActionType() {
        return actionType;
    }

    public String getOtherUserID() {
        return otherUserID;
    }
}
