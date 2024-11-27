package org.example.asstest;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private List<Employee> employees = new ArrayList<>();
    private int employeeIdCounter = 1;

    public Employee addEmployee(String firstName, String lastName, String phone, String address, String role, String departmentName) {
        Employee employee = new Employee(employeeIdCounter++, firstName, lastName, phone, address, role, departmentName);
        employees.add(employee);
        return employee; // Return the created employee
    }

    public boolean updateEmployee(int employeeId, String firstName, String lastName, String phone, String address, String role, String departmentName) {
        Employee employee = findEmployee(employeeId);
        if (employee != null) {
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setPhoneNumber(phone);
            employee.setAddress(address);
            employee.setRole(role);
            employee.setDepartmentName(departmentName);
            return true;
        }
        return false;
    }

    public void removeEmployee(int employeeId) {
        employees.removeIf(employee -> employee.getId() == employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Employee findEmployee(int employeeId) {
        for (Employee employee : employees) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }
}
