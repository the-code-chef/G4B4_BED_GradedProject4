package com.gl.gp4.service.employee;

import com.gl.gp4.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    
    List<Employee> getAllEmployees();
    
    Optional<Employee> getEmployeeById(long id);
    
    Employee updateEmployee(long id, Employee employee);
    
    void deleteEmployee(long id);

    List<Employee> getEmployeesByFirstName(String firstName);

    List<Employee> getAllEmployeesSortedByFirstNameAsc();

    List<Employee> getAllEmployeesSortedByFirstNameDesc();
}