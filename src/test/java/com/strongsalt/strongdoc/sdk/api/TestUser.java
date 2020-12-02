package com.strongsalt.strongdoc.sdk.api;

public class TestUser {
    public String userName;
    public String userEmail;
    public String password;
    public String userID;

//    public TestUser(String userName, String userEmail, String password) {
//        this.userName = userName;
//        this.userEmail = userEmail;
//        this.password = password;
//    }

    public static class Builder {
        public String userName;
        public String userEmail;
        public String password;
        public String userID;


        public Builder() {
        }

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setUserEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public TestUser build() {
            return new TestUser(this);
        }

    }

    private TestUser(Builder builder) {
        this.userName = builder.userName;
        this.userEmail = builder.userEmail;
        this.password = builder.password;
        this.userID = builder.userID;
    }
}
