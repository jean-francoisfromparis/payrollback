package com.jffromparis.payroll.repository;

import com.jffromparis.payroll.model.Employee;

import org.springframework.data.repository.CrudRepository;

/**
 * @Author jffromparis
 * @email jeanfrancois.lepante@gmail.com
 * @since 03/12/2022
*/
public interface EmployeeCrudRepository extends CrudRepository<Employee, Long>{
    Employee findByEmail(String email);
}
