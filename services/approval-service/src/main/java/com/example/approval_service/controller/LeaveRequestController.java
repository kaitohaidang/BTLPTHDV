package com.example.approval_service.controller;

import com.example.approval_service.dto.LeaveRequestUpdateData;
import com.example.approval_service.model.LeaveRequest;
import com.example.approval_service.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/request")
public class LeaveRequestController {

    @Autowired
    LeaveRequestService leaveRequestService;

    @PostMapping
    public LeaveRequest addLeaveRequest(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody LeaveRequest leaveRequest) {

        return leaveRequestService.create(authorizationHeader, leaveRequest);
    }

    @GetMapping("/getMyLeaveRequest")
    public List<LeaveRequest> findLeaveRequestsByEmployeeId(
            @RequestHeader("Authorization") String authorizationHeader) {

        return leaveRequestService.readListByEmployeeId(authorizationHeader);
    }

    @GetMapping("/getMyEmployeesPendingLeaveRequest")
    public List<LeaveRequest> findPendingLeaveRequests(
            @RequestHeader("Authorization") String authorizationHeader) {
        return leaveRequestService.readPendingListByListManagerId(authorizationHeader);
    }

    @PostMapping("/updatePendingLeaveRequest/{requestId}")
    public Integer updateLeaveRequest(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Integer requestId,
            @RequestBody LeaveRequestUpdateData leaveRequestUpdateData
    ) {

        return leaveRequestService.updateLeaveRequestStatus(
                authorizationHeader,
                requestId,
                leaveRequestUpdateData.getStatus(),
                leaveRequestUpdateData.getComment()
        );
    }
}
