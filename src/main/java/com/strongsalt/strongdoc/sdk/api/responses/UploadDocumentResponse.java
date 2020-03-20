/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;


/**
 * This class holds the response from the upload document API call.
 */
public class UploadDocumentResponse {
    /**
     * The document ID for the uploaded document
     */
    private String docID;
    /**
     * The total number of bytes uploaded
     */
    private int numBytes;

    /**
     * Constructs a UploadDocumentResponse
     *
     * @param docID    The uploaded document ID
     * @param numBytes The total uploaded bytes
     */
    public UploadDocumentResponse(final String docID, final int numBytes) {
        this.docID = docID;
        this.numBytes = numBytes;
    }

    /**
     * Returns the uploaded document ID
     *
     * @return The uploaded document ID
     */
    public String getDocID() {
        return docID;
    }

    /**
     * Returns the total uploaded bytes
     *
     * @return The total uploaded bytes
     */
    public int getNumBytes() {
        return numBytes;
    }
}
