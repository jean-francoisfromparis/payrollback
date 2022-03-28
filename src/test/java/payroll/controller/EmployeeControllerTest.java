package payroll.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

// import org.junit.Assert;
import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import payroll.model.Employee;
import payroll.repository.EmployeeRepository;

// import payroll.model.Employee;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

        @MockBean
        private EmployeeRepository employeeRepository;

        private Optional<Employee> employee;

        @Autowired
        private ObjectMapper mapper;

        @Autowired
        private MockMvc mockMvc;

        @SuppressWarnings( "deprecation" )
        @Test
        public void testListAllEmployees() throws Exception {
                String url = "/employees";
                int databaseSize = employeeRepository.findAll().size();

                System.out.println("database size is :" + databaseSize);

                mockMvc.perform(get(url)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());

                assertThat(databaseSize, is(1000));
        }

        @SuppressWarnings( "deprecation" )
        @Test
        public void testListAllEmployees_ThenListSizeUncorrect() throws Exception {
                String url = "/employees";
                int databaseSize = employeeRepository.findAll().size();
                mockMvc.perform(get(url)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk());

                assertThat(databaseSize, is(not(950)));

        }

        @Test
        public void whenPostRequestToEmployeesAndValidEmployees_thenCorrectResponse() throws Exception {
                Employee employee = new Employee(
                                (long) 1001,
                                "Paul",
                                "Auguste",
                                "paul@auguste.fr",
                                "technique",
                                (float) 20000.00,
                                (float) 600.00,
                                "gardien",
                                LocalDate.now());

                String url = "/employees";

                Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

                String content = mapper.writeValueAsString(employee);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", notNullValue()))
                                .andExpect(jsonPath("$.firstname", is("Paul")));
        }

        @Test
        public void whenPostRequestToEmployeesAndInvalidEmployee_thenCorrectResponse() throws Exception {
                Employee employee = new Employee(
                                (long) 1001,
                                "",
                                "Auguste",
                                "paul@auguste.fr",
                                "technique",
                                (float) 20000.00,
                                (float) 600.00,
                                "gardien",
                                LocalDate.now());

                String url = "/employees";

                Mockito.when(employeeRepository.save(employee)).thenReturn(employee);

                String content = mapper.writeValueAsString(employee);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest)
                                .andExpect(status().isBadRequest());
        }

        @Test
        public void getTestEmployeeById_thenCorrectResponse() throws Exception {

                String url = "/employeebyId/{1}";

                Mockito.when(employeeRepository.findById((long) 1)).thenReturn(employee);

                String content = mapper.writeValueAsString(employee);

                MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get(url, 1)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(content);

                mockMvc.perform(mockRequest)
                                .andExpect(status().isOk());

        }

}
