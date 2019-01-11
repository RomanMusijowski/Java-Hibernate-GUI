package controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import application.Czytelnicy;
import application.Egzemplarze;
import application.Pracownicy;
import application.Wypozyczenia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.converter.DateTimeStringConverter;

public class HistoryInfController {


    private Czytelnicy czytelnik;
    private Pracownicy pracownik;
    private Egzemplarze egzemplarz;
    private Wypozyczenia wypozyczenie;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField employeeName;

    @FXML
    private Button bookInfPrzyjacButton;

    @FXML
    private TextField dateForGive;

    @FXML
    private TextField dateOfTaken;

    @FXML
    private TextField bookAuthor;

    @FXML
    private TextField bookState;

    @FXML
    private TextField dateOfGiven;

    @FXML
    private TextField personName;

    @FXML
    private TextField boookName;

    @FXML
    private TextField employeeLastName;

    @FXML
    private TextField bookYear;

    @FXML
    private TextField personLastName;

    @FXML
    private Label first_lable;

    @FXML
    private Button bookInfEdytowacButton;

    @FXML
    private Label labelAfterAction;

    @FXML
    void f50000(ActionEvent event) {

    }

    @FXML
    void actionTake(ActionEvent event) {
        ConnectionDB connectionDB = new ConnectionDB();

        if (connectionDB.takeWypozyczenie(this.wypozyczenie)){
            labelAfterAction.setText("Przyjęto");
        }

        connectionDB.closeConnection();
    }

    @FXML
    void actionExtend(ActionEvent event) {
        ConnectionDB connectionDB = new ConnectionDB();
        String date = dateForGive.getText();

        try {
            DateTimeStringConverter format = new DateTimeStringConverter("dd/MM/yyyy");
            Date editDate = format.fromString(date);

            Calendar newDate = Calendar.getInstance();
            newDate.setTime(editDate);

            connectionDB.extendWypozyczenie(this.wypozyczenie,newDate);
            labelAfterAction.setText("Przedłużono");

        }catch (Exception e){
            e.printStackTrace();
            labelAfterAction.setText("Problem podania daty");
        }

        connectionDB.closeConnection();
    }

    @FXML
    void initialize() {

    }


    public void setText(Czytelnicy czytelnik, Pracownicy pracownik, Egzemplarze egzemplarz, Wypozyczenia wypozyczenie){
        this.czytelnik = czytelnik;
        this.pracownik = pracownik;
        this.egzemplarz = egzemplarz;
        this.wypozyczenie = wypozyczenie;


        this.personName.setText(czytelnik.getName());
        this.personLastName.setText(czytelnik.getLast_name());

        this.employeeName.setText(pracownik.getName());
        this.employeeLastName.setText(pracownik.getLast_name());

        this.boookName.setText(egzemplarz.getKsiazka().getNazwa());
        this.bookAuthor.setText(egzemplarz.getKsiazka().getAutor());
        this.bookYear.setText(String.valueOf(egzemplarz.getKsiazka().getRok_wydania()));
        this.bookState.setText(String.valueOf(egzemplarz.isStan()));


        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar;

        String termin;
        String dateWypoz;
        String dateZwrotu;



        calendar = wypozyczenie.getData_zwrotu();
        try{
            dateZwrotu = dateFormat.format(calendar.getTime());
        }catch(Exception e) {
            dateZwrotu = "null";
        }

        calendar = wypozyczenie.getData_wypozyczenia();
        dateWypoz = dateFormat.format(calendar.getTime());

        calendar = wypozyczenie.getTermin();
        termin = dateFormat.format(calendar.getTime());

        dateOfTaken.setText(dateWypoz);
        dateForGive.setText(termin);
        dateOfGiven.setText(dateZwrotu);
    }
}
