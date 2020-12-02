package com.strongsalt.strongdoc.sdk.api;

public class TestData {
    public TestOrg[] testOrgs;
    public TestUser[][] testUsers;

    public static class Builder {
        private TestOrg[] testOrgs;
        private TestUser[][] testUsers;

        public Builder() {
        }

        public Builder setTestOrgs(TestOrg[] testOrgs) {
            this.testOrgs = testOrgs;
            return this;
        }

        public Builder setTestUsers(TestUser[][] testUsers) {
            this.testUsers = testUsers;
            return this;
        }

        public TestData build() {
            return new TestData(this);
        }
    }

    private TestData(TestData.Builder builder) {
        this.testOrgs = builder.testOrgs;
        this.testUsers = builder.testUsers;
    }
}
