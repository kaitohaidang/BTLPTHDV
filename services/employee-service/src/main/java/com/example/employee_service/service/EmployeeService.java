package com.example.employee_service.service;

import com.example.employee_service.model.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RCToPasswordServiceService passwordClientService;

    @Autowired
    private RCToJWTServiceService jwtClientService;

    public Employee createEmployee(Employee employee) {
        employee.setPassword(passwordClientService.createPassword(employee.getPassword()));
        System.out.println(employee.getIs_manager());
        return employeeRepository.save(employee);
    }

    public String getJWT(String username, String password) {
        Optional<Employee> employee = employeeRepository.findByUsername(username);

        if(employee.isEmpty()) {
            return null;
        } else {
            Employee e = employee.get();
            if (!passwordClientService.checkPassword(password, e.getPassword())){
                return null;
            } else {
                return jwtClientService.getJWT(e.getId(), e.getName(), e.getIs_manager());
            }
        }
    }

    public List<Integer> readListEmployeeIdByManagerId(Integer managerId) {
        return employeeRepository.findIdsByManagerId(managerId);
    }

    public Employee read(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }
}
