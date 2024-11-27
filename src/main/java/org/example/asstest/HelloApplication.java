package org.example.asstest;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        /*MainView mainView = new MainView();
        Scene scene = new Scene(mainView.getLayout(), 800, 600);*/
        LoginPage loginPage = new LoginPage(primaryStage);
        Scene scene = new Scene(loginPage.getLayout(), 800, 600);

        primaryStage.setTitle("HR Management System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
