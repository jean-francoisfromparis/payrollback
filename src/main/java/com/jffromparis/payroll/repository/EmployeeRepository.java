package com.jffromparis.payroll.repository;

import com.jffromparis.payroll.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends  JpaRepository<Employee, Long>{

}
