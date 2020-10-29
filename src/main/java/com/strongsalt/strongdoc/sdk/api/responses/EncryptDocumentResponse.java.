/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;


/**
 * This class holds the response from the encrypt document API call.
 */
public class EncryptDocumentResponse {
    /**
     * The document ID for the encrypted document
     * This ID is needed to decrypt the document
     */
    private String docID;
    /**
     * The encrypted ciphertext of the document
     */
    private byte[] ciphertext;

    /**
     * Constructs a EncryptDocumentResponse
     *
     * @param docID      The document ID for the encrypted document
     * @param ciphertext The encrypted ciphertext of the document
     */
    public EncryptDocumentResponse(final String docID, final byte[] ciphertext) {
        this.docID = docID;
        this.ciphertext = ciphertext;
    }

    /**
     * Returns the encrypted document ID
     *
     * @return The encrypted document ID
     */
    public String getDocID() {
        return docID;
    }

    /**
     * Returns the encrypted ciphertext of the document
     *
     * @return The encrypted ciphertext of the document
     */
    public byte[] getCiphertext() {
        return ciphertext;
    }
}
