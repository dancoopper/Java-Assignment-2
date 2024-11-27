package org.example.asstest;


import java.io.Serializable;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private int id;
    private String role;
    private String departmentName; // Tracks the department this employee belongs to

    public Employee(int id, String firstName, String lastName, String phoneNumber, String address, String role, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.departmentName = departmentName;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getRole() {
        return role;
    }


    public String getDepartmentName() {
        return departmentName;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s %s, Role: %s, Department: %s, Salary: %.2f", id, firstName, lastName, role, departmentName);
    }
}