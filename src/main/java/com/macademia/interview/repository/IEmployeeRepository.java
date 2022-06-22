package com.macademia.interview.repository;

import com.macademia.interview.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IEmployeeRepository extends JpaRepository<Employee,String> {

    @Modifying
    @Query(value = "SELECT * FROM EMPLOYEE e WHERE e.STARTED_DATE > :startedDate AND e.INCOME > :limitIncome", nativeQuery = true)
    List<Employee> getEmployeesWithCriteria(int startedDate, Double limitIncome);

    @Transactional
    @Modifying
    @Query(value = "UPDATE EMPLOYEE e SET e.OFFICE_LOCATION = :officeLocation WHERE e.DEPARTMENT = :department", nativeQuery = true)
    void updateOfficeLocation(String department, String officeLocation);

}
