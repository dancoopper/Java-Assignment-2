package org.example.asstest;


import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class EmployeeView {
    private final EmployeeManager employeeManager;
    private final DepartmentManager departmentManager;
    private final ListView<String> employeeListView;
    private final ComboBox<String> departmentDropdown;

    public EmployeeView(EmployeeManager employeeManager, DepartmentManager departmentManager) {
        this.employeeManager = employeeManager;
        this.departmentManager = departmentManager;
        this.employeeListView = new ListView<>();
        this.departmentDropdown = new ComboBox<>();
    }

    public VBox getLayout() {
        Label titleLabel = new Label("Employee Management");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Input fields
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField roleField = new TextField();
        roleField.setPromptText("Role");

        departmentDropdown.setPromptText("Select Department");
        updateDepartmentDropdown();

        // Buttons
        Button addButton = createStyledButton("Add Employee", "#4CAF50");
        Button updateButton = createStyledButton("Update Employee", "#FFC107");
        Button deleteButton = createStyledButton("Delete Employee", "#F44336");

        employeeListView.setPrefHeight(200);
        updateEmployeeList();

        // Add employee action
        addButton.setOnAction(event -> {
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressField.getText().trim();
            String role = roleField.getText().trim();
            String departmentName = departmentDropdown.getValue();

            if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || address.isEmpty() || role.isEmpty() || departmentName == null) {
                showAlert("Error", "All fields must be filled, and a department must be selected.");
                return;
            }

            employeeManager.addEmployee(firstName, lastName, phone, address, role, departmentName);
            updateEmployeeList();
            clearFields(firstNameField, lastNameField, phoneField, addressField, roleField);
            departmentDropdown.getSelectionModel().clearSelection();
        });

        // Delete employee action
        deleteButton.setOnAction(event -> {
            String selectedEmployeeString = employeeListView.getSelectionModel().getSelectedItem();
            if (selectedEmployeeString == null) {
                showAlert("Error", "Please select an employee to delete.");
                return;
            }

            int employeeId = Integer.parseInt(selectedEmployeeString.split(",")[0].split(":")[1].trim());
            employeeManager.removeEmployee(employeeId);
            updateEmployeeList();
        });

        VBox layout = new VBox(15, titleLabel,
                new HBox(10, firstNameField, lastNameField, phoneField, addressField, roleField, departmentDropdown),
                new HBox(10, addButton, updateButton, deleteButton),
                employeeListView);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        return layout;
    }

    private void updateEmployeeList() {
        employeeListView.getItems().clear();
        for (Employee emp : employeeManager.getAllEmployees()) {
            employeeListView.getItems().add(String.format("ID: %d, Name: %s %s, Role: %s, Department: %s",
                    emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getRole(), emp.getDepartmentName()));
        }
    }

    private void updateDepartmentDropdown() {
        departmentDropdown.getItems().clear();
        for (Department department : departmentManager.getDepartments()) {
            departmentDropdown.getItems().add(department.getDepartmentName());
        }
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) field.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: white; -fx-font-weight: bold;");
        return button;
    }
}
