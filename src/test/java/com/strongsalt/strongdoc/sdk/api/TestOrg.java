package com.strongsalt.strongdoc.sdk.api;

public class TestOrg {
    public String orgName;
    public String orgEmail;
    public String orgAddr;
    public String orgID;

//    public TestOrg(String orgName, String orgEmail, String orgAddr) {
//        this.orgName = orgName;
//        this.orgEmail = orgEmail;
//        this.orgAddr = orgAddr;
//    }

    public static class Builder {
        public String orgName;
        public String orgEmail;
        public String orgAddr;
        public String orgID;

        public Builder() {
        }

        public Builder setOrgName(String orgName) {
            this.orgName = orgName;
            return this;
        }

        public Builder setOrgAddr(String orgAddr) {
            this.orgAddr = orgAddr;
            return this;
        }

        public Builder setOrgEmail(String orgEmail) {
            this.orgEmail = orgEmail;
            return this;
        }

        public Builder setOrgID(String orgID) {
            this.orgID = orgID;
            return this;
        }

        public TestOrg build() {
            return new TestOrg(this);
        }

    }

    private TestOrg(Builder builder) {
        this.orgAddr = builder.orgAddr;
        this.orgEmail = builder.orgEmail;
        this.orgName = builder.orgName;
        this.orgID = builder.orgID;
    }
}
