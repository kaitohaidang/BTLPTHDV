package com.example.approval_service.model;

public enum LeaveType {
    ANNUAL_LEAVE("Nghỉ phép năm"),
    SICK_LEAVE("Nghỉ ốm"),
    MATERNITY_LEAVE("Nghỉ thai sản"),
    PATERNITY_LEAVE("Nghỉ thai sản cho nam"),
    PERSONAL_LEAVE("Nghỉ việc riêng"),
    UNPAID_LEAVE("Nghỉ không lương"),
    PUBLIC_HOLIDAY("Nghỉ lễ, Tết"),
    SABBATICAL("Nghỉ dài hạn");

    public static LeaveType fromString(String value) {
        try {
            return LeaveType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    private final String description;

    LeaveType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

