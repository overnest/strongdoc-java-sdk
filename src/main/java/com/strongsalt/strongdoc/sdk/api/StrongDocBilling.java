/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;


import com.google.protobuf.util.Timestamps;
import com.strongsalt.strongdoc.sdk.api.responses.BillingDetailsResponse;
import com.strongsalt.strongdoc.sdk.api.responses.BillingFrequencyListResponse;
import com.strongsalt.strongdoc.sdk.api.responses.NextBillingFrequencyResponse;
import com.strongsalt.strongdoc.sdk.api.responses.ProcessSubscriptionEventResponse;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Billing;
import io.grpc.StatusRuntimeException;


/**
 * This class can be used to perform actions that are related to the cost breakdown and other details related to billing.
 */
public class StrongDocBilling {
    /**
     * It lists all items of the cost breakdown and also other details such as the billing frequency.
     * Requires Administrator privilege
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The billing details response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public BillingDetailsResponse getBillingDetails(final StrongDocServiceClient client, final String token)
            throws StatusRuntimeException {

        final Billing.GetBillingDetailsReq req = Billing.GetBillingDetailsReq.newBuilder().build();
        final Billing.GetBillingDetailsResp res = client.getBlockingStub().getBillingDetails(req);

        BillingDetailsResponse response = new BillingDetailsResponse(
                Timestamps.toString(res.getPeriodStart()),
                Timestamps.toString(res.getPeriodEnd()),
                res.getTotalCost());
        //searchCosts,
        //trafficCosts,
        //res.getBillingFrequency());

        Billing.DocumentCosts dc = res.getDocuments();
        response.setDocumentCosts(dc.getCost(), dc.getSize(), dc.getTier());

        Billing.SearchCosts sc = res.getSearch();
        response.setSearchCosts(sc.getCost(), sc.getSize(), sc.getTier());

        Billing.BillingFrequency bf = res.getBillingFrequency();
        response.setBillingFrequency(bf.getFrequency(), bf.getValidFrom(), bf.getValidTo());

        return response;
        //return new BillingDetailsResponse(res);
    }

    /**
     * It obtains the list of billing frequencies (past, current and future)
     * Requires Administrator privilege
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The billing frequency list
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public BillingFrequencyListResponse getBillingFrequencyList(final StrongDocServiceClient client,
                                                                final String token)
            throws StatusRuntimeException {
        final Billing.GetBillingFrequencyListReq req = Billing.GetBillingFrequencyListReq.newBuilder().build();
        final Billing.GetBillingFrequencyListResp res = client.getBlockingStub().getBillingFrequencyList(req);
        return new BillingFrequencyListResponse();
    }

    /**
     * It changes the next billing frequency
     * Requires Administrator privilege
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The next billing frequency response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public NextBillingFrequencyResponse setNextBillingFrequency(final StrongDocServiceClient client,
                                                                final String token)
            throws StatusRuntimeException {
        final Billing.SetNextBillingFrequencyReq req = Billing.SetNextBillingFrequencyReq.newBuilder().build();
        final Billing.SetNextBillingFrequencyResp res = client.getBlockingStub().setNextBillingFrequency(req);
        return new NextBillingFrequencyResponse();
    }

    /**
     * It processes subscription-related event
     * Designed to be called only by third-party services (like online payments provider)
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @return The billing details response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public ProcessSubscriptionEventResponse processSubscriptionEvent(final StrongDocServiceClient client,
                                                                     final String token)
            throws StatusRuntimeException {
        final Billing.ProcessSubscriptionEventReq req = Billing.ProcessSubscriptionEventReq.newBuilder().build();
        final Billing.ProcessSubscriptionEventResp res = client.getBlockingStub().processSubscriptionEvent(req);
        return new ProcessSubscriptionEventResponse();
    }
}
