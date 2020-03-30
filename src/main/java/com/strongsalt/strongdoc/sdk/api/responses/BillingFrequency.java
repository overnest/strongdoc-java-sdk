/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;
import com.strongsalt.strongdoc.sdk.proto.Billing;

/**
 * This class holds the billing frequency used during the requested billing period
 */
public class BillingFrequency {
    /**
     * Billing frequency
     */
    private Billing.TimeInterval frequency;
    /**
     * Start of billing frequency validity
     */
    private Timestamp validFrom;
    /**
     * End of billing frequency validity
     */
    private Timestamp validTo;

    /**
     * Constructs a BillingFrequency
     *
     * @param frequency Billing frequency
     * @param validFrom Start of billing frequency validity
     * @param validTo   End of billing frequency validity
     */
    public BillingFrequency(final Billing.TimeInterval frequency, final Timestamp validFrom,
                            final Timestamp validTo) {
        this.frequency = frequency;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    /**
     * Gets the billing frequency
     */
    public Billing.TimeInterval getFrequency() {
        return frequency;
    }

    /**
     * Gets the start of billing frequency validity
     */
    public Timestamp getValidFrom() {
        return validFrom;
    }

    /**
     * Gets the end of billing frequency validity
     */
    public Timestamp getValidTo() {
        return validTo;
    }
}
