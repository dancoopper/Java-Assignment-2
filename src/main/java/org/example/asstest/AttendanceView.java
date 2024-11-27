package org.example.asstest;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.time.LocalDate;

public class AttendanceView {
    private final AttendanceManager attendanceManager;
    private final EmployeeManager employeeManager;

    public AttendanceView(AttendanceManager attendanceManager, EmployeeManager employeeManager) {
        this.attendanceManager = attendanceManager;
        this.employeeManager = employeeManager;
    }

    public VBox getLayout() {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));

        Label titleLabel = new Label("Attendance Management");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ComboBox<String> employeeDropdown = new ComboBox<>();
        employeeManager.getAllEmployees().forEach(emp ->
                employeeDropdown.getItems().add(String.format("ID: %d - %s %s", emp.getId(), emp.getFirstName(), emp.getLastName())));

        DatePicker datePicker = new DatePicker();
        ToggleGroup attendanceGroup = new ToggleGroup();
        RadioButton presentButton = new RadioButton("Present");
        presentButton.setToggleGroup(attendanceGroup);
        RadioButton absentButton = new RadioButton("Absent");
        absentButton.setToggleGroup(attendanceGroup);
        TextField reasonField = new TextField();
        reasonField.setPromptText("Reason (if absent)");

        Button markButton = new Button("Mark Attendance");
        markButton.setOnAction(e -> {
            String selectedEmployee = employeeDropdown.getValue();
            if (selectedEmployee == null || datePicker.getValue() == null || attendanceGroup.getSelectedToggle() == null) {
                showAlert("Error", "All fields must be filled.");
                return;
            }

            int employeeId = Integer.parseInt(selectedEmployee.split(" ")[1]);
            boolean isPresent = presentButton.isSelected();
            String reason = isPresent ? "" : reasonField.getText();

            attendanceManager.markAttendance(employeeId, datePicker.getValue(), isPresent, reason);
            showAlert("Success", "Attendance marked successfully.");
        });

        layout.getChildren().addAll(titleLabel, employeeDropdown, datePicker, new HBox(10, presentButton, absentButton),
                reasonField, markButton);
        return layout;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
