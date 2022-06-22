package com.macademia.interview.services;

import com.macademia.interview.dto.CreateEmployeeRequest;
import com.macademia.interview.dto.EmployeeDto;
import com.macademia.interview.dto.EmployeeDtoConverter;
import com.macademia.interview.entity.Employee;
import com.macademia.interview.exceptions.EmployeeNotFoundException;
import com.macademia.interview.repository.IEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    private EmployeeService service;
    private IEmployeeRepository employeeRepository;
    private EmployeeDtoConverter converter;

    private static final String  DUMMY_LITERAL = "dummy";
    private static final Double  DUMMY_DOUBLE = 10.0;
    private static final String  DUMMY_ID = "ID";
    private static final int  DUMMY_INT = 2000;

    @BeforeEach
    public void setUp(){
        employeeRepository = mock(IEmployeeRepository.class);
        converter = mock(EmployeeDtoConverter.class);
        service = new EmployeeService(employeeRepository,converter);
    }

    @Test
    public void testFindEmployeeById_whenEmployeeExist_shouldReturnEmployee() {
        Employee employee = new Employee(DUMMY_ID,DUMMY_LITERAL,DUMMY_LITERAL,
                DUMMY_INT,DUMMY_LITERAL,DUMMY_LITERAL,DUMMY_DOUBLE);

        Mockito.when(employeeRepository.findById(DUMMY_ID)).thenReturn(Optional.of(employee));

        Employee result = service.findEmployeeById(DUMMY_ID);

        assertEquals(result,employee);

    }

    @Test
    public void testFindEmployeeById_whenEmployeeNoExist_shouldThrowException() {

        Mockito.when(employeeRepository.findById(DUMMY_ID)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, ()-> service.findEmployeeById(DUMMY_ID));

    }

    @Test
    public void testGetAllEmployees_whenEmployeesExist_shouldReturnEmployeeList_orReturnEmptyList() {
        Employee employee = new Employee(DUMMY_ID,DUMMY_LITERAL,DUMMY_LITERAL,
                DUMMY_INT,DUMMY_LITERAL,DUMMY_LITERAL,DUMMY_DOUBLE);

        List<Employee> employeeList = new LinkedList<>();

        employeeList.add(employee);
        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        List<EmployeeDto> resultList = service.getAllEmployees();

        assertEquals(resultList,converter.convert(employeeList));
    }

    @Test
    public void testCreateEmployee_whenEmployeeCreated_shouldReturnEmployeeDto() {
        CreateEmployeeRequest request = new CreateEmployeeRequest(DUMMY_ID,DUMMY_LITERAL,DUMMY_LITERAL,
                DUMMY_LITERAL,DUMMY_INT,DUMMY_LITERAL,DUMMY_DOUBLE);

        Employee employee = new Employee(DUMMY_ID,DUMMY_LITERAL,DUMMY_LITERAL,
                DUMMY_INT,DUMMY_LITERAL,DUMMY_LITERAL,DUMMY_DOUBLE);

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);


        EmployeeDto result = service.createEmployee(request);

        assertEquals(result,converter.convert(employee));
    }

    @Test
    public void testDeleteEmployeeById_whenEmployeeNonExist_shouldThrowException() {

        assertThrows(EmployeeNotFoundException.class, ()-> service.findEmployeeById(DUMMY_ID));
    }

    @Test
    public void testGetEmployeeWithCriteria_whenCriteriaProvided_shouldReturnEmployees() {
        Employee employee = new Employee(DUMMY_ID,DUMMY_LITERAL,DUMMY_LITERAL,
                DUMMY_INT,DUMMY_LITERAL,DUMMY_LITERAL,DUMMY_DOUBLE);

        List<Employee> employeeList = new LinkedList<>();

        employeeList.add(employee);

        Mockito.when(employeeRepository.getEmployeesWithCriteria(DUMMY_INT,DUMMY_DOUBLE)).thenReturn(employeeList);

        List<EmployeeDto> employeeDtoList = service.getEmployeeWithCriteria(DUMMY_INT,DUMMY_DOUBLE);

        assertEquals(employeeDtoList,converter.convert(employeeList));

    }

}