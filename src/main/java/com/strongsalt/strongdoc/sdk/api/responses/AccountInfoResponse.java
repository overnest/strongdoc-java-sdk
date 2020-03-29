/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;

import java.util.ArrayList;

/**
 * This class holds the response from a billing details request.
 */
public class AccountInfoResponse {
    /**
     * Account's subscription info
     */
    private Subscription subscription;
    /**
     * List of all account's payments
     */
    private ArrayList<Payment> paymentList;

    // --------------------------- AccountInfoResponse ---------------------------

    /**
     * Constructs a AccountInfoResponse
     */
    public AccountInfoResponse() {
        this.paymentList = new ArrayList<Payment>();
    }

    /**
     * Sets the response's subscription
     *
     * @param type   The type of subscription (AWS Marketplace, Credit Card, etc.)
     * @param status The state of the subscription (Created, Subscribed, Unsubscribed, etc.)
     */
    public void setSubscription(final String type, final String status) {
        this.subscription = new Subscription(type, status);
    }

    /**
     * Gets the subscription from response
     *
     * @return The subscription
     */
    public Subscription getSubscription() {
        return this.subscription;
    }

    /**
     * Adds a payment to the response's payment list
     *
     * @param billedAt    The timestamp of the payment billing transaction
     * @param periodStart The start of the payment period
     * @param periodEnd   The end of the payment period
     * @param amount      The amount of payment
     * @param status      The Payment status ("No Payment","Zero Payment","Payment Pending","Payment Success","Payment Failed")
     */
    public void addPayment(final Timestamp billedAt, final Timestamp periodStart,
                           final Timestamp periodEnd, final double amount,
                           final String status) {
        this.paymentList.add(new Payment(billedAt, periodStart, periodEnd, amount, status));
    }

    /**
     * Gets the account's payments
     */
    public ArrayList<Payment> getPayments() {
        return this.paymentList;
    }
}
