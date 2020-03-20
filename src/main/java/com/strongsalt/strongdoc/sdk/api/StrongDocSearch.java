/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.strongsalt.strongdoc.sdk.api.responses.SearchDocumentResponse;
import com.strongsalt.strongdoc.sdk.api.responses.SearchDocumentResult;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Search.DocumentResult;
import com.strongsalt.strongdoc.sdk.proto.Search.SearchReq;
import com.strongsalt.strongdoc.sdk.proto.Search.SearchResp;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;


/**
 * This class can be used to perform actions that are related to Search.
 */
public class StrongDocSearch {
    /**
     * Search for document that contains a specific word.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param query  The query string.
     * @return The search response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public SearchDocumentResponse runSearch(final StrongDocServiceClient client,
                                            final String token,
                                            final String query)
            throws StatusRuntimeException {

        final SearchReq req = SearchReq.newBuilder()
                .setQuery(query)
                .build();

        final SearchResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).search(req);

        ArrayList<SearchDocumentResult> hitsList = new ArrayList<SearchDocumentResult>();
        for (DocumentResult result : res.getHitsList()) {
            hitsList.add(new SearchDocumentResult(result.getDocID(), result.getScore()));
        }

        return new SearchDocumentResponse(hitsList);
    }
}
