package com.jffromparis.payroll.controller;

import java.util.List;

import com.jffromparis.payroll.model.Employee;
import com.jffromparis.payroll.repository.EmployeeCrudRepository;
import com.jffromparis.payroll.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    //Récupérer un employé par son identifiant
    // @GetMapping("/employee/{id}")
    // public ResponseEntity<Employee> getOneEmployeeById(@PathVariable Long id) {
    //     // User user = userService.findById(id);
    //     Employee employee = employeeRepository.getById(id);

    //     if (employee == null) {
    //         LOG.info("Il n'y a pas d'employé avec l'identifiant :", id);
    //         return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
    //     }

    //     return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    // }



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

    //Créer et modifier un employé
@PostMapping("/employees")
public Employee createRecord(@RequestBody Employee employee) {
    return employeeRepository.save(employee);
}

}
