/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.strongsalt.strongdoc.sdk.api.StrongDocBilling;

import java.util.ArrayList;
import java.util.Date;

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
    public void addBillingFrequency(final StrongDocBilling.TimeInterval frequency,
                                    final Date validFrom, final Date validTo) {
        this.billingFrequencyList.add(new BillingFrequency(frequency, validFrom, validTo));
    }

    /*
     * Gets the list of billing frequencies
     */
    public ArrayList<BillingFrequency> getBillingFrequencyList() {
        return billingFrequencyList;
    }
}
