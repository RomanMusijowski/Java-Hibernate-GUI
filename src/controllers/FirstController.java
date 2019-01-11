package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FirstController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button loginSingInButton;

    @FXML
    private TextField login_field;

    @FXML
    private Button registerSingUpButton;

    @FXML
    private Label first_lable;

    @FXML
    void actionLogUp(ActionEvent event) {
        registerSingUpButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/rejestracja_scene.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }


    @FXML
    void actionLogIn(ActionEvent event) {
        ConnectionDB connect = new ConnectionDB();

        String login = login_field.getText();
        login_field.setText(null);
        String haslo = password_field.getText();
        password_field.setText(null);




        try {
            if (connect.login(login, haslo)){

                loginSingInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/scenes/pracownik_scene.fxml"));

                try {
                    loader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                PracownikController pracownikController = loader.getController();
                pracownikController.setText(connect.getPracownik());

                connect.closeConnection();



                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }else {
                first_lable.setText("Zły login lub hasło");
            }

        } catch (Exception e) {
            first_lable.setText("Zły login lub hasło");
        }

}


    @FXML
    void initialize() {

    }
}
