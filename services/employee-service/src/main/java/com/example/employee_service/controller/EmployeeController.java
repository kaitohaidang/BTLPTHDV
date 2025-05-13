package com.example.employee_service.controller;

import com.example.employee_service.dto.LoginData;
import com.example.employee_service.model.Employee;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Employee register(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.createEmployee(employee);
        newEmployee.setPassword("password");
        System.out.println(newEmployee.getIs_manager());
        return newEmployee;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginData employee) {
        return employeeService.getJWT(employee.getUsername(), employee.getPassword());
    }

    @GetMapping("/getEmployeeEmail/{employeeId}")
    public String getEmployeeEmailById(@PathVariable Integer employeeId) {
        Employee employee = employeeService.read(employeeId);
        if (employee == null) {
            System.out.println("Employee not found");
            return null;
        }

        return employee.getEmail();
    }

    @GetMapping("/getListEmployeeIds/{managerId}")
    public List<Integer> getEmployeeByManagerId(@PathVariable Integer managerId) {
        return employeeService.readListEmployeeIdByManagerId(managerId);
    }

}
