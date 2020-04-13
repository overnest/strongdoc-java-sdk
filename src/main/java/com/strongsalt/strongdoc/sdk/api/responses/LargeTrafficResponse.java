/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class holds the response from a large traffic information request.
 */
public class LargeTrafficResponse {
    /**
     * Start of the requested billing period
     */
    private Date periodStart;
    //private Timestamps periodStart;
    /**
     * End of the requested billing period
     */
    private Date periodEnd;
    //private Timestamps periodEnd;
    /**
     * The details of large traffic events
     */
    private ArrayList<TrafficDetail> trafficDetailList;

    /**
     * Constructs a LargeTrafficResponse
     *
     * @param periodStart Start of the requested billing period
     * @param periodEnd   End of the requested billing period
     */
    public LargeTrafficResponse(final Date periodStart,
                                final Date periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.trafficDetailList = new ArrayList<TrafficDetail>();
    }

    /**
     * Add a traffic detail to the response's traffic detail list
     *
     * @param time     Timestamp of the large traffic event
     * @param userID   ID of the user who made the request
     * @param method   Method of the request
     * @param uri      URI called by the request
     * @param incoming Size of the request (in MB)
     * @param outgoing Size of the response (in MB)
     */
    public void addTrafficDetail(final Date time, final String userID, final String method,
                                 final String uri, final double incoming, final double outgoing) {
        this.trafficDetailList.add(new TrafficDetail(time, userID, method, uri, incoming, outgoing));
    }

    /**
     * Gets the start of the requested billing period
     */
    public Date getPeriodStart() {
        return periodStart;
    }

    /**
     * Gets the end of the requested billing period
     */
    public Date getPeriodEnd() {
        return periodEnd;
    }

    /**
     * Gets the details of large traffic events
     */
    public ArrayList<TrafficDetail> getTrafficDetails() {
        return trafficDetailList;
    }
}
