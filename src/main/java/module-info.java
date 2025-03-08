module com.example.crapgame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.crapgame to javafx.fxml;
    exports com.example.crapgame;
}