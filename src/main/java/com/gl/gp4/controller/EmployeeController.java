package com.gl.gp4.controller;

import com.gl.gp4.entity.Employee;
import com.gl.gp4.service.employee.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    public EmployeeController(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = null;
        updatedEmployee = employeeService.updateEmployee(id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{firstName}")
    public List<Employee> getEmployeesByFirstName(@PathVariable String firstName) {
        return employeeService.getEmployeesByFirstName(firstName);
    }

    @GetMapping("/sort")
    public List<Employee> getAllEmployeesSorted(@RequestParam(defaultValue = "asc") String order) {
        if ("asc".equalsIgnoreCase(order)) {
            return employeeService.getAllEmployeesSortedByFirstNameAsc();
        } else if ("desc".equalsIgnoreCase(order)) {
            return employeeService.getAllEmployeesSortedByFirstNameDesc();
        } else {
            throw new IllegalArgumentException("Invalid sorting order. Use 'asc' or 'desc'.");
        }
    }
}
