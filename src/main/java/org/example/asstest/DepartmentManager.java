package org.example.asstest;

import java.util.ArrayList;
import java.util.List;

public class DepartmentManager {
    private List<Department> departments = new ArrayList<>();

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    public Department findDepartmentByName(String name) {
        for (Department department : departments) {
            if (department.getDepartmentName().equalsIgnoreCase(name)) {
                return department;
            }
        }
        return null;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}