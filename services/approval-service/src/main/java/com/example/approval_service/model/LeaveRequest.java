package com.example.approval_service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "leave_request")
public class LeaveRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer employee_id;

    private Integer manager_id;

    @Column(nullable = false)
    private LocalDate create_date;

    @Column(nullable = false)
    private LocalDate start_date;

    private LocalDate end_date;

    @Column(nullable = false)
    private LeaveType type;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private RequestStatus status; // PENDING, APPROVED, REJECTED

    private String comments;

    public LeaveRequest() {
    }

    public LeaveRequest(
            Integer employee_id, Integer manager_id, LocalDate create_date,
            LocalDate start_date, LocalDate end_date, LeaveType type, String detail,
            RequestStatus status, String comments
    ) {
        this.employee_id = employee_id;
        this.manager_id = manager_id;
        this.create_date = create_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
        this.detail = detail;
        this.status = status;
        this.comments = comments;
    }

    public LeaveRequest(
            Integer id, Integer employee_id, Integer manager_id,
            LocalDate create_date, LocalDate start_date, LocalDate end_date,
            LeaveType type, String detail, RequestStatus status, String comments
    ) {
        this.id = id;
        this.employee_id = employee_id;
        this.manager_id = manager_id;
        this.create_date = create_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
        this.detail = detail;
        this.status = status;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    public LocalDate getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDate create_date) {
        this.create_date = create_date;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "id=" + id +
                ", employee_id=" + employee_id +
                ", manager_id=" + manager_id +
                ", create_date=" + create_date +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", type=" + type +
                ", detail='" + detail + '\'' +
                ", status=" + status +
                ", comments='" + comments + '\'' +
                '}';
    }
}
