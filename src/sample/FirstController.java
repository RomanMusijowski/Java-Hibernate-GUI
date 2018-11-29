package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class FirstController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField password_field;

    @FXML
    private Button loginSingInButton;

    @FXML
    private TextField login_field;

    @FXML
    private Button registerSingUpButton;

//    @FXML
//    void 808080(ActionEvent event) {
//
//    }

    @FXML
    void ffe6e6(ActionEvent event) {

    }

    @FXML
    void initialize() {
        String login = login_field.getText();
        String hoslo = password_field.getText();

        String sql = "SELECT * FROM Lekarze WHERE id_lakarza = ?  AND pesel = ?";
        loginSingInButton.setOnAction(actionEvent ->

                );
    }
}
