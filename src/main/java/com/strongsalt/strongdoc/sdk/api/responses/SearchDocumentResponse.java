/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import java.util.List;

/**
 * This class holds the response from the Search query API call.
 */
public class SearchDocumentResponse {
    /**
     * The hit list of the search query
     */
    private List hitsList;

    /**
     * Constructs a SearchDocumentResponse
     * @param hitsList The hit list of the search query
     */
    public SearchDocumentResponse(final List hitsList) {
        this.hitsList = hitsList;
    }

    /**
     * Returns the search results
     * @return The hit list of the search query
     */
    public List getHitsList() {
        return hitsList;
    }
}
