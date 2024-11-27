package org.example.asstest;


public class Payroll {
    private int employeeId;
    private double hoursWorked;
    private double hourlyRate;
    private double overtimeHours;
    private double bonus;
    private double deductions;

    public Payroll(int employeeId, double hoursWorked, double hourlyRate, double overtimeHours, double bonus, double deductions) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.overtimeHours = overtimeHours;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double calculateGrossSalary() {
        return (hoursWorked * hourlyRate) + (overtimeHours * hourlyRate * 1.5) + bonus;
    }

    public double calculateNetSalary() {
        return calculateGrossSalary() - deductions;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public double getBonus() {
        return bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double calculateTotalSalary() {
        double regularSalary = hoursWorked * hourlyRate; // Salary for regular hours
        double overtimeSalary = overtimeHours * (hourlyRate * 1.5); // Overtime is usually paid at 1.5x the hourly rate
        double totalSalary = regularSalary + overtimeSalary + bonus - deductions; // Total Salary
        return totalSalary;
    }

}
