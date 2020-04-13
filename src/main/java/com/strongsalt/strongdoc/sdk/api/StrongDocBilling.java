/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api;

import com.google.protobuf.util.Timestamps;
import com.strongsalt.strongdoc.sdk.api.responses.*;
import com.strongsalt.strongdoc.sdk.client.JwtCallCredential;
import com.strongsalt.strongdoc.sdk.client.StrongDocServiceClient;
import com.strongsalt.strongdoc.sdk.proto.Billing;
import io.grpc.StatusRuntimeException;

import java.util.Date;

/**
 * This class can be used to perform actions that are related to the cost breakdown and other details related to billing.
 */
public class StrongDocBilling {
    public enum TimeInterval {
        /**
        * <code>UNDEFINED = 0;</code>
        */
        UNDEFINED(0),
        /**
        * <code>MONTHLY = 1;</code>
        */
        MONTHLY(1),
        /**
        * <code>YEARLY = 2;</code>
        */
        YEARLY(2),
        UNRECOGNIZED(-1);
    
        public final int interval;
    
        private TimeInterval(int interval) {
            this.interval = interval;
        }
    }

    /**
     * It lists all items of the cost breakdown and also other details such as the billing frequency.
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param at     The timestamp of the requested billing details.
     * @return The billing details response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public BillingDetailsResponse getBillingDetails(final StrongDocServiceClient client, final String token,
                                                    final Date at)
            throws StatusRuntimeException {

        final Billing.GetBillingDetailsReq req = Billing.GetBillingDetailsReq.newBuilder()
                .setAt(Timestamps.fromMillis(at.getTime()))
                .build();
        final Billing.GetBillingDetailsResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getBillingDetails(req);

        Billing.DocumentCosts bdc = res.getDocuments();
        DocumentCosts dc = new DocumentCosts(bdc.getCost(), bdc.getSize(), bdc.getTier());

        Billing.SearchCosts bsc = res.getSearch();
        SearchCosts sc = new SearchCosts(bsc.getCost(), bsc.getSize(), bsc.getTier());

        Billing.TrafficCosts btc = res.getTraffic();
        TrafficCosts tc = new TrafficCosts(btc.getCost(), btc.getIncoming(), btc.getOutgoing(), btc.getTier());

        Billing.BillingFrequency bbf = res.getBillingFrequency();
        BillingFrequency bf = new BillingFrequency(
                TimeInterval.valueOf(bbf.getFrequency().toString()), 
                new Date(Timestamps.toMillis(bbf.getValidFrom())), 
                new Date(Timestamps.toMillis(bbf.getValidTo())));

        BillingDetailsResponse response = new BillingDetailsResponse(
                new Date(Timestamps.toMillis(res.getPeriodStart())),
                new Date(Timestamps.toMillis(res.getPeriodEnd())),
                res.getTotalCost(),
                dc, sc, tc, bf);

        return response;
    }

    /**
     * It obtains the list of billing frequencies (past, current and future)
     * This requires an administrator privilege.
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
        final Billing.GetBillingFrequencyListResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getBillingFrequencyList(req);

        // To do create response
        final BillingFrequencyListResponse response = new BillingFrequencyListResponse();

        for (int index = 0; index < res.getBillingFrequencyListCount(); index++) {
            Billing.BillingFrequency bf = res.getBillingFrequencyList(index);
            response.addBillingFrequency(TimeInterval.valueOf(bf.getFrequency().toString()), 
                new Date(Timestamps.toMillis(bf.getValidFrom())), 
                new Date(Timestamps.toMillis(bf.getValidTo())));
        }

        return response;
    }

    /**
     * It changes the next billing frequency
     * This requires an administrator privilege.
     *
     * @param client    The StrongDoc client used to call this API.
     * @param token     The user JWT token.
     * @param frequency The Billing frequency.
     * @param validFrom When this billing frequency becomes valid.
     * @return The next billing frequency response.
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public NextBillingFrequencyResponse setNextBillingFrequency(final StrongDocServiceClient client,
                                                                final String token,
                                                                final TimeInterval frequency,
                                                                final Date validFrom)
            throws StatusRuntimeException {

        final Billing.SetNextBillingFrequencyReq req = Billing.SetNextBillingFrequencyReq.newBuilder()
                .setFrequency(Billing.TimeInterval.valueOf(frequency.toString()))
                .setValidFrom(Timestamps.fromMillis(validFrom.getTime()))
                .build();
        final Billing.SetNextBillingFrequencyResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).setNextBillingFrequency(req);

        Billing.BillingFrequency bbf = res.getNextBillingFrequency();
        BillingFrequency bf = new BillingFrequency(
                TimeInterval.valueOf(bbf.getFrequency().toString()), 
                new Date(Timestamps.toMillis(bbf.getValidFrom())), 
                new Date(Timestamps.toMillis(bbf.getValidTo())));

        return new NextBillingFrequencyResponse(bf);
    }

    /**
     * Obtains the list of large traffic usages
     * This requires an administrator privilege.
     *
     * @param client The StrongDoc client used to call this API.
     * @param token  The user JWT token.
     * @param at     The Timestamp of the requested traffic information
     * @return The large traffic response
     * @throws StatusRuntimeException on gRPC errors
     * @see StatusRuntimeException io.grpc
     */
    public LargeTrafficResponse getLargeTraffic(final StrongDocServiceClient client,
                                                final String token,
                                                final Date at)
            throws StatusRuntimeException {

        final Billing.GetLargeTrafficReq req = Billing.GetLargeTrafficReq.newBuilder()
                .setAt(Timestamps.fromMillis(at.getTime()))
                .build();
        final Billing.GetLargeTrafficResp res = client.getBlockingStub()
                .withCallCredentials(JwtCallCredential.getCallCredential(token)).getLargeTraffic(req);

        final LargeTrafficResponse response = new LargeTrafficResponse(
                new Date(Timestamps.toMillis(res.getPeriodStart())),
                new Date(Timestamps.toMillis(res.getPeriodEnd())));

        for (int index = 0; index < res.getLargeTrafficCount(); index++) {
            Billing.TrafficDetail td = res.getLargeTraffic(index);
            response.addTrafficDetail(new Date(Timestamps.toMillis(td.getTime())), td.getUserID(), td.getMethod(),
                    td.getURI(), td.getIncoming(), td.getOutgoing());
        }

        return response;
    }
}
