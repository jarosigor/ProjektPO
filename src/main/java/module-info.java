module com.example.pacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pacman to javafx.fxml;
    exports com.example.pacman;
    exports com.example.pacman.Boosters;
    opens com.example.pacman.Boosters to javafx.fxml;
}