/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.strongsalt.strongdoc.sdk.proto.Billing;

import java.util.Date;

/*
enum TimeInterval {
    UNDEFINED(0),
    MONTHLY(1),
    YEARLY(2);

    public final int interval;

    private TimeInterval(int interval) {
        this.interval = interval;
    }
}
*/

/**
 * This class holds the response from a billing details request.
 */
public class BillingDetailsResponse {
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
     * Total cost incurred during the requested billing period
     */
    private double totalCost;
    /**
     * Usage and cost breakdown for stored documents
     */
    private DocumentCosts documentCosts;
    /**
     * Usage and cost breakdown for stored search indices
     */
    private SearchCosts searchCosts;
    /**
     * Usage and cost breakdown for used traffic
     */
    private TrafficCosts trafficCosts;
    /**
     * Billing frequency used during the requested billing period
     */
    private BillingFrequency billingFrequency;

    /**
     * Constructs a BillingDetailsResponse
     *
     * @param periodStart Start of the requested billing period
     * @param periodEnd   End of the requested billing period
     * @param totalCost   Total cost incurred during the requested billing period
     * @param documentCosts   Usage and cost breakdown for stored documents
     * @param searchCosts   Usage and cost breakdown for stored search indices
     * @param trafficCosts   Usage and cost breakdown for used traffic
     * @param billingFrequency   Billing frequency used during the requested billing period
     */
    public BillingDetailsResponse(final Date periodStart,
                                  final Date periodEnd,
                                  final double totalCost,
                                  final DocumentCosts documentCosts,
                                  final SearchCosts searchCosts,
                                  final TrafficCosts trafficCosts,
                                  final BillingFrequency billingFrequency) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalCost = totalCost;
        this.documentCosts = documentCosts;
        this.searchCosts = searchCosts;
        this.trafficCosts = trafficCosts;
        this.billingFrequency = billingFrequency;
    }

    /**
     * Gets the start of the requested billing period.
     *
     * @return The start of the billing period
     */
    public Date getPeriodStart() {
      return periodStart;
    }

    /**
     * Gets the end of the requested billing period.
     *
     * @return The end of the billing period
     */
    public Date getPeriodEnd() {
      return periodEnd;
    }

    /**
     * Gets the total cost incurred during the requested billing period.
     *
     * @return The total cost
     */
    public double getTotalCost() {
      return totalCost;
    }

    /**
     * Gets the usage and cost breakdown for stored documents.
     *
     * @return The document costs
     * @see com.strongsalt.strongdoc.sdk.api.responses.DocumentCosts
     */
    public DocumentCosts getDocumentCosts() {
      return documentCosts;
    }

    /**
     * Gets the usage and cost breakdown for stored search indices.
     *
     * @return The search costs
     * @see com.strongsalt.strongdoc.sdk.api.responses.SearchCosts
     */
    public SearchCosts getSearchCosts() {
      return searchCosts;
    }

    /**
     * Gets the usage and cost breakdown for used traffic.
     *
     * @return The traffic costs
     * @see com.strongsalt.strongdoc.sdk.api.responses.TrafficCosts
     */
    public TrafficCosts getTrafficCosts() {
      return trafficCosts;
    }

    /**
     * Gets the billing frequency used during the requested billing period.
     *
     * @return The billing frequency
     * @see com.strongsalt.strongdoc.sdk.api.responses.BillingFrequency
     */
    public BillingFrequency getBillingFrequency() {
      return billingFrequency;
    }
}
