package org.example.asstest;


import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.util.Map;
import java.util.stream.Collectors;

public class PayrollView {
    private final PayrollManager payrollManager;
    private final EmployeeManager employeeManager;
    private final DepartmentManager departmentManager;
    private final ListView<String> employeeListView;

    public PayrollView(PayrollManager payrollManager, EmployeeManager employeeManager, DepartmentManager departmentManager) {
        this.payrollManager = payrollManager;
        this.employeeManager = employeeManager;
        this.departmentManager = departmentManager;
        this.employeeListView = new ListView<>();
    }

    public VBox getLayout() {
        Label titleLabel = new Label("Payroll Management");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField hoursWorkedField = createTextField("Hours Worked");
        TextField hourlyRateField = createTextField("Hourly Rate");
        TextField overtimeHoursField = createTextField("Overtime Hours");
        TextField bonusField = createTextField("Bonus");
        TextField deductionsField = createTextField("Deductions");

        Button addOrUpdateButton = createButton("Add/Update Payroll", "#4CAF50");
        Button deletePayrollButton = createButton("Delete Payroll", "#F44336");
        Button generateEmployeeReportButton = createButton("Generate Employee Report", "#2196F3");
        Button generateDepartmentReportButton = createButton("Generate Department Report", "#2196F3");


        employeeListView.setPrefHeight(200);
        updateEmployeeList();

        // Add or Update Payroll
        addOrUpdateButton.setOnAction(event -> {
            String selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
            if (selectedEmployee == null) {
                showAlert("No Employee Selected", "Please select an employee.");
                return;
            }

            try {
                int employeeId = Integer.parseInt(selectedEmployee.split(",")[0].split(": ")[1].trim());
                double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
                double hourlyRate = Double.parseDouble(hourlyRateField.getText());
                double overtimeHours = Double.parseDouble(overtimeHoursField.getText());
                double bonus = Double.parseDouble(bonusField.getText());
                double deductions = Double.parseDouble(deductionsField.getText());

                payrollManager.createPayroll(employeeId, hoursWorked, hourlyRate, overtimeHours, bonus, deductions);
                showAlert("Success", "Payroll details have been added/updated.");
                clearFields(hoursWorkedField, hourlyRateField, overtimeHoursField, bonusField, deductionsField);
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Please ensure all fields contain valid numbers.");
            }
        });

        // Generate Employee Report
        generateEmployeeReportButton.setOnAction(event -> {
            String selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
            if (selectedEmployee == null) {
                showAlert("No Employee Selected", "Please select an employee.");
                return;
            }

            int employeeId = Integer.parseInt(selectedEmployee.split(",")[0].split(": ")[1].trim());
            String report = payrollManager.generateEmployeeReport(employeeId);
            showAlert("Employee Payroll Report", report);
        });

        // Generate Department Report
        generateDepartmentReportButton.setOnAction(event -> {
            String selectedEmployee = employeeListView.getSelectionModel().getSelectedItem();
            if (selectedEmployee == null) {
                showAlert("No Employee Selected", "Please select an employee to determine the department.");
                return;
            }

            String departmentName = selectedEmployee.split(", Department: ")[1];

            // Convert List<Employee> to Map<Integer, Employee>
            Map<Integer, Employee> employeeMap = employeeManager.getAllEmployees()
                    .stream()
                    .collect(Collectors.toMap(Employee::getId, emp -> emp));

            String report = payrollManager.generateDepartmentReport(employeeMap, departmentName);
            showAlert("Department Payroll Report", report);
        });

        VBox layout = new VBox(15, titleLabel,
                employeeListView,
                new HBox(10, hoursWorkedField, hourlyRateField),
                new HBox(10, overtimeHoursField, bonusField, deductionsField),
                new HBox(10, addOrUpdateButton, deletePayrollButton),
                new HBox(10, generateEmployeeReportButton, generateDepartmentReportButton)
        );

        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f9f9f9;");
        return layout;
    }

    private void updateEmployeeList() {
        employeeListView.getItems().clear();
        for (Employee emp : employeeManager.getAllEmployees()) {
            employeeListView.getItems().add(String.format("ID: %d, Name: %s %s, Department: %s",
                    emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getDepartmentName()));
        }
    }

    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setStyle("-fx-font-size: 14px; -fx-padding: 5px;");
        return textField;
    }

    private Button createButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");
        return button;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }
}
