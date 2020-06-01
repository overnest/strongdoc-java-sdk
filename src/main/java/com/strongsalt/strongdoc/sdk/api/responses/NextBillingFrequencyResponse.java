/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;


/**
 * This class holds the response from a next billing frequency request.
 */
class NextBillingFrequencyResponse {
    /**
     * The next billing frequency
     */
    private BillingFrequency nextBillingFrequency;

    /**
     * Constructs a NextBillingFrequencyResponse
     *
     * @param nextBillingFrequency The next billing frequency
     */
    public NextBillingFrequencyResponse(BillingFrequency nextBillingFrequency) {
        this.nextBillingFrequency = nextBillingFrequency;
    }

    /**
     * Get the next billing frequency
     */
    public BillingFrequency getNextBillingFrequency() {
        return nextBillingFrequency;
    }
}
