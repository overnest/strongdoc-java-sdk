/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

/**
 * This class holds the subscription information
 */
public class Subscription {
    /**
     * The type of subscription (AWS Marketplace, Credit Card, etc.)
     */
    private String type;
    /**
     * The state of the subscription (Created, Subscribed, Unsubscribed, etc.)
     */
    private String status;

    /**
     * Constructs a Subscription
     *
     * @param type   The type of subscription (AWS Marketplace, Credit Card, etc.)
     * @param status The state of the subscription (Created, Subscribed, Unsubscribed, etc.)
     */
    public Subscription(final String type, final String status) {
        this.type = type;
        this.status = status;
    }

    /**
     * Gets the subscription type
     *
     * @return The type of subscription (AWS Marketplace, Credit Card, etc.)
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the subscription status
     *
     * @return The state of the subscription (Created, Subscribed, Unsubscribed, etc.)
     */
    public String getStatus() {
        return status;
    }
}
