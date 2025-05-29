package com.naveenBanwala.springboot.demo.employeeproject.entity;


import jakarta.persistence.*;

@Entity
@Table(name="employee")
public class Employee {

    //defineFields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Column(name="id")
    private int id;

    @Column(name="last_name")
    private String firstName;
    @Column(name="first_name")
    private String lastName;
    @Column(name="email")
    private String email;


    //Define Constructors
    public Employee() {}

    public Employee(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Define Getters/Setters

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //Define toString
     // it is used for printing By default when we create Object of this class
    //And print the data then it print like this

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
