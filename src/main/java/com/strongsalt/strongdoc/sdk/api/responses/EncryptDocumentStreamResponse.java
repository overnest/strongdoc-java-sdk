/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import java.io.InputStream;

/**
 * This class holds the response from the encrypt document stream API call.
 */
public class EncryptDocumentStreamResponse {
    /**
     * The document ID for the encrypted document
     * This ID is needed to decrypt the document
     */
    private String docID;
    /**
     * The stream of the encrypted ciphertext
     */
    private InputStream cipherStream;

    /**
     * Constructs a EncryptDocumentResponse
     *
     * @param docID        The document ID for the encrypted document
     * @param cipherStream The stream of the encrypted ciphertext
     */
    public EncryptDocumentStreamResponse(final String docID, InputStream cipherStream) {
        this.docID = docID;
        this.cipherStream = cipherStream;
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
     * Returns the stream of the encrypted ciphertext
     *
     * @return The stream of the encrypted ciphertext
     */
    public InputStream getCipherStream() {
        return cipherStream;
    }
}
