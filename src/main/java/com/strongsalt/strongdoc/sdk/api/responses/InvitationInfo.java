package com.strongsalt.strongdoc.sdk.api.responses;

import com.google.protobuf.Timestamp;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class InvitationInfo {
    private String email;
    private Timestamp createdAt;
    private Timestamp expiredAt;

    public InvitationInfo(String email, Timestamp createdAt, Timestamp expiredAt) {
        this.email = email;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;


    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedAtTimestamp() {
        return this.createdAt;
    }

    public LocalDateTime getCreatedAtLocalTime(String zoneID) {
        return protoTimestampToLocalDateTime(this.createdAt, zoneID);
    }

    public Timestamp getExpiredAtTimestamp() {
        return this.expiredAt;
    }

    public LocalDateTime getExpiredAtLocalTime(String zoneID) {
        return protoTimestampToLocalDateTime(this.expiredAt, zoneID);
    }

    private LocalDateTime protoTimestampToLocalDateTime(Timestamp ts, String zoneID) {
        LocalDateTime time = Instant.ofEpochSecond(ts.getSeconds(), ts.getNanos())
                .atZone(ZoneId.of(zoneID))
                .toLocalDateTime();
        return time;
    }
}
