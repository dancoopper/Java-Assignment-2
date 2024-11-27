package org.example.asstest;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private static int departmentIdCounter = 1;
    private final int departmentId;
    private final String departmentName;
    private List<Employee> employees;

    public Department(String departmentName) {
        this.departmentId = departmentIdCounter++;
        this.departmentName = departmentName;
        this.employees = new ArrayList<>();
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }
}