/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

/**
 * This class holds the details of large traffic events
 */
public class TrafficDetail {
    /**
     * Timestamp of the large traffic event
     */
    private String time;
    /**
     * The ID of the user who made the request
     */
    private String userID;
    /**
     * TTP method of the request
     */
    private String method;
    /**
     * URI called by the request
     */
    private String uri;
    /**
     * Size of the request (in MB)
     */
    private double incoming;
    /**
     * Size of the response (in MB)
     */
    private double outgoing;

    /**
     * Constructs a TrafficDetail
     *
     * @param time     Timestamp of the large traffic event
     * @param userID   ID of the user who made the request
     * @param method   Method of the request
     * @param uri      URI called by the request
     * @param incoming Size of the request (in MB)
     * @param outgoing Size of the response (in MB)
     */
    public TrafficDetail(final String time, final String userID, final String method,
                         final String uri, final double incoming, final double outgoing) {
        this.time = time;
        this.userID = userID;
        this.method = method;
        this.uri = uri;
        this.incoming = incoming;
        this.outgoing = outgoing;
    }

    /*
     * Gets the timestamp of the large traffic event
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the the ID of the user who made the request
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Gets the method of the request
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gets the URI called by the request
     */
    public String getURI() {
        return uri;
    }

    /**
     * Gets the size of the request (in MB)
     */
    public double getIncoming() {
        return incoming;
    }

    /**
     * Gets the size of the response (in MB)
     */
    public double getOutgoing() {
        return outgoing;
    }
}
