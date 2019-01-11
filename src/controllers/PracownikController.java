package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

import application.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PracownikController {

    private Czytelnicy czytelnik;
    private Pracownicy pracownik;
    private Ksiazki ksiazka = null;

    @FXML
    private TextField iloscKsiazek_field;


    @FXML
    private TextField wyporz_rokKsiazki_field;

    @FXML
    private TextField historyNazwiskoCzytelnika;

    @FXML
    private TableView<Wypozyczenia> pracTableViewHistory;

    @FXML
    private TableColumn<Ksiazki, String> columnBookName;

    @FXML
    private TextField lastName_czytelnika_field;

    @FXML
    private DatePicker wyporz_terminOddania;

    @FXML
    private Label labelBookInf;

    @FXML
    private TableColumn<Czytelnicy, String> tableWyporzLastNameCzyt;


    @FXML
    private TableColumn<Czytelnicy, String> tableWyporzNameCzyt;

    @FXML
    private TableColumn<Czytelnicy, Boolean> tableWyporzActionCzyt;

    @FXML
    private TableColumn<Ksiazki, String> tableWyporzIdNameBook;

    @FXML
    private TextField rokKsiazki_field;

    @FXML
    private TableColumn<Ksiazki, Integer> columnBookRok;

    @FXML
    private TextField nazwaKsiazki_field;

    @FXML
    private TableColumn<Ksiazki, String> columnBookAutor;

    @FXML
    private TextField pesel_czytelnika_field;

    @FXML
    private TableColumn<Ksiazki, Integer> tableWyporzAutorBook;


    @FXML
    private TableView<Ksiazki> pracTabViewKsiazki;

    @FXML
    private TextField name_czytelnika_field;

    @FXML
    private TextField wyporz_nazwaKsiazki_field;


    @FXML
    private Button buttonWyporzSzukBook;

    @FXML
    private TableColumn<Czytelnicy, String> tableWyporzPeselCzytelnika;

    @FXML
    private Button szukac_ksiazke_button2;

    @FXML
    private TableColumn<Ksiazki, Boolean> columnBookInf;

    @FXML
    private TableView<Ksiazki> tableWypozKsiazki;

    @FXML
    private Button logOutButton;

    @FXML
    private TableColumn<Ksiazki, Boolean> tableWyporzActionBook;

    @FXML
    private TableColumn<Ksiazki, Integer> tableWyporzRokBook;

    @FXML
    private Label lable_name_user;

    @FXML
    private TextField historyImieCzytelnika;



    @FXML
    private TableColumn<Wypozyczenia, Boolean> column_historyAct;

    @FXML
    private Label labelWypozSuccessful;

    @FXML
    private TableView<Czytelnicy> tableWypozCzyt;

    @FXML
    private TextField autorKsiazki_field;

    @FXML
    private TextField wyporz_autorKsiazki_field1;

    @FXML
    private Label pracHistoryLabel;
    @FXML
    private Label labelWypozCzytelnik;

    @FXML
    private Label labelWypozBook;

    @FXML
    private Label labelWypozData;

    @FXML
    private TableColumn<Wypozyczenia, Calendar> column_dataOdebrania;

    @FXML
    private TableColumn<Wypozyczenia, Calendar> column_termin;

    @FXML
    private TableColumn<Wypozyczenia, Calendar> column_dataOddania;

    @FXML
    private TableColumn<Wypozyczenia, Long> column_historyIdWyp;


    //table Dane osobowe czytelnicy
    @FXML
    private TableColumn<Czytelnicy, String > tableOsobaPesel;

    @FXML
    private TableColumn<Czytelnicy, String > tableOsobaImie;

    @FXML
    private TableColumn<Czytelnicy, String > tableOsobaNazwisko;

    @FXML
    private TableColumn<Czytelnicy, Boolean> tableOsobaButton;

    //Table Dane osobowe pracownicy
    @FXML
    private TableColumn<Czytelnicy, String > tableOsobaPracownikPesel;

    @FXML
    private TableColumn<Czytelnicy, String > tableOsobaPracownikImie;

    @FXML
    private TableColumn<Czytelnicy, String > tableOsobaPracownikNazwisko;

    @FXML
    private TableColumn<Pracownicy, Boolean> tableOsobaPracownikButton;

    @FXML
    private TextField osobaNameNew;

    @FXML
    private TextField osobaLastnameNew;

    @FXML
    private TextField osobaEmailNew;

    @FXML
    private TextField osobaPeselNew;

    @FXML
    private TextField osobaType;

    //pola tekstowe Dane osobowe
    @FXML
    private TextField osobaName;

    @FXML
    private TextField osobaLastname;

    @FXML
    private TextField osobaEmail;

    @FXML
    private TextField osobaPesel;

    @FXML
    private TableColumn columnBookDelete;

    //button and action
    @FXML
    private Button buttonOsobaZmienic;
    @FXML
    void
    actionOsobaEdytowac(ActionEvent event){
        ConnectionDB connectionDB = new ConnectionDB();
        String name;
        String lastName;
        String pesel;
        String email;

        String nameNew;
        String lastNameNew;
        String emailNew;
        String peselNew;

        String type;

        name = osobaName.getText();
        lastName = osobaLastname.getText();
        email = osobaEmail.getText();
        pesel = osobaPesel.getText();

        nameNew = osobaNameNew.getText();
        lastNameNew = osobaLastnameNew.getText();
        emailNew = osobaEmailNew.getText();
        peselNew = osobaPeselNew.getText();
        type = osobaType.getText();



        if (type.equals("pracownik") || type.equals("czytelnik")) {
            System.out.println(type);
            if (!name.isEmpty() && !lastName.isEmpty() && !pesel.isEmpty() && !email.isEmpty()) {
                System.out.println(name);
                if (!nameNew.isEmpty() || !lastNameNew.isEmpty() || !peselNew.isEmpty() || !emailNew.isEmpty()) {
                    System.out.println(lastNameNew);
                    try {
                        Boolean stan;
                        stan = connectionDB.changeCzytelnik(name, lastName, email, pesel, type, nameNew, lastNameNew, emailNew, peselNew);
                        System.out.println(stan);
                        if (stan){
                            labelOsobaInform.setText("Dane zmienione");

                            osobaName.setText("");
                            osobaLastname.setText("");
                            osobaEmail.setText("");
                            osobaPesel.setText("");

                            osobaNameNew.setText("");
                            osobaLastnameNew.setText("");
                            osobaEmailNew.setText("");
                            osobaPeselNew.setText("");

                            osobaType.setText("");

                        }else{
                            labelOsobaInform.setText("Błąd zmiany danych");
                        }

                    } catch (Exception e) { e.printStackTrace(); }

                } else { labelOsobaInform.setText("Dodaj nowe dane"); }

            } else { labelOsobaInform.setText("Dodaj bieżące dane"); }

        }else {labelOsobaInform.setText("Źle podany typ");}

        connectionDB.closeConnection();
    }

    @FXML
    private Button buttonOsobaUsunac;
    @FXML
    void actionOsobaUsunac(ActionEvent event){
        ConnectionDB connectionDB = new ConnectionDB();

        int  result;
        String type;
        String name;
        String lastName;
        String email = null;
        String pesel;

        name = osobaName.getText();
        lastName = osobaLastname.getText();
        email = osobaEmail.getText();
        pesel  = osobaPesel.getText();
        type = osobaType.getText();

        try{
//            connectionDB.deletePerson(name,lastName,email,pesel,type);

            result = connectionDB.deletePerson(name,lastName,email,pesel,type);
            if (result == 0){
                labelOsobaInform.setText("Osoba usunięta");
            }else if (result > 0){
                labelOsobaInform.setText("Błąd usuwania "+ result + " nieoddanych egzemplarze");
            }else if (result == -1){
                labelOsobaInform.setText("Błąd usuwania");
            }
        }catch (Exception e){
            e.printStackTrace();
            labelOsobaInform.setText("Błąd usuwania");
        }


        connectionDB.closeConnection();
    }

    @FXML
    private Button buttonOsobaPracownikZnalezc;
    @FXML
    void actionOsobaPracownikZnalezc(ActionEvent event){
        ConnectionDB connectionDB = new ConnectionDB();

        ObservableList<Pracownicy> pracownicies =   FXCollections.observableArrayList();
        String name;
        String lastName;
        String email = null;
        String pesel;

        name = osobaName.getText();
        lastName = osobaLastname.getText();
        email = osobaEmail.getText();
        pesel  = osobaPesel.getText();

        if (!name.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty() || !email.isEmpty()) {
            labelOsobaInform.setText("");

            pracownicies = connectionDB.populationTablePracownicy(name, lastName, pesel, email);
            if (pracownicies.size()>0) {
                tableOsobaPracowniki.setItems(pracownicies);
            }else {
                labelOsobaInform.setText("Nie ma takiej osoby");
                osobaName.setText("");
                osobaLastname.setText("");
                osobaEmail.setText("");
                osobaPesel.setText("");
            }
        }else { labelOsobaInform.setText("Dadoj dane"); }


        connectionDB.closeConnection();
    }

    @FXML
    private Button buttonOsobaZnalezc;
    @FXML
    void actionOsobaZnalezc(ActionEvent event){}

    //tables
    @FXML
    private TableView tableOsobaPracowniki;

    @FXML
    private TableView tableOsobaCzytelniki;


    @FXML
    void actionWydacKsiazke(ActionEvent event) {   //wydawanie ksiazek
        Calendar terminOddania = null;
        String message;

       String peselCzyt = pesel_czytelnika_field.getText();;
       String nameCzytelnik = name_czytelnika_field.getText();
       String lastNameCzytelnik = lastName_czytelnika_field.getText();

       Integer rokBook = null;
       String rok_book = wyporz_rokKsiazki_field.getText();
       String autorBook = wyporz_autorKsiazki_field1.getText();
       String nameBook = wyporz_nazwaKsiazki_field.getText();


        try {
            if (rok_book == ""){
                rokBook = null;
            }else {
                rokBook = Integer.valueOf(wyporz_rokKsiazki_field.getText());
            }
        } catch (NumberFormatException e) {
            labelWypozBook.setText("Zly ROK");
            e.printStackTrace();
        }


        if (!nameCzytelnik.isEmpty() && !lastNameCzytelnik.isEmpty() && !peselCzyt.isEmpty()) {
            if (!nameBook.isEmpty() && !autorBook.isEmpty()) {

                try {

                    LocalDate now = LocalDate.now();
                    LocalDate ld = wyporz_terminOddania.getValue();

                    terminOddania = Calendar.getInstance();
                    terminOddania.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());

                    if (ld.isAfter(now)){//sprawdzanie terminu oddania

                        try {
                            ConnectionDB connectionDB = new ConnectionDB();

                            message =  connectionDB.giveBook(this.pracownik,
                                    peselCzyt, nameCzytelnik, lastNameCzytelnik,
                                    rokBook, nameBook, autorBook,
                                    terminOddania);

                            connectionDB.closeConnection();
                            labelWypozSuccessful.setText(message);
                        } catch (Exception e) {
                            labelWypozSuccessful.setText("Nie udalo sie wydac");
                            e.printStackTrace();
                        }

                    }else{ labelWypozData.setText("Change date"); }

                } catch (Exception e) {
                    labelWypozData.setText("Add date");
                    e.printStackTrace();
                }


            } else { labelWypozBook.setText("Mało danych"); }

        } else { labelWypozCzytelnik.setText("Mało danych"); }
    }


    @FXML
    void actionOdebracBook(ActionEvent event) {
        String name = historyImieCzytelnika.getText();
        historyImieCzytelnika.setText("");
        String lastName = historyNazwiskoCzytelnika.getText();
        historyNazwiskoCzytelnika.setText("");

        ConnectionDB connectionDB = new ConnectionDB();

    }


    @FXML
    void actionFindBook(ActionEvent event) {

        ConnectionDB connectionDB = new ConnectionDB();

        String name;
        String autor;
        Integer rok = null;
        String rok_wypozyczenia;


        //przycisk z czesci ksiazki
        if (event.getSource() == szukac_ksiazke_button2) {

            System.out.println("Czesc ksiazki");

            name = nazwaKsiazki_field.getText();
            autor = autorKsiazki_field.getText();
            rok_wypozyczenia = rokKsiazki_field.getText();

            try{
                if (rok_wypozyczenia == ""){

                    rok = null;
                }else { rok = Integer.valueOf(rok_wypozyczenia); }

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Blad konvertacji roku");
            }


            if (!name.isEmpty() || !autor.isEmpty() || !rok.equals(null) )  {

//                pracTabViewKsiazki.getItems().removeAll();

                pracTabViewKsiazki.setItems(connectionDB.populationTableBook(name, autor, rok));

                pracTabViewKsiazki.refresh();

                nazwaKsiazki_field.setText("");
                autorKsiazki_field.setText("");
                rokKsiazki_field.setText("");

            } else {    labelBookInf.setText("Podaj dane");    }



            //przycisk z czesci wypozyczenia
        } else if (event.getSource() == buttonWyporzSzukBook) {

            System.out.println("Czesc wypozyczenia");

            name = wyporz_nazwaKsiazki_field.getText();
            autor = wyporz_autorKsiazki_field1.getText();
            rok_wypozyczenia = wyporz_rokKsiazki_field.getText();

            try{
                if (rok_wypozyczenia == ""){

                    rok = null;
                }else { rok = Integer.valueOf(rok_wypozyczenia); }

            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Blad konvertacji roku");
            }


            if (!name.isEmpty() || !autor.isEmpty() || !rok.equals(null)) {

                tableWypozKsiazki.setItems(connectionDB.populationTableBook(name, autor,rok));

                wyporz_nazwaKsiazki_field.setText("");
                wyporz_autorKsiazki_field1.setText("");
                wyporz_rokKsiazki_field.setText("");

            } else {  labelWypozBook.setText("Podaj dane");   }
        }

        connectionDB.closeConnection();
    }

    @FXML
    private Label labelOsobaInform;

    @FXML
    private Button buttonWyporzSzukCzyt;

    @FXML
    void actionFindCzytelnik(ActionEvent event) {
        ConnectionDB connectionDB = new ConnectionDB();
        ObservableList<Czytelnicy> czytelnicies =   FXCollections.observableArrayList();
        String name;
        String lastName;
        String email = null;
        String pesel;


        //Przycisk z panelu DANE OSOBOWE
        if (event.getSource() == buttonOsobaZnalezc){
            name = osobaName.getText();
            lastName = osobaLastname.getText();
            email = osobaEmail.getText();
            pesel  = osobaPesel.getText();

            if (!name.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty() || !email.isEmpty()){
                labelOsobaInform.setText("");

                czytelnicies = connectionDB.populationTableCzytelnicy(name, lastName, pesel, email);
                if (czytelnicies.size()>0) {
                    tableOsobaCzytelniki.setItems(czytelnicies);
                }else {
                    labelOsobaInform.setText("Nie ma takiej osoby");
                    osobaName.setText("");
                    osobaLastname.setText("");
                    osobaEmail.setText("");
                    osobaPesel.setText("");
                }
            }else { labelOsobaInform.setText("Dadoj dane"); }



            //Przycisk z panelu WYPOZYCZENIA
        }else if (event.getSource() == buttonWyporzSzukCzyt) {

            name = name_czytelnika_field.getText();
            lastName = lastName_czytelnika_field.getText();
            pesel = pesel_czytelnika_field.getText();


            if (!name.isEmpty() || !lastName.isEmpty() || !pesel.isEmpty()) {
                czytelnicies = connectionDB.populationTableCzytelnicy(name, lastName, pesel, email);
                if (czytelnicies.size()>0) {
                    tableWypozCzyt.setItems(czytelnicies);
                }else {
                    labelWypozCzytelnik.setText("Nie ma takiej osoby");
                    name_czytelnika_field.setText("");
                    lastName_czytelnika_field.setText("");
                    pesel_czytelnika_field.setText("");
                }



            } else { labelWypozCzytelnik.setText("Podaj dane"); }
        }
        connectionDB.closeConnection();
    }



    @FXML
    void actionLogOutButton(ActionEvent event) {
        logOutButton.getScene().getWindow().hide();

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
    void actionFind_historie(ActionEvent event) {
        ConnectionDB connectionDB = new ConnectionDB();

        String name =historyImieCzytelnika.getText();
        String lastName = historyNazwiskoCzytelnika.getText();

        if (!name.isEmpty() || !lastName.isEmpty()) {

            try{
                pracTableViewHistory.setItems(connectionDB.findHistory(name, lastName));
                pracHistoryLabel.setText("");
            } catch (Exception e){
                e.printStackTrace();
                pracHistoryLabel.setText("Nie ma takich danych");
            }
        }else { pracHistoryLabel.setText("Podaj dane"); }

        historyImieCzytelnika.setText("");
        historyNazwiskoCzytelnika.setText("");

        connectionDB.closeConnection();
    }

    @FXML
    void actionDodacKsiazke(ActionEvent event) {
        ConnectionDB connect = new ConnectionDB();

        int ilosc_egzemplarze = 0;
        int rokWydania=0;
        String nazwaKsiazki = nazwaKsiazki_field.getText();
        String autorKsiazi = autorKsiazki_field.getText();



        try{
            ilosc_egzemplarze = Integer.parseInt(iloscKsiazek_field.getText());
            rokWydania = Integer.parseInt(rokKsiazki_field.getText());
        }catch (Exception e ){
            labelBookInf.setText("Błąd podania liczby");

        }



        if (!nazwaKsiazki.isEmpty() && !autorKsiazi.isEmpty() && ilosc_egzemplarze > 0){

            connect.newBook(nazwaKsiazki, rokWydania, autorKsiazi, ilosc_egzemplarze);
            labelBookInf.setText("Dodano nowa informacje");
        }else { labelBookInf.setText("Błąd podania nazwy lub autora"); }

        connect.closeConnection();
    }


    @FXML
    void initialize() {
        rokKsiazki_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                rokKsiazki_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        iloscKsiazek_field.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                iloscKsiazek_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        osobaPeselNew.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                iloscKsiazek_field.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });




        //define tablevie for table books ksiazki
        columnBookName.setCellValueFactory(
                new PropertyValueFactory<Ksiazki, String>("nazwa"));
        columnBookAutor.setCellValueFactory(
                new PropertyValueFactory<Ksiazki, String>("autor"));
        columnBookRok.setCellValueFactory(
                new PropertyValueFactory<Ksiazki, Integer>("rok_wydania"));

        columnBookDelete.setSortable(false);
        columnBookDelete.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
                    @Override
                    public ObservableValue call(TableColumn.CellDataFeatures param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                }
        );
        columnBookDelete.setCellFactory(
                new Callback<TableColumn<Ksiazki, Boolean>, TableCell<Ksiazki, Boolean>>() {
                    @Override
                    public TableCell<Ksiazki, Boolean> call(TableColumn<Ksiazki, Boolean> param) {
                        return new ButtonCellKsiazkiDelete(pracTabViewKsiazki);
                    }
                }
        );



        columnBookInf.setSortable(false);
        columnBookInf.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Ksiazki, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Ksiazki, Boolean> param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                });
        columnBookInf.setCellFactory(
                new Callback<TableColumn<Ksiazki, Boolean>, TableCell<Ksiazki, Boolean>>() {
                    @Override
                    public TableCell<Ksiazki, Boolean> call(TableColumn<Ksiazki, Boolean> param) {
                        return new ButtonCellKsiazki(pracTabViewKsiazki);
                    }
                }
        );



        //define tableview in table czytelnicy wypozyczenia
        tableWyporzPeselCzytelnika.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("pesel"));
        tableWyporzNameCzyt.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("name"));
        tableWyporzLastNameCzyt.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("last_name"));

        tableWyporzActionCzyt.setSortable(false);
        tableWyporzActionCzyt.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Czytelnicy, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Czytelnicy, Boolean> param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                }
        );
        tableWyporzActionCzyt.setCellFactory(
                new Callback<TableColumn<Czytelnicy, Boolean>, TableCell<Czytelnicy, Boolean>>() {

                    @Override
                    public TableCell<Czytelnicy, Boolean> call(TableColumn<Czytelnicy, Boolean> param) {
                        return new ButtonCellKsiazkiWyp(tableWypozCzyt);
                    }
                }
        );


        //define tableview for books wypozyczenia
        tableWyporzIdNameBook.setCellValueFactory(
                new PropertyValueFactory<Ksiazki, String>("nazwa"));
        tableWyporzAutorBook.setCellValueFactory(
                new PropertyValueFactory<Ksiazki, Integer>("autor"));
        tableWyporzRokBook.setCellValueFactory(
                new PropertyValueFactory<Ksiazki, Integer>("rok_wydania"));


        tableWyporzActionBook.setSortable(false);
        tableWyporzActionBook.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Ksiazki, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Ksiazki, Boolean> param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                }
        );
        tableWyporzActionBook.setCellFactory(
                new Callback<TableColumn<Ksiazki, Boolean>, TableCell<Ksiazki, Boolean>>() {
                    @Override
                    public TableCell<Ksiazki, Boolean> call(TableColumn<Ksiazki, Boolean> param) {
                        return new ButtonCellBookWyp(tableWypozKsiazki);
                    }
                }
        );




        //define tableview for history

        column_historyIdWyp.setCellValueFactory(
                new PropertyValueFactory<Wypozyczenia, Long>("id_wypozyczenia"));

        column_dataOdebrania.setCellValueFactory(
                new PropertyValueFactory<Wypozyczenia, Calendar>("data_wypozyczenia"));
        column_dataOdebrania.setCellFactory(ButtonCellWypozyczeniaCalendar());

        column_termin.setCellValueFactory(
                new PropertyValueFactory<Wypozyczenia, Calendar>("termin"));
        column_termin.setCellFactory(ButtonCellWypozyczeniaCalendar());

        column_dataOddania.setCellValueFactory(
                new PropertyValueFactory<Wypozyczenia, Calendar>("data_zwrotu"));
        column_dataOddania.setCellFactory(ButtonCellWypozyczeniaCalendar());

        column_historyAct.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Wypozyczenia, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Wypozyczenia, Boolean> param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                }
        );
        column_historyAct.setCellFactory(
                new Callback<TableColumn<Wypozyczenia, Boolean>, TableCell<Wypozyczenia, Boolean>>() {
                    @Override
                    public TableCell<Wypozyczenia, Boolean> call(TableColumn<Wypozyczenia, Boolean> param) {
                        return new ButtonCellCzytelnicyHistory(pracTableViewHistory);
                    }
                }
        );


        //define teble for "DANE OSOBOWE" Czytelnicy
        tableOsobaPesel.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("pesel"));
        tableOsobaImie.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("name"));
        tableOsobaNazwisko.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("last_name"));

        tableOsobaButton.setSortable(false);
        tableOsobaButton.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Czytelnicy, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Czytelnicy, Boolean> param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                }
        );
        tableOsobaButton.setCellFactory(
                new Callback<TableColumn<Czytelnicy, Boolean>, TableCell<Czytelnicy, Boolean>>() {

                    @Override
                    public TableCell<Czytelnicy, Boolean> call(TableColumn<Czytelnicy, Boolean> param) {
                        return new ButtonCellOsobaCzyt(tableOsobaCzytelniki);
                    }
                }
        );


        //define teble for "DANE OSOBOWE" Pracownicy
        tableOsobaPracownikPesel.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("pesel"));
        tableOsobaPracownikImie.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("name"));
        tableOsobaPracownikNazwisko.setCellValueFactory(
                new PropertyValueFactory<Czytelnicy, String>("last_name"));

        tableOsobaPracownikButton.setSortable(false);
        tableOsobaPracownikButton.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Pracownicy, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Pracownicy, Boolean> param) {
                        return new SimpleBooleanProperty(param.getValue() != null);
                    }
                }
        );
        tableOsobaPracownikButton.setCellFactory(
                new Callback<TableColumn<Pracownicy, Boolean>, TableCell<Pracownicy, Boolean>>() {

                    @Override
                    public TableCell<Pracownicy, Boolean> call(TableColumn<Pracownicy, Boolean> param) {
                        return new ButtonCellOsobaPrac(tableOsobaPracowniki);
                    }
                }
        );


    }



    public void setText(Pracownicy pracownik) {

        this.pracownik = pracownik;
        this.lable_name_user.setText(this.pracownik.getName()
                    + " " + this.pracownik.getLast_name());
    }


    //Define the button cell for table ksizka

    private class ButtonCellKsiazki extends TableCell<Ksiazki, Boolean> {

        final Button cellButton = new Button("Edycja");

        ButtonCellKsiazki(final TableView tblView) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    ConnectionDB connectionDB = new ConnectionDB();
                    Egzemplarze exampl;

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/scenes/tableInformation_scene.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();


                    int selectdIndex = getTableRow().getIndex();
                    System.out.println("teble selected index = " + selectdIndex);
//
//                    //Create a new table show details of the selected item
                    ksiazka = (Ksiazki) pracTabViewKsiazki.getItems().get(selectdIndex);
                    System.out.println("Name is " + ksiazka.getNazwa());




                    exampl = connectionDB.stateEgzemplarze(ksiazka.getNazwa(), ksiazka.getAutor(), selectdIndex);


                    TableInfController tablInform = loader.getController();
                    tablInform.setText(ksiazka , exampl, selectdIndex);

                    connectionDB.closeConnection();
                }
            });
        }

        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }



    //Table cell dla usuwania ksiazek
    private class ButtonCellKsiazkiDelete extends TableCell<Ksiazki, Boolean> {

        final Button cellButton = new Button("X");

        ButtonCellKsiazkiDelete(final TableView tblView) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    ConnectionDB connectionDB = new ConnectionDB();
                    Egzemplarze exampl;



                    int selectdIndex = getTableRow().getIndex();
                    System.out.println("teble selected index = " + selectdIndex);
//
//                    //Create a new table show details of the selected item
                    ksiazka = (Ksiazki) pracTabViewKsiazki.getItems().get(selectdIndex);
                    System.out.println("Name is " + ksiazka.getNazwa());


                    exampl = connectionDB.stateEgzemplarze(ksiazka.getNazwa(), ksiazka.getAutor(), selectdIndex);
                    if (exampl.isStan() == true){
                        connectionDB.deleteBook(ksiazka, selectdIndex);
                        labelBookInf.setText("Książka usunięta");
                    }else{
                        labelBookInf.setText("Błąd usuwania książki");
                    }

                    connectionDB.closeConnection();


//                    void actionDelete(ActionEvent event) {
//                        ConnectionDB connectionDB = new ConnectionDB();
//
//                        System.out.println(this.ksiazka.getNazwa());
//                        System.out.println(this.ksiazka.getAutor());
//                        System.out.println(this.ksiazka.getRok_wydania());
//                        System.out.println("SelectedExample " + this.selectedExample);
//
//                        if (this.egzemplarz.isStan() == true) {
//                            try {
//                                connectionDB.deleteBook(this.ksiazka, this.selectedExample);
//                                bookInfLable.setText("deleted");
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                bookInfLable.setText("eror deleting book");
//                            }
//                        }else {
//                            bookInfLable.setText("state if false");
//                        }
//
//                        connectionDB.closeConnection();
//                    }
                }
            });
        }

        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }



    //Define the button cell for table czytelniki
    private class ButtonCellKsiazkiWyp extends TableCell<Czytelnicy, Boolean> {

        final Button cellButton = new Button("Take");

        ButtonCellKsiazkiWyp(final TableView<Czytelnicy> table) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int selectedIndex = getTableRow().getIndex();
                    System.out.println("Selected index = " + selectedIndex);

                    Czytelnicy czytelnik = (Czytelnicy) tableWypozCzyt.getItems().get(selectedIndex);

                    pesel_czytelnika_field.setText(String.valueOf(czytelnik.getPesel()));
                    name_czytelnika_field.setText(czytelnik.getName());
                    lastName_czytelnika_field.setText(czytelnik.getLast_name());
                }

            });
        }

        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }

    //Define the button cell for table "tableOsobaCzytelniki"!!!!!!!!!!!!!
    private class ButtonCellOsobaCzyt extends TableCell<Czytelnicy, Boolean> {

        final Button cellButton = new Button("Wziąśćc");

        ButtonCellOsobaCzyt(final TableView<Czytelnicy> table) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int selectedIndex = getTableRow().getIndex();
                    System.out.println("Selected index = " + selectedIndex);

                    Czytelnicy czytelnik = (Czytelnicy) tableOsobaCzytelniki.getItems().get(selectedIndex);

                    osobaName.setText(czytelnik.getName());
                    osobaLastname.setText(czytelnik.getLast_name());
                    osobaEmail.setText(czytelnik.getEmail());
                    osobaPesel.setText(czytelnik.getPesel());
                    osobaType.setText("czytelnik");
                }

            });
        }

        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }


    //Define the button cell for table "tableOsobaCzytelniki"!!!!!!!!!!!!!
    private class ButtonCellOsobaPrac extends TableCell<Pracownicy, Boolean> {

        final Button cellButton = new Button("Wziąśćc");

        ButtonCellOsobaPrac(final TableView<Czytelnicy> table) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int selectedIndex = getTableRow().getIndex();
                    System.out.println("Selected index = " + selectedIndex);

                    Pracownicy pracownik= (Pracownicy) tableOsobaPracowniki.getItems().get(selectedIndex);

                    osobaName.setText(pracownik.getName());
                    osobaLastname.setText(pracownik.getLast_name());
                    osobaEmail.setText(pracownik.getEmail());
                    osobaPesel.setText(pracownik.getPesel());
                    osobaType.setText("pracownik");
                }

            });
        }

        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }







    //Define the button cell for table czytelniki
    private class ButtonCellBookWyp extends TableCell<Ksiazki, Boolean> {
        final Button cellButton = new Button("Wziąć");

        ButtonCellBookWyp(final TableView<Ksiazki> table) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    int selectedIndex = getTableRow().getIndex();
                    System.out.println("Selected index = " + selectedIndex);

                    Ksiazki book = (Ksiazki) tableWypozKsiazki.getItems().get(selectedIndex);

                    wyporz_nazwaKsiazki_field.setText(String.valueOf(book.getNazwa()));
                    wyporz_rokKsiazki_field.setText(String.valueOf(book.getRok_wydania()));
                    wyporz_autorKsiazki_field1.setText(book.getAutor());
                }
            });
        }

        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }


    //define tableCell for tableview in history for cell with calendar data
    private  Callback<TableColumn<Wypozyczenia, Calendar>, TableCell<Wypozyczenia, Calendar>> ButtonCellWypozyczeniaCalendar(){
        final DateFormat dateFormat = DateFormat.getDateInstance();

        return new Callback<TableColumn<Wypozyczenia, Calendar>, TableCell<Wypozyczenia, Calendar>>() {

            @Override
            public TableCell<Wypozyczenia, Calendar> call(TableColumn<Wypozyczenia, Calendar> param) {
                TableCell<Wypozyczenia, Calendar> cell = new TableCell<Wypozyczenia, Calendar>() {

                    @Override
                    protected void updateItem(Calendar item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null) {
                            setText(null);
                        } else {
                            setText(dateFormat.format(item.getTime()));
                        }
                    }
                };
                return cell;
            }
        };
    }





    //Define the button cell for table "HISTORY"
    private class ButtonCellCzytelnicyHistory extends TableCell<Wypozyczenia, Boolean> {

        final Button cellButton = new Button("Szczegóły");

        ButtonCellCzytelnicyHistory(final TableView<Wypozyczenia> table) {

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    int selectedIndex = getTableRow().getIndex();
                    System.out.println("Selected index = " + selectedIndex);
                    Wypozyczenia wypozyczenia= (Wypozyczenia) pracTableViewHistory.getItems().get(selectedIndex);

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/scenes/historyInformation_scene.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();


//
//                    //Open a new window and show details of the selected item
                    HistoryInfController historycontrol = loader.getController();

                    ConnectionDB connectionDB = new ConnectionDB();
                    Wypozyczenia wypoz = connectionDB.findHistoryDetails(wypozyczenia.getId_wypozyczenia());
                    connectionDB.closeConnection();

                    historycontrol.setText(wypoz.getCzytelnik(), wypoz.getPracownik(), wypoz.getEgzemplarz(), wypoz);
                }
            });
        }


        //        Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if (!empty) {
                setGraphic(cellButton);
            }
        }
    }
}