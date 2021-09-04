package com.example.jugalbeats.enums;

public enum BookingStatus {
    ACCEPTED("Accepted"),
    DECLINED("Declined"),
    PENDING("Pending");

    private String value;

    public String getValue() {
        return value;
    }

    private BookingStatus(String value) {
        this.value = value;
    }
}
