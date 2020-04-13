/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import java.util.Date;
import com.strongsalt.strongdoc.sdk.api.StrongDocBilling;

/**
 * This class holds the billing frequency used during the requested billing period
 */
public class BillingFrequency {
    /**
     * Billing frequency
     */
    private StrongDocBilling.TimeInterval frequency;
    /**
     * Start of billing frequency validity
     */
    private Date validFrom;
    /**
     * End of billing frequency validity
     */
    private Date validTo;

    /**
     * Constructs a BillingFrequency
     *
     * @param frequency Billing frequency
     * @param validFrom Start of billing frequency validity
     * @param validTo   End of billing frequency validity
     */
    public BillingFrequency(final StrongDocBilling.TimeInterval frequency, final Date validFrom,
                            final Date validTo) {
        this.frequency = frequency;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    /**
     * Gets the billing frequency
     */
    public StrongDocBilling.TimeInterval getFrequency() {
        return frequency;
    }

    /**
     * Gets the start of billing frequency validity
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * Gets the end of billing frequency validity
     */
    public Date getValidTo() {
        return validTo;
    }
}
