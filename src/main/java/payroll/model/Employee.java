package payroll.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author jffromparis
 * @email jeanfrancois.lepante@gmail.com
 * @since 03/12/2022
*/
@Entity
public class Employee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="firstname")
  private String firstname;

  @Column(name="lastname")
  private String lastname;

  @Column(name="email")
  private String email;

  @Column(name="department")
  private String department;

  @Column(name="wage")
  private Float wage;

  @Column(name="sales_objective")
  private Float sales_objective;

  @Column(name="role")
  private String role;

  @Column(name="hired_at")
  private LocalDate hired_at;

  public Employee() {
  }

  public Employee(Long i, String firstname, String lastname, String email, String department, Float wage, Float sales_objective, String role, LocalDate localDate) {
    this.id = i;
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.department = department;
    this.wage = wage;
    this.sales_objective = sales_objective;
    this.role = role;
    this.hired_at = localDate;
  }

  public Long getId() {
    return (long) this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Float getWage() {
    return this.wage;
  }

  public void setWage(Float wage) {
    this.wage = wage;
  }

  public Float getSales_objective() {
    return this.sales_objective;
  }

  public void setSales_objective(Float sales_objective) {
    this.sales_objective = sales_objective;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public LocalDate getHired_at() {
    return this.hired_at;
  }

  public void setHired_at(LocalDate hired_at) {
    this.hired_at = hired_at;
  }

  public Employee id(Long id) {
    setId(id);
    return this;
  }

  public Employee firstname(String firstname) {
    setFirstname(firstname);
    return this;
  }

  public Employee lastname(String lastname) {
    setLastname(lastname);
    return this;
  }

  public Employee email(String email) {
    setEmail(email);
    return this;
  }

  public Employee department(String department) {
    setDepartment(department);
    return this;
  }

  public Employee wage(Float wage) {
    setWage(wage);
    return this;
  }

  public Employee sales_objective(Float sales_objective) {
    setSales_objective(sales_objective);
    return this;
  }

  public Employee role(String role) {
    setRole(role);
    return this;
  }

  public Employee hired_at(LocalDate hired_at) {
    setHired_at(hired_at);
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(firstname, employee.firstname) && Objects.equals(lastname, employee.lastname) && Objects.equals(email, employee.email) && Objects.equals(department, employee.department) && Objects.equals(wage, employee.wage) && Objects.equals(sales_objective, employee.sales_objective) && Objects.equals(role, employee.role) && Objects.equals(hired_at, employee.hired_at);
  }

  @Override
  public String toString() {
    return "{" +
      " id='" + getId() + "'" +
      ", firstname='" + getFirstname() + "'" +
      ", lastname='" + getLastname() + "'" +
      ", email='" + getEmail() + "'" +
      ", department='" + getDepartment() + "'" +
      ", wage='" + getWage() + "'" +
      ", sales_objective='" + getSales_objective() + "'" +
      ", role='" + getRole() + "'" +
      ", hired_at='" + getHired_at() + "'" +
      "}";
  }
}
