package com.jffromparis.payroll.repository;

import com.jffromparis.payroll.model.Employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeCrudRepository extends CrudRepository<Employee, Long>{
    Employee findByEmail(String email);
}
