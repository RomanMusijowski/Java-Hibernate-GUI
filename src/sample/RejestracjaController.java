package sample;

import java.net.URL;
import java.util.ResourceBundle;

import application.Czytelnicy;
import application.Pracownicy;
import hibernate.Student;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

public class RejestracjaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField email_field;

    @FXML
    private RadioButton pracownik_radio;

    @FXML
    private Button rejestracjaButton;

    @FXML
    private TextField pesel_field;

    @FXML
    private TextField name_field;


    @FXML
    private RadioButton czytelnik_radio;

    @FXML
    private TextField last_name_field;


    @FXML
    void actionCztylnikRadio(ActionEvent event) {
        if (event.getSource() == czytelnik_radio){
            czytelnik_radio.setSelected(true);
            pracownik_radio.setSelected(false);
        }
    }

    @FXML
    void actionPracownikRadio(ActionEvent event) {
        if (event.getSource() == pracownik_radio){
            pracownik_radio.setSelected(true);
            czytelnik_radio.setSelected(false);
        }
    }

    @FXML
    void ffe6e6(ActionEvent event) {

    }


    @FXML
    void initialize() {

        rejestracjaButton.setOnAction(event -> {

            String name = name_field.getText();
            name_field.setText(null);
            String last_name = last_name_field.getText();
            last_name_field.setText(null);
            String email = email_field.getText();
            email_field.setText(null);
            String pesel = pesel_field.getText();
            pesel_field.setText(null);


//            if (typ == "czytelnik" || typ == "Czytelnik") {
//                Czytelnicy czytelnik = new Czytelnicy(name,last_name,pesel,email);
//                Configuration configuration = new Configuration().configure("cfg/hibernate.cfg.xml");
//                configuration.addAnnotatedClass(Czytelnicy.class);
//
//                    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
//                            configuration.getProperties()).build();
//
//                        SessionFactory factory = configuration.buildSessionFactory();
//
//                            Session session = factory.openSession();
//                            Transaction transaction = session.beginTransaction();
//
//                                System.out.println("~~~~~session completed~~~~~~");
//
//                                session.save(czytelnik);
//
//                            transaction.commit();
//                            System.out.println("~~~~~Transaction completed~~~~~~");
//
//                        factory.close();
//                session.close();
//
//            }else if (typ == "Pracownik" || typ == "pracownik"){
//
//            }






        });
    }
}
