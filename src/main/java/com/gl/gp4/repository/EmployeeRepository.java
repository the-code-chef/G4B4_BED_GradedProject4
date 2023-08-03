package com.gl.gp4.repository;

import com.gl.gp4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> getEmployeesByFirstName(String firstName);
    
    List<Employee> getAllEmployeesSortedByFirstNameAsc();

    List<Employee> getAllEmployeesSortedByFirstNameDesc();
}
