package com.example.approval_service.repository;

import com.example.approval_service.model.LeaveRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Integer> {

    @Query(value = """
        SELECT * FROM leave_request 
        WHERE employee_id IN (:employeeIds) 
        AND status = 'PENDING'
        """, nativeQuery = true)
    List<LeaveRequest> findPendingRequestsByEmployeeIds(@Param("employeeIds") List<Integer> employeeIds);

    @Query(value = """
        SELECT * FROM leave_request 
        WHERE employee_id = :employeeId
    """, nativeQuery = true)
    List<LeaveRequest> findByEmployeeId(@Param("employeeId") Integer employeeId);


    @Transactional
    @Modifying
    @Query(value = """
        UPDATE leave_request
        SET manager_id = :managerId, status = :status, comments = :comments
        WHERE id = :requestId
        AND manager_id IS NULL
        """, nativeQuery = true)
    int updateLeaveRequest(
            @Param("requestId") Integer requestId,
            @Param("managerId") Integer managerId,
            @Param("status") String status,
            @Param("comments") String comments
    );
}
