/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;

/**
 * This class holds the payment information
 */
public class Payment {
    /**
     * The timestamp of the payment billing transaction
     */
    private Timestamp billedAt;
    /**
     * The start of the payment period
     */
    private Timestamp periodStart;
    /**
     * The end of the payment period
     */
    private Timestamp periodEnd;
    /**
     * The amount of payment
     */
    private double amount;
    /**
     * The Payment status ("No Payment","Zero Payment","Payment Pending","Payment Success","Payment Failed")
     */
    private String status;

    /**
     * Constructs a Payment
     *
     * @param billedAt    The timestamp of the payment billing transaction
     * @param periodStart The start of the payment period
     * @param periodEnd   The end of the payment period
     * @param amount      The amount of payment
     * @param status      The Payment status ("No Payment","Zero Payment","Payment Pending","Payment Success","Payment Failed")
     */
    public Payment(final Timestamp billedAt, final Timestamp periodStart,
                   final Timestamp periodEnd, final double amount, final String status) {
        this.billedAt = billedAt;
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.amount = amount;
        this.status = status;
    }

    /**
     * Gets the timestamp of the payment billing transaction
     *
     * @return The timestamp of the payment billing transaction
     */
    public Timestamp getBillAt() {
        return billedAt;
    }

    /**
     * Gets the start of the payment period
     *
     * @return The start of the payment period
     */
    public Timestamp getPeriodStart() {
        return periodStart;
    }

    /**
     * Gets the end of the payment period
     *
     * @return The end of the payment period
     */
    public Timestamp getPeriodEnd() {
        return periodEnd;
    }

    /**
     * Gets the amount of payment
     *
     * @return The amount of payment
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the Payment status ("No Payment","Zero Payment","Payment Pending", "Payment Success","Payment Failed")
     *
     * @return The Payment status ("No Payment","Zero Payment","Payment Pending", "Payment Success","Payment Failed")
     */
    public String getStatus() {
        return status;
    }
}
