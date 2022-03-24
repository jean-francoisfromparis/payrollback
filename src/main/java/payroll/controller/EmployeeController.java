package payroll.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import payroll.model.Employee;
import payroll.repository.EmployeeCrudRepository;
import payroll.repository.EmployeeRepository;

/**
 * @Author jffromparis
 * @email jeanfrancois.lepante@gmail.com
 * @since 03/12/2022
*/
@RestController
public class EmployeeController {
    private final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final EmployeeCrudRepository employeeCrudRepository;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeCrudRepository employeeCrudRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCrudRepository = employeeCrudRepository;
    }

    //Récupérer tous les employées
    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

 //Récupérer un employé par son id
@GetMapping("/employeebyId/{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") Long id) {
    Employee employee = employeeRepository.findById(id).get();
    if (employee == null) {
                LOG.info("Il n'y a pas d'employé avec l'identifiant :", id);
                return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<Employee>(employee, HttpStatus.OK);
}
    //Récupérer un employé par son email
    @GetMapping("/employeebyemail/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(String email) {
        Employee employee = employeeCrudRepository.findByEmail(email);

        if (employee == null) {
            LOG.info("Il n'y a pas d'employé avec l'email :", email);
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    //Créer un employé
@PostMapping("/employees")
public Employee createRecord(@RequestBody Employee employee) {
    return employeeRepository.save(employee);
}

//Modifier un employé
@PutMapping("/employees/{id}")
  Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

    return employeeRepository.findById(id)
      .map(employee -> {
        employee.setFirstname(newEmployee.getFirstname());
        employee.setLastname(newEmployee.getLastname());
        employee.setEmail(newEmployee.getEmail());
        employee.setDepartment(newEmployee.getDepartment());
        employee.setWage(newEmployee.getWage());
        employee.setSales_objective(newEmployee.getSales_objective());
        employee.setHired_at(newEmployee.getHired_at());
        employee.setRole(newEmployee.getRole());
        return employeeRepository.save(employee);
      })
      .orElseGet(() -> {
        newEmployee.setId(id);
        return employeeRepository.save(newEmployee);
      });
  }
    //Supprimer un employé (en terme légale la suppression d'un employé n'est pas possible, l'employé doit être transféré dans )
@DeleteMapping("/employee/{id}")
public void deletePatientById(@PathVariable(value = "id") Long id) throws NotFoundException {
    if (employeeRepository.findById(id).isEmpty()) {
        throw new NotFoundException();
    }
    employeeRepository.deleteById(id);
}
}
