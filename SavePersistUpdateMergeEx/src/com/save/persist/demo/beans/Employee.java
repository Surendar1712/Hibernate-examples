package com.save.persist.demo.beans;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "EMPLOYEE")
@Access(value = AccessType.FIELD)
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "emp_id")
  private long id;

  @Column(name = "emp_name")
  private String name;

  @Column(name = "emp_salary")
  private double salary;

  @OneToOne(mappedBy = "employee")
  @Cascade(value = CascadeType.ALL)
  private Address address;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Id= " + id + ", Name= " + name + ", Salary= " + salary + ", {Address= " + address + "}";
  }

}
