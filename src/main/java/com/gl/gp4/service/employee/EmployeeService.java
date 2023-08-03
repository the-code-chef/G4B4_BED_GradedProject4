package com.gl.gp4.service.employee;

import com.gl.gp4.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    
    List<Employee> getAllEmployees();
    
    Employee getEmployeeById(long id);
    
    Employee updateEmployee(long id, Employee employee);
    
    void deleteEmployee(long id);
}