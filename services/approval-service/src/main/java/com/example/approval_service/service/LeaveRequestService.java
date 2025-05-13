package com.example.approval_service.service;

import com.example.approval_service.model.*;
import com.example.approval_service.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveRequestService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private RCToEmployeeServiceService rcToEmployeeServiceService;

    @Autowired
    private RCToJWTServiceService rcToJWTServiceService;

    @Autowired
    private RCToLeaveBalanceServiceService rcToLeaveBalanceServiceService;

    public LeaveRequest create(String authorizationHeader, LeaveRequest leaveRequest) {
        leaveRequest.setStatus(RequestStatus.PENDING);
        leaveRequest.setCreate_date(LocalDate.now());
        System.out.println(leaveRequest);

        Integer employeeId = rcToJWTServiceService.getEmployeeId(authorizationHeader);

        if (employeeId == null) {
            System.out.println("Employee id not found");
            return null;
        } else {
            leaveRequest.setEmployee_id(employeeId);
        }


        LeaveType type = leaveRequest.getType();
        LocalDate startDate = leaveRequest.getStart_date();
        LocalDate endDate = leaveRequest.getEnd_date();

        if (startDate == null) {
            System.out.println("Ngày bắt đầu (startDate) là bắt buộc cho mọi loại nghỉ.");
            return null;
        }

        if (type == null) {
            System.out.println("Loại nghỉ (LeaveType) không được để trống.");
            return null;
        }

        if (type == LeaveType.ANNUAL_LEAVE || type == LeaveType.SICK_LEAVE || type == LeaveType.UNPAID_LEAVE) {
            if (endDate == null) {
                System.out.println("Loại nghỉ " + type + " yêu cầu ngày kết thúc (endDate).");
                return null;
            }

            if (endDate.isBefore(startDate)) {
                System.out.println("Ngày kết thúc không thể trước ngày bắt đầu.");
                return null;
            }

            int requestedDays = (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
            if (requestedDays > rcToLeaveBalanceServiceService.getBalance(employeeId)) {
                System.out.println("Vượt quá số ngày nghỉ cho phép.");
                return null;
            }

        } else if (type == LeaveType.MATERNITY_LEAVE) {
            // end_date có thể null đối với nghỉ thai sản
            if (endDate != null &&
                    endDate.isBefore(startDate)) {
                System.out.println("Ngày kết thúc không thể trước ngày bắt đầu.");
                return null;
            }

            // Tự động thiết lập ngày kết thúc nghỉ thai sản sau 6 tháng.
            System.out.println("Tự động thiết lập ngày kết thúc nghỉ thai sản sau 6 tháng.");
            endDate = startDate.plusMonths(6);
        } else {
            System.out.println("Loại nghỉ không hợp lệ.");
            return null;
        }

        leaveRequest.setEnd_date(endDate);
        System.out.println(leaveRequest);
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> readListByEmployeeId(String authorizationHeader) {
        Integer employeeId = rcToJWTServiceService.getEmployeeId(authorizationHeader);
        if (employeeId == null){
            System.out.println("Employee id not found");
            return null;
        }

        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    public List<LeaveRequest> readPendingListByListManagerId(String authorizationHeader) {
        Integer managerId = rcToJWTServiceService.getManagerId(authorizationHeader);
        if (managerId == null){
            System.out.println("Manager id not found");
            return null;
        }

        // Get Employee's id List By Manager's id
        List<Integer> list = rcToEmployeeServiceService.getEmployeeIdsByManagerId(managerId);

        return leaveRequestRepository.findPendingRequestsByEmployeeIds(list);
    }

    public Integer updateLeaveRequestStatus(
            String authorizationHeader,
            Integer requestId,
            RequestStatus status,
            String comment
    ) {
        Integer managerId = rcToJWTServiceService.getManagerId(authorizationHeader);
        if (managerId == null){
            System.out.println("Manager id not found");
            return null;
        }

        int flag = leaveRequestRepository.updateLeaveRequest(requestId, managerId, status.name(), comment);
        if (flag == 0){
            return 0;
        }

        if (status == RequestStatus.APPROVED && flag == 1) {
            LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId).orElse(null);
            if (leaveRequest == null) {
                return 0;
            }

            Integer employeeId = leaveRequest.getEmployee_id();
            Integer oldBalance = rcToLeaveBalanceServiceService.getBalance(employeeId);
            int requestedDays = (int) ChronoUnit.DAYS.between(leaveRequest.getCreate_date(), leaveRequest.getEnd_date()) + 1;
            Integer newBalance = oldBalance - requestedDays;
            if (requestedDays > oldBalance) {
                return 0;
            } else {
                return rcToLeaveBalanceServiceService.updateBalance(employeeId, newBalance);
            }

        }
        return flag;
    }
}
