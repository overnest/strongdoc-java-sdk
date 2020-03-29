/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;
import com.strongsalt.strongdoc.sdk.proto.Billing;

import java.util.ArrayList;

/**
 * This class holds the response from a billing frequency list request.
 */
public class BillingFrequencyListResponse {
    /**
     * List of billing frequencies
     */
    private ArrayList<BillingFrequency> billingFrequencyList;

    /**
     * Constructs a BillingFrequencyListResponse
     */
    public BillingFrequencyListResponse() {
        this.billingFrequencyList = new ArrayList<BillingFrequency>();
    }

    /**
     * Adds a billing frequency to the response's billing frequency list
     *
     * @param frequency Billing frequency
     * @param validFrom Start of billing frequency validity
     * @param validTo   End of billing frequency validity
     */
    public void addBillingFrequency(final Billing.TimeInterval frequency,
                                    final Timestamp validFrom, final Timestamp validTo) {
        this.billingFrequencyList.add(new BillingFrequency(frequency, validFrom, validTo));
    }

    /*
     * Gets the list of billing frequencies
     */
    public ArrayList<BillingFrequency> getBillingFrequencyList() {
        return billingFrequencyList;
    }
}
