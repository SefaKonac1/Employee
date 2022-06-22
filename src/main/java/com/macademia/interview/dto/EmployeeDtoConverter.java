package com.macademia.interview.dto;

import com.macademia.interview.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDtoConverter {

    public EmployeeDto convert(Employee emp) {

        return new EmployeeDto(emp.getId(), emp.getName(),
                emp.getSurname(), emp.getStartedDate(),
                emp.getDepartment(), emp.getOfficeLocation(), emp.getIncome());

    }

    public List<EmployeeDto> convert(List<Employee> employeeList){

        return employeeList.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
