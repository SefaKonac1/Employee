package com.macademia.interview.dto;

import com.macademia.interview.entity.Employee;

public class EmployeeConverter {

    public EmployeeDto convert(Employee emp) {

        return new EmployeeDto(emp.getId(), emp.getName(),
                emp.getSurname(), emp.getStartedDate(),
                emp.getDepartment(), emp.getOfficeLocation(), emp.getPersonalInfo() );

    }

}
