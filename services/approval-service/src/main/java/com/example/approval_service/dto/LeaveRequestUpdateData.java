package com.example.approval_service.dto;

import com.example.approval_service.model.RequestStatus;

public class LeaveRequestUpdateData {

    private RequestStatus status;

    private String comment;

    public LeaveRequestUpdateData() {
    }

    public LeaveRequestUpdateData(RequestStatus status, String comment) {
        this.status = status;
        this.comment = comment;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "LeaveRequestUpdateData{" +
                "status=" + status +
                ", comment='" + comment + '\'' +
                '}';
    }
}
