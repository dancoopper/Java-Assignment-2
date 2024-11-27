package org.example.asstest;


import java.util.HashMap;
import java.util.Map;

public class PayrollManager {
    private Map<Integer, Payroll> payrolls = new HashMap<>();

    public void createPayroll(int employeeId, double hoursWorked, double hourlyRate, double overtimeHours, double bonus, double deductions) {
        payrolls.put(employeeId, new Payroll(employeeId, hoursWorked, hourlyRate, overtimeHours, bonus, deductions));
    }

    public Payroll getPayroll(int employeeId) {
        return payrolls.get(employeeId);
    }

    public void updatePayroll(int employeeId, double hoursWorked, double hourlyRate, double overtimeHours, double bonus, double deductions) {
        Payroll payroll = payrolls.get(employeeId);
        if (payroll != null) {
            payroll.setHoursWorked(hoursWorked);
            payroll.setHourlyRate(hourlyRate);
            payroll.setOvertimeHours(overtimeHours);
            payroll.setBonus(bonus);
            payroll.setDeductions(deductions);
        }
    }

    public void deletePayroll(int employeeId) {
        payrolls.remove(employeeId);
    }

    public String generateEmployeeReport(int employeeId) {
        Payroll payroll = payrolls.get(employeeId);
        if (payroll == null) {
            return "No payroll data available for this employee.";
        }

        return String.format(
                "Payroll Report for Employee ID: %d\nHours Worked: %.2f\nHourly Rate: %.2f\nOvertime Hours: %.2f\nBonus: %.2f\nDeductions: %.2f\nTotal Salary: %.2f",
                employeeId, payroll.getHoursWorked(), payroll.getHourlyRate(), payroll.getOvertimeHours(),
                payroll.getBonus(), payroll.getDeductions(), payroll.calculateTotalSalary()
        );
    }

    public String generateDepartmentReport(Map<Integer, Employee> employees, String departmentName) {
        StringBuilder report = new StringBuilder("Payroll Report for Department: " + departmentName + "\n\n");
        double totalDepartmentSalary = 0;

        for (Employee employee : employees.values()) {
            if (departmentName.equals(employee.getDepartmentName())) {
                Payroll payroll = payrolls.get(employee.getId());
                if (payroll != null) {
                    report.append(String.format(
                            "Employee: %s %s, ID: %d\nHours Worked: %.2f, Hourly Rate: %.2f, Overtime Hours: %.2f, Bonus: %.2f, Deductions: %.2f, Total Salary: %.2f\n\n",
                            employee.getFirstName(), employee.getLastName(), employee.getId(),
                            payroll.getHoursWorked(), payroll.getHourlyRate(), payroll.getOvertimeHours(),
                            payroll.getBonus(), payroll.getDeductions(), payroll.calculateTotalSalary()
                    ));
                    totalDepartmentSalary += payroll.calculateTotalSalary();
                }
            }
        }

        report.append("Total Department Salary: ").append(totalDepartmentSalary);
        return report.toString();
    }

}
