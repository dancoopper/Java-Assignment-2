package org.example.asstest;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class DepartmentView {
    private final DepartmentManager departmentManager;
    private final ListView<String> departmentListView;

    public DepartmentView(DepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
        this.departmentListView = new ListView<>();
    }

    public VBox getLayout() {
        Label titleLabel = new Label("Department Management");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField nameField = createTextField("Department Name");

        Button addButton = createButton("Add Department", "#4CAF50");
        Button removeDepartmentButton = createButton("Remove Department", "#F44336");

        departmentListView.setPrefHeight(200);
        updateDepartmentList();

        // Add Department Action
        addButton.setOnAction(event -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                showAlert("Invalid Input", "Department name must be provided.");
                return;
            }
            departmentManager.addDepartment(new Department(name)); // Pass a Department object
            updateDepartmentList();
            nameField.clear();
        });


        // Remove Department Action
        removeDepartmentButton.setOnAction(event -> {
            String selected = departmentListView.getSelectionModel().getSelectedItem();
            if (selected == null) {
                showAlert("No Department Selected", "Please select a department to remove.");
                return;
            }
            if (showConfirmationAlert("Confirm Deletion", "Are you sure you want to delete the selected department?")) {
                String departmentId = selected.split(", ")[0].split(": ")[1];
                Department department = departmentManager.findDepartmentByName(departmentId);
                if (department != null) {
                    departmentManager.removeDepartment(department);
                    updateDepartmentList();
                } else {
                    showAlert("Error", "Could not find the selected department.");
                }
            }
        });

        VBox layout = new VBox(15,
                titleLabel,
                new HBox(10, nameField, addButton, removeDepartmentButton),
                departmentListView
        );

        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #dcdcdc; -fx-border-width: 1px;");
        return layout;
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

    private void updateDepartmentList() {
        departmentListView.getItems().clear();
        for (Department dept : departmentManager.getDepartments()) {
            departmentListView.getItems().add("ID: " + dept.getDepartmentId() + ", Name: " + dept.getDepartmentName());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);
        return result == ButtonType.OK;
    }
}
