package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Czytelnicy;
import application.Pracownicy;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RejestracjaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_field;

    @FXML
    private Label email_lable;

    @FXML
    private Button rejestracjaButton;

    @FXML
    private TextField pesel_field;

    @FXML
    private Label haslo_lable_1;

    @FXML
    private TextField haslo_2_field;

    @FXML
    private Label haslo_lable_2;

    @FXML
    private RadioButton czytelnik_radio;

    @FXML
    private TextField haslo_1_field;

    @FXML
    private Label pesel_lable;

    @FXML
    private RadioButton pracownik_radio;

    @FXML
    private Label typ_osoby;

    @FXML
    private Label last_name_lable;

    @FXML
    private TextField name_field;

    @FXML
    private Label name_lable;

    @FXML
    private TextField last_name_field;

    @FXML
    private Button backRejestracjaButton;

    @FXML
    void ffe6e6(ActionEvent event) {

    }

    @FXML
    void actionBackRejestracja(ActionEvent event) {
        backRejestracjaButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/scenes/first_scene.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    void actionCztylnikRadio(ActionEvent event) {
        if (event.getSource() == czytelnik_radio) {
            czytelnik_radio.setSelected(true);
            pracownik_radio.setSelected(false);
        }
    }

    @FXML
    void actionPracownikRadio(ActionEvent event) {
        if (event.getSource() == pracownik_radio) {
            pracownik_radio.setSelected(true);
            czytelnik_radio.setSelected(false);
        }
    }


    @FXML
    void initialize() {

        pesel_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                pesel_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        rejestracjaButton.setOnAction(event -> {

            ConnectionDB connect = new ConnectionDB();

            String name = name_field.getText();
            String last_name = last_name_field.getText();
            String email = email_field.getText();
            String pesel = pesel_field.getText();

            String haslo1 = haslo_1_field.getText();
            String haslo2 = haslo_2_field.getText();

            if(name.isEmpty() || last_name.isEmpty() || email.isEmpty() ||
                    pesel.isEmpty() || haslo1.isEmpty() || haslo2.isEmpty()){

                typ_osoby.setText("Niewystarczajacy ilosc danych");
            } else {
                //sprawdza identycznosc hasl
                if (haslo1.equals(haslo2)) {

                    if (pracownik_radio.isSelected()) {

                        Pracownicy pracownicy = new Pracownicy(name, last_name, pesel, email, haslo2);
                        try {
                            connect.rejestracjaPrac(pracownicy);
                            typ_osoby.setText("ZArejestrowany pracownik");
                        } catch (Exception e) {
                            System.out.println("Blad rejestracji");
                            e.printStackTrace();
                        }

                        name_field.setText(null);
                        last_name_field.setText(null);
                        email_field.setText(null);
                        pesel_field.setText(null);

                    } else if (czytelnik_radio.isSelected()) {
                        Czytelnicy czytelnicy = new Czytelnicy(name, last_name, pesel, email, haslo2);


                        try {
                            connect.rejestracjaCzyt(czytelnicy);
                            typ_osoby.setText("ZArejestrowany czytelnik");
                        } catch (Exception e) {
                            System.out.println("Blad rejestracji");
                            e.printStackTrace();
                        }

                    } else
                        typ_osoby.setText("Nie wybrano typu");

                } else {
                    haslo_lable_1.setText("Zle podane hasło");
                    haslo_lable_2.setText("Zle podane hasło");
                    haslo_1_field.setText(null);
                    haslo_2_field.setText(null);
                }
            }
            connect.closeConnection();
        });
    }
}
