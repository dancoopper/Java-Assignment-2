package org.example.asstest;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage {
    private final Stage primaryStage;

    public LoginPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public VBox getLayout() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Text title = new Text("Login");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        Button loginButton = new Button("Login");

        Label messageLabel = new Label();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (Auth.login(username, password)) {
                messageLabel.setText("Login successful!");
                MainView mainView = new MainView();
                Scene mainScene = new Scene(mainView.getLayout(), 800, 600);
                primaryStage.setScene(mainScene);
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        });

        layout.getChildren().addAll(title, usernameField, passwordField, loginButton, messageLabel);
        return layout;
    }
}