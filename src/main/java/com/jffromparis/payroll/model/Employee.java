package com.jffromparis.payroll.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author jffromparis
 * @email jeanfrancois.lepante@gmail.com
 * @since 03/12/2022
*/
@Entity
public class Employee {
  @Id
  @GeneratedValue
  private Long id;

  private String firstname;
  private String name;
  private String email;
  private String role;

  public Employee() {}

  public Employee(String firstname, String name, String email, String role) {
    this.firstname = firstname;
    this.name = name;
    this.email = email;
    this.role = role;
  }

  public Long getId() {
    return this.id;
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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Employee id(Long id) {
    setId(id);
    return this;
  }

  public Employee firstname(String firstname) {
    setFirstname(firstname);
    return this;
  }

  public Employee name(String name) {
    setName(name);
    return this;
  }

  public Employee role(String role) {
    setRole(role);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Employee)) {
      return false;
    }
    Employee employee = (Employee) o;
    return (
      Objects.equals(id, employee.id) &&
      Objects.equals(firstname, employee.firstname) &&
      Objects.equals(name, employee.name) &&
      Objects.equals(email, employee.email) &&
      Objects.equals(role, employee.role)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, name, email, role);
  }

  @Override
  public String toString() {
    return (
      "L'employé (e) {" +
      "a pour identifiant ='" +
      getId() +
      "'" +
      ", pour prénom ='" +
      getFirstname() +
      "'" +
      ", pour nom ='" +
      getName() +
      "'" +
      ", pour email ='" +
      getEmail() +
      "'" +
      ", pour rôle ='" +
      getRole() +
      "'" +
      "}"
    );
  }
}
