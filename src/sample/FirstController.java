package sample;

import application.Czytelnicy;
import application.Pracownicy;
import hibernate.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

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

    @FXML
    private Label first_lable;


//    @FXML
//    void 808080(ActionEvent event) {
//
//    }

    @FXML
    void ffe6e6(ActionEvent event) {

    }

    @FXML
    void initialize() {



        loginSingInButton.setOnAction(event -> {

            String login = login_field.getText();
            login_field.setText(null);
            String haslo = password_field.getText();
            password_field.setText(null);


            ConnectionDB connect = new ConnectionDB();
            try {
                connect.login(login, haslo);
            }catch (Exception e){
                first_lable.setText("Zły login lub hasło");
            }

        });



        registerSingUpButton.setOnAction(event -> {
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
        });






    }
}
