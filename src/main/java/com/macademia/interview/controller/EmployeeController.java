package com.macademia.interview.controller;

import com.macademia.interview.dto.CreateEmployeeRequest;
import com.macademia.interview.dto.EmployeeDto;
import com.macademia.interview.entity.Employee;
import com.macademia.interview.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping("/v1/employee-management")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value="/employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping(value="/employee")
    public ResponseEntity<EmployeeDto> createEmployee
            (@RequestBody @Valid CreateEmployeeRequest request) {

        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @DeleteMapping(value="/employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId){
        return ResponseEntity.ok(employeeService.deleteEmployee(employeeId));
    }

    @GetMapping(value = "/employee-with-criteria")
    public ResponseEntity<List<EmployeeDto>> getEmployeesWithCriteria
            (@RequestParam int specificDate, @RequestParam Double limitIncome){

        return ResponseEntity.ok(employeeService.getEmployeeWithCriteria(specificDate,limitIncome));

    }

    @PutMapping(value = "/employee/office")
    public ResponseEntity<?> updateOfficeLocation
            (@RequestParam String department, @RequestParam String officeLocation) {
        employeeService.updateOfficeLocation(department,officeLocation);
        return ResponseEntity.ok("ok");
    }


}
