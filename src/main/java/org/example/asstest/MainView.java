package org.example.asstest;


import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.chart.*;
import javafx.geometry.Insets;
import javafx.scene.text.Text;

public class MainView {
    private final DepartmentManager departmentManager = new DepartmentManager();
    private final EmployeeManager employeeManager = new EmployeeManager();
    private final PayrollManager payrollManager = new PayrollManager();
    private final AttendanceManager attendanceManager = new AttendanceManager();

    public BorderPane getLayout() {
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        Menu manageMenu = new Menu("Manage");
        MenuItem departmentMenuItem = new MenuItem("Departments");
        MenuItem employeeMenuItem = new MenuItem("Employees");
        MenuItem payrollMenuItem = new MenuItem("Payroll");
        MenuItem analyticsMenuItem = new MenuItem("Attendance Analytics");
        MenuItem attendanceMenuItem = new MenuItem("Mark Attendance");

        manageMenu.getItems().addAll(departmentMenuItem, employeeMenuItem, payrollMenuItem, analyticsMenuItem, attendanceMenuItem);
        menuBar.getMenus().add(manageMenu);

        DepartmentView departmentView = new DepartmentView(departmentManager);
        EmployeeView employeeView = new EmployeeView(employeeManager, departmentManager);
        PayrollView payrollView = new PayrollView(payrollManager, employeeManager, departmentManager);

        departmentMenuItem.setOnAction(e -> root.setCenter(departmentView.getLayout()));
        employeeMenuItem.setOnAction(e -> root.setCenter(employeeView.getLayout()));
        payrollMenuItem.setOnAction(e -> root.setCenter(payrollView.getLayout()));
        analyticsMenuItem.setOnAction(e -> root.setCenter(getAttendanceAnalyticsLayout()));
        attendanceMenuItem.setOnAction(e -> root.setCenter(new AttendanceView(attendanceManager, employeeManager).getLayout()));

        root.setTop(menuBar);
        return root;
    }

    private VBox getAttendanceAnalyticsLayout() {
        VBox layout = new VBox(20);
        layout.setPadding(new Insets(20));

        Text title = new Text("Attendance Analytics");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        GridPane attendanceGrid = new GridPane();
        attendanceGrid.setHgap(20);
        attendanceGrid.setVgap(10);
        attendanceGrid.setPadding(new Insets(20));
        attendanceGrid.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #dcdcdc; -fx-border-width: 1px;");

        // Add header row
        attendanceGrid.add(new Label("Employee"), 0, 0);
        attendanceGrid.add(new Label("Days Present"), 1, 0);
        attendanceGrid.add(new Label("Days Absent"), 2, 0);

        // Populate attendance data
        int rowIndex = 1; // Start from the second row for employee data
        for (Employee emp : employeeManager.getAllEmployees()) {
            int daysPresent = (int) attendanceManager.getAttendance(emp.getId()).stream()
                    .filter(AttendanceRecord::isPresent)
                    .count();

            int daysAbsent = (int) attendanceManager.getAttendance(emp.getId()).stream()
                    .filter(record -> !record.isPresent())
                    .count();

            attendanceGrid.add(new Label(emp.getFirstName() + " " + emp.getLastName()), 0, rowIndex);
            attendanceGrid.add(new Label(String.valueOf(daysPresent)), 1, rowIndex);
            attendanceGrid.add(new Label(String.valueOf(daysAbsent)), 2, rowIndex);

            rowIndex++;
        }

        layout.getChildren().addAll(title, attendanceGrid);
        return layout;
    }
}
