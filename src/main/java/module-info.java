module com.example.cashlimit {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires jdk.jdi;


    opens com.example.cashlimit to javafx.fxml;
    exports com.example.cashlimit;
    exports com.example.cashlimit.controllers;
    opens com.example.cashlimit.controllers to javafx.fxml;
}