package payroll.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

// import org.springframework.test.web.servlet.MockMvc;



import payroll.model.Employee;
import payroll.repository.EmployeeRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

// import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;

@WebMvcTest(EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    // @Autowired
    // private MockMvc mockMvc;

    // @Autowired
    // private ObjectMapper objectMapper;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Before
    public void setup() {

    }

    @Test
    public void testListAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        // Date now = new Date();
        employees.add(new Employee((long) 1, "Louis", "martin", "louis@martin.fr", "dsi", (float) 30000.00,
                (float) 600000.00, "sales manager", LocalDate.now()));
        employees.add(new Employee((long) 2, "Jeanne", "Bertrand", "jeanne@bertrand.fr", "usine1", (float) 25000.00,
                (float) 6000.00, "chef d'équipe", LocalDate.now()));
        employees.add(new Employee((long) 3, "Paul", "Auguste", "paul@auguste.fr", "technique", (float) 20000.00,
                (float) 600.00, "gardien", LocalDate.now()));

        // Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        // String url = "/employees";
        // mockMvc.perform(MockMvcRequestBuilders
        // .get(url)
        // .contentType(MediaType.APPLICATION_JSON))
        // .andExpect(status().isOk())
        // .andExpect(jsonPath("$", hasSize(3)))
        // .andExpect(jsonPath("$[0].lastname", is("Capet")));
    }
}
