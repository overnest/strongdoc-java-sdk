/*
 * All Rights Reserved 2020
 */

package com.strongsalt.strongdoc.sdk.api.responses;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class holds the response from a billing details request.
 */
public class AccountInfoResponse {
    /**
     * Account's orgID
     */
    private String orgID;
    /**
     * Account's organization email address
     */
    private String orgEmail;
    /**
     * Account's organization address
     */
    private String orgAddress;
    /**
     * Account's subscription info
     */
    private Subscription subscription;
    /**
     * List of all account's payments
     */
    private ArrayList<Payment> paymentList;
    /**
     * Whether multi-level sharing is enabled
     */
    private boolean multiLevelSharing;
    /**
     * List of all account's sharable orgs
     */
    private ArrayList<String> sharableOrgList;

    // --------------------------- AccountInfoResponse ---------------------------

    /**
     * Constructs a AccountInfoResponse
     */
    public AccountInfoResponse(String orgID, String orgEmail, String orgAddress, boolean multiLevelSharing) {
        this.orgID = orgID;
        this.orgEmail = orgEmail;
        this.orgAddress = orgAddress;
        this.paymentList = new ArrayList<Payment>();
        this.multiLevelSharing = multiLevelSharing;
        this.sharableOrgList = new ArrayList<String>();
    }

    /**
     * Gets the orgID from response
     *
     * @return The orgID
     */
    public String getOrgID() {
        return this.orgID;
    }

    /**
     * Gets the organization email address from response
     *
     * @return The org email address
     */
    public String getOrgEmail() {
        return this.orgEmail;
    }

    /**
     * Gets the organization's address from response
     *
     * @return The org address
     */
    public String getOrgAddress() {
        return this.orgAddress;
    }

    /**
     * Sets the response's subscription
     *
     * @param type   The type of subscription (AWS Marketplace, Credit Card, etc.)
     * @param status The state of the subscription (Created, Subscribed, Unsubscribed, etc.)
     */
    public void setSubscription(final String type, final String status) {
        this.subscription = new Subscription(type, status);
    }

    /**
     * Gets the subscription from response
     *
     * @return The subscription
     */
    public Subscription getSubscription() {
        return this.subscription;
    }

    /**
     * Adds a payment to the response's payment list
     *
     * @param billedAt    The timestamp of the payment billing transaction
     * @param periodStart The start of the payment period
     * @param periodEnd   The end of the payment period
     * @param amount      The amount of payment
     * @param status      The Payment status ("No Payment","Zero Payment","Payment Pending","Payment Success","Payment Failed")
     */
    public void addPayment(final Date billedAt, final Date periodStart,
                           final Date periodEnd, final double amount,
                           final String status) {
        this.paymentList.add(new Payment(billedAt, periodStart, periodEnd, amount, status));
    }

    /**
     * Gets the account's payments
     */
    public ArrayList<Payment> getPayments() {
        return this.paymentList;
    }

    /**
     * Gets whether multi-level sharing is enabled
     *
     * @return Whether multi-level sharing is enabled
     */
    public boolean getMultiLevelSharing() {
        return this.multiLevelSharing;
    }

    /**
     * Adds a payment to the response's payment list
     *
     * @param sharableOrg    The orgID of the sharable org
     */
    public void addSharableOrg(final String sharableOrg) {
        this.sharableOrgList.add(sharableOrg);
    }

    /**
     * Gets the account's sharable orgs
     */
    public ArrayList<String> getSharableOrgs() {
        return this.sharableOrgList;
    }
}
