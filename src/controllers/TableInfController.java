package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import application.Egzemplarze;
import application.Ksiazki;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TableInfController {

    private Integer selectedExample;
    private Ksiazki ksiazka;
    private Egzemplarze egzemplarz;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField bookInfRok;

    @FXML
    private TextField bookInfAutor;

    @FXML
    private Button bookInfDeleteButton;

    @FXML
    private Label bookInfLable;

    @FXML
    private TextField bookInfStan;

    @FXML
    private Label first_lable;

    @FXML
    private Button bookInfEdytowacButton;

    @FXML
    private TextField bookInfName;


    @FXML
    void f50000(ActionEvent event) {

    }

    @FXML
    void actionEdit(ActionEvent event) {
        ConnectionDB connectionDB = new ConnectionDB();

        System.out.println(this.ksiazka.getNazwa());
        System.out.println(this.ksiazka.getAutor());
        System.out.println(this.ksiazka.getRok_wydania());
        System.out.println("SelectedExample " + this.selectedExample);

        try{


            if (this.egzemplarz.isStan() == true) {
                connectionDB.editBook(this.ksiazka, selectedExample);
                System.out.println("Deleted book");

                this.ksiazka.setNazwa(bookInfName.getText());
                this.ksiazka.setAutor(bookInfAutor.getText());
                this.ksiazka.setRok_wydania(Integer.valueOf(bookInfRok.getText()));

                connectionDB.newBook(this.ksiazka.getNazwa(),this.ksiazka.getRok_wydania(),this.ksiazka.getAutor(),1);

                System.out.println("Created new book");
                bookInfLable.setText("Saved and deleted book");

            }else {
                bookInfLable.setText("state if false");
            }


        }catch (Exception e){
            e.printStackTrace();
            bookInfLable.setText("Edit eror");
        }
        connectionDB.closeConnection();
    }

//    @FXML
//    void actionDelete(ActionEvent event) {
//        ConnectionDB connectionDB = new ConnectionDB();
//
//        System.out.println(this.ksiazka.getNazwa());
//        System.out.println(this.ksiazka.getAutor());
//        System.out.println(this.ksiazka.getRok_wydania());
//        System.out.println("SelectedExample " + this.selectedExample);
//
//        if (this.egzemplarz.isStan() == true) {
//            try {
//                connectionDB.deleteBook(this.ksiazka, this.selectedExample);
//                bookInfLable.setText("deleted");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                bookInfLable.setText("eror deleting book");
//            }
//        }else {
//            bookInfLable.setText("state if false");
//        }
//
//        connectionDB.closeConnection();
//    }

    @FXML
    void initialize() {

    }

    public void setText(Ksiazki ksiazka, Egzemplarze egzemplarz, Integer indexExample){
        this.ksiazka = ksiazka;
        this.egzemplarz = egzemplarz;
        this.selectedExample = indexExample;

        bookInfName.setText(this.ksiazka.getNazwa());
        bookInfAutor.setText(this.ksiazka.getAutor());
        bookInfRok.setText(String.valueOf(this.ksiazka.getRok_wydania()));
        bookInfStan.setText(String.valueOf(this.egzemplarz.isStan()));
    }
}
