module org.example.asstest {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.asstest to javafx.fxml;
    exports org.example.asstest;
}