module com.example.garage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.garage to javafx.fxml;
    exports com.example.garage;
}