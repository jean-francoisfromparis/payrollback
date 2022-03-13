package payroll.repository;

import org.springframework.data.repository.CrudRepository;

import payroll.model.Employee;

/**
 * @Author jffromparis
 * @email jeanfrancois.lepante@gmail.com
 * @since 03/12/2022
*/
public interface EmployeeCrudRepository extends CrudRepository<Employee, Long>{
    Employee findByEmail(String email);
}
