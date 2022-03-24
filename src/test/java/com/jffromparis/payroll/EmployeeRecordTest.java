package com.jffromparis.payroll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.platform.runner.JUnitPlatform;
import static org.hamcrest.MatcherAssert.assertThat;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import payroll.controller.EmployeeController;
import payroll.model.Employee;
import payroll.repository.EmployeeRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

/**
 * @Author jffromparis
 * @email jeanfrancois.lepante@gmail.com
 * @since 03/12/2022
 */

@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class EmployeeRecordTest {
        @Autowired
        MockMvc mockMvc;

        @Autowired
        private ObjectMapper mapper;

        @MockBean
        private EmployeeRepository employeeRepository;



        // Testing the creation on an employee
        @Test
        public void testAddEmployee() throws Exception {
                Employee employee1 = new Employee((long) 1, "Louis", "martin", "louis@martin.fr", "dsi", (float) 30000.00,
                (float) 600000.00, "sales manager", LocalDate.now());

                Mockito.when(employeeRepository.save(employee1)).thenReturn(employee1);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/employees")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(employee1));

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.lastname", is("Capet")));
        }

        // Testing the retrival of all the employees recorded
        @Test
        public void testGetAllRecords_success() throws Exception {

                List<Employee> employees = new ArrayList<>();
                employees.add(new Employee((long) 1, "Louis", "martin", "louis@martin.fr", "dsi", (float) 30000.00,
                                (float) 600000.00, "sales manager", LocalDate.now()));
                employees.add(new Employee((long) 2, "Jeanne", "Bertrand", "jeanne@bertrand.fr", "usine1",
                                (float) 25000.00,
                                (float) 6000.00, "chef d'équipe", LocalDate.now()));
                employees.add(new Employee((long) 3, "Paul", "Auguste", "paul@auguste.fr", "technique",
                                (float) 20000.00,
                                (float) 600.00, "gardien", LocalDate.now()));

                when(employeeRepository.findAll()).thenReturn(employees);
                MvcResult mvcResult  = mockMvc.perform(MockMvcRequestBuilders
                                .get("/employees")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andReturn();
                String actualJsonResponse = mvcResult.getResponse().getContentAsString();
                System.out.println(actualJsonResponse);

                String expectedJsonResponse = mapper.writeValueAsString(employees);
                assertThat(actualJsonResponse, equalTo(expectedJsonResponse));

        }

        // Testing the retrival of an employee by id
        @Test
        public void testGetRecordById() throws Exception {
                Employee employee1 = new Employee((long) 1, "Louis", "martin", "louis@martin.fr", "dsi", (float) 30000.00,
                (float) 600000.00, "sales manager", LocalDate.now());
                Mockito.when(employeeRepository.findById(employee1.getId()))
                                .thenReturn(java.util.Optional.of(employee1));

                mockMvc.perform(MockMvcRequestBuilders
                                .get("/employees/1")
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.lastname", is("Capet")));
        }

        // Testing the retrival of an employee by id
        @Test
        public void testUpdateEmployeeRecord_success() throws Exception {
                Employee employee1 = new Employee((long) 1, "Louis", "martin", "louis@martin.fr", "dsi", (float) 30000.00,
                (float) 600000.00, "sales manager", LocalDate.now());
                Employee newEmployee1 = new Employee(null, "François I", "Angoulème", "francoisI@capet.fr", "roi", null,
                                null, null, null);

                Mockito.when(employeeRepository.findById(employee1.getId()))
                                .thenReturn(java.util.Optional.of(employee1));
                Mockito.when(employeeRepository.save(newEmployee1)).thenReturn(newEmployee1);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/employees/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(newEmployee1));

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.lastname", is("merové")));
        }

}
