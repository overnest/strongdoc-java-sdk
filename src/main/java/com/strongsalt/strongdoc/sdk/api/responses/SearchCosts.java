/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

/**
 * This class holds the usage and cost breakdown for stored documents
 */
public class SearchCosts {
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

    /**
     * Gets the cost Cost of search index storage incurred during a billing period
     */
    public double getCost() {
        return cost;
    }

    /**
     * Gets the size of search indices stored during a billing period (in MBhours)
     */
    public double getSize() {
        return size;
    }

    /**
     * Gets the cost tier reached for search index storage during a billing period
     */
    public String getTier() {
        return tier;
    }
}
