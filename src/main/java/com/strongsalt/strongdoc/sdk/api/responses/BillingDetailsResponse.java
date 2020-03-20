/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;
import com.strongsalt.strongdoc.sdk.proto.Billing;

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
    private String periodStart;
    //private Timestamps periodStart;
    /**
     * End of the requested billing period
     */
    private String periodEnd;
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

    // --------------------------- BillingDetailsResponse ---------------------------

    /**
     * Constructs a BillingDetailsResponse
     *
     * @param periodStart Start of the requested billing period
     * @param periodEnd   End of the requested billing period
     * @param totalCost   Total cost incurred during the requested billing period
     */
    public BillingDetailsResponse(final String periodStart,
                                  final String periodEnd,
                                  final double totalCost) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
        this.totalCost = totalCost;
    }

    /**
     * Set the response's usage and cost breakdown for stored documents
     *
     * @param cost Cost of document storage incurred during a billing period
     * @param size Size of documents stored during a billing period (in MBhours)
     * @param tier Cost tier reached for document storage during a billing period
     */
    public void setDocumentCosts(final double cost, final double size, final String tier) {
        this.documentCosts = new DocumentCosts(cost, size, tier);
    }

    /**
     * Set the response's usage and cost breakdown for stored search indices
     *
     * @param cost Cost of search index storage incurred during a billing period
     * @param size Size of search indices stored during a billing period (in MBhours)
     * @param tier Cost tier reached for search index storage during a billing period
     */
    public void setSearchCosts(final double cost, final double size, final String tier) {
        this.searchCosts = new SearchCosts(cost, size, tier);
    }

    /**
     * Set the response's usage and cost breakdown for used traffic
     *
     * @param cost     Cost of network traffic incurred during a billing period
     * @param incoming Size of incoming requests during a billing period (in MB)
     * @param outgoing Size of outgoing requests during a billing period (in MB)
     * @param tier     Cost tier reached for network traffic during a billing period
     */
    public void setTrafficCosts(final double cost, final double incoming, final double outgoing, final String tier) {
        this.trafficCosts = new TrafficCosts(cost, incoming, outgoing, tier);
    }

    /**
     * Set the response's billingFrequency Billing frequency used during the requested billing period
     *
     * @param frequency Billing frequency
     * @param validFrom Start of billing frequency validity
     * @param validTo   End of billing frequency validity
     */
    public void setBillingFrequency(final Billing.TimeInterval frequency, final Timestamp validFrom, final Timestamp validTo) {
        this.billingFrequency = new BillingFrequency(frequency, validFrom, validTo);
    }

    // --------------------------- DocumentCosts ---------------------------
    class DocumentCosts {
        /**
         * Cost of document storage incurred during a billing period
         */
        private double cost;
        /**
         * Size of documents stored during a billing period (in MBhours)
         */
        private double size;
        /**
         * Cost tier reached for document storage during a billing period
         */
        private String tier;

        /**
         * Constructs a DocumentCosts
         *
         * @param cost Cost of document storage incurred during a billing period
         * @param size Size of documents stored during a billing period (in MBhours)
         * @param tier Cost tier reached for document storage during a billing period
         */
        public DocumentCosts(final double cost, final double size, final String tier) {
            this.cost = cost;
            this.size = size;
            this.tier = tier;
        }
    }

    // --------------------------- SearchCosts ---------------------------
    class SearchCosts {
        /**
         * Cost of search index storage incurred during a billing period
         */
        private double cost;
        /**
         * Size of search indices stored during a billing period (in MBhours)
         */
        private double size;
        /**
         * Cost tier reached for search index storage during a billing period
         */
        private String tier;

        /**
         * Constructs a SearchCosts
         *
         * @param cost Cost of search index storage incurred during a billing period
         * @param size Size of search indices stored during a billing period (in MBhours)
         * @param tier Cost tier reached for search index storage during a billing period
         */
        public SearchCosts(final double cost, final double size, final String tier) {
            this.cost = cost;
            this.size = size;
            this.tier = tier;
        }
    }

    // --------------------------- TrafficCosts ---------------------------
    class TrafficCosts {
        /**
         * Cost of network traffic incurred during a billing period
         */
        private double cost;
        /**
         * Size of incoming requests during a billing period (in MB)
         */
        private double incoming;
        /**
         * Size of outgoing requests during a billing period (in MB)
         */
        private double outgoing;
        /**
         * Cost tier reached for network traffic during a billing period
         */
        private String tier;

        /**
         * Constructs a TrafficCosts
         *
         * @param cost     Cost of network traffic incurred during a billing period
         * @param incoming Size of incoming requests during a billing period (in MB)
         * @param outgoing Size of outgoing requests during a billing period (in MB)
         * @param tier     Cost tier reached for network traffic during a billing period
         */
        public TrafficCosts(final double cost, final double incoming, final double outgoing,
                            final String tier) {
            this.cost = cost;
            this.incoming = incoming;
            this.outgoing = outgoing;
            this.tier = tier;
        }
    }

    // --------------------------- BillingFrequency ---------------------------
    class BillingFrequency {
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
    }
}
