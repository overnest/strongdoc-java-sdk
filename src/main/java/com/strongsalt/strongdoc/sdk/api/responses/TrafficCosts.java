/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

/**
 * This class holds the usage and cost breakdown for used traffic
 */
public class TrafficCosts {
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

    /**
     * Gets the cost of network traffic incurred during a billing period.
     *
     * @return The cost of network traffic
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the size of incoming requests during a billing period (in MB).
     *
     * @return The size of incoming network traffic
     */
    public double getIncoming() {
        return incoming;
    }

    /**
     * Gets the size of outgoing requests during a billing period (in MB).
     *
     * @return The size of outgoing network traffic
     */
    public double getOutgoing() {
        return outgoing;
    }

    /**
     * Gets the cost tier reached for network traffic during a billing period.
     *
     * @return The cost tier of the network traffic
     */
    public String getTier() {
        return tier;
    }
}
