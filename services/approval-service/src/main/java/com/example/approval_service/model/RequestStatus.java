package com.example.approval_service.model;

public enum RequestStatus {
    PENDING("Chờ duyệt"),
    APPROVED("Đã chấp nhận"),
    REJECTED("Đã từ chối");

    public static RequestStatus fromString(String value) {
        try {
            return RequestStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    private final String description;

    RequestStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}