module com.example.crapgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.crapgame to javafx.fxml;
    exports com.example.crapgame;
}