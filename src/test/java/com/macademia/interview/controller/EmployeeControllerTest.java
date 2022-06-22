package com.macademia.interview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.macademia.interview.dto.CreateEmployeeRequest;
import com.macademia.interview.dto.EmployeeDtoConverter;
import com.macademia.interview.entity.Employee;
import com.macademia.interview.repository.IEmployeeRepository;
import com.macademia.interview.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.Clock;
import java.util.UUID;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private Clock clock;

    @MockBean
    private Supplier<UUID> uuidSupplier;

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDtoConverter converter;

    private ObjectMapper mapper = new ObjectMapper();

    private static final UUID uuid = UUID.randomUUID();

    @BeforeEach
    public void setUp() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void testCreateEmployee() throws Exception {
        Employee argEmployee = new Employee("ali","çınar","Technology",1250.16);

        Employee employee = employeeRepository.save(argEmployee);

        CreateEmployeeRequest request = new CreateEmployeeRequest(employee.getId(),
                employee.getName(), employee.getSurname(), employee.getDepartment(),
                employee.getStartedDate(), employee.getOfficeLocation(), employee.getIncome());

        this.mockMvc.perform(post("/v1/employee-management/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(request)))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testGetAllEmployees() throws Exception {
        this.mockMvc.perform(get("/v1/employee-management/employee")).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testGetAllEmployeesWithCriteria() throws Exception {
        this.mockMvc.perform(
                get("/v1/employee-management/employee-with-criteria?specificDate={date}&limitIncome={income}",
                        2015,618.52))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateOfficeLocations() throws Exception {
        this.mockMvc.perform(put("/v1/employee-management/employee/office?department={department}&officeLocation={location}"
                        ,"IT","Istanbul")).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }




}