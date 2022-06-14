module com.example.pacman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pacman to javafx.fxml;
    exports com.example.pacman;
    exports com.example.pacman.components;
    opens com.example.pacman.components to javafx.fxml;
    exports com.example.pacman.ghosts;
    opens com.example.pacman.ghosts to javafx.fxml;
    exports com.example.pacman.gameUtilities;
    opens com.example.pacman.gameUtilities to javafx.fxml;
}