package com.macademia.interview.services;

import com.macademia.interview.dto.CreateEmployeeRequest;
import com.macademia.interview.dto.EmployeeDto;
import com.macademia.interview.dto.EmployeeDtoConverter;
import com.macademia.interview.entity.Employee;
import com.macademia.interview.exceptions.EmployeeNotFoundException;
import com.macademia.interview.repository.IEmployeeRepository;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class EmployeeService {

    private final IEmployeeRepository employeeRepository;
    private final EmployeeDtoConverter employeeDtoConverter;

    public EmployeeService(IEmployeeRepository employeeRepository,
                           EmployeeDtoConverter employeeDtoConverter) {
        this.employeeRepository = employeeRepository;
        this.employeeDtoConverter = employeeDtoConverter;
    }

    public Employee findEmployeeById(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException("Employee could have not found by id " + id));
    }

    public EmployeeDto createEmployee(CreateEmployeeRequest createEmployeeRequest){

        Employee employee = new Employee(
                createEmployeeRequest.getEmployeeId(),
                createEmployeeRequest.getName(),
                createEmployeeRequest.getSurname(),
                createEmployeeRequest.getStartedDate(),
                createEmployeeRequest.getDepartment(),
                createEmployeeRequest.getOfficeLocation(),
                createEmployeeRequest.getIncome()
        );

        return employeeDtoConverter.convert(employeeRepository.save(employee));
    }

    public List<EmployeeDto> getAllEmployees(){
        return employeeDtoConverter.convert(employeeRepository.findAll());
    }


    public String deleteEmployee(String employeeId){
        Employee employee = findEmployeeById(employeeId);
        employeeRepository.delete(employee);
        return employeeId;
    }

    public List<EmployeeDto> getEmployeeWithCriteria(int specificDate, Double limitIncome){
        return employeeDtoConverter.convert(employeeRepository.getEmployeesWithCriteria(specificDate,limitIncome));
    }

    public void updateOfficeLocation(String department, String officeLocation){
        employeeRepository.updateOfficeLocation(department,officeLocation);
    }

}
