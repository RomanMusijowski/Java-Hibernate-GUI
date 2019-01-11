package controllers;

import application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ConnectionDB{

    private String url = "cfg/hibernate.cfg.xml";
    private Configuration configuration = null;
    private SessionFactory factory= null;
    private Session session = null;
    private Transaction transaction=null;
    private Pracownicy pracownik = null;
    private Czytelnicy czytelnik = null;


    public ConnectionDB() {
        this.configuration = new Configuration().configure(url);

        this.configuration.addAnnotatedClass(Pracownicy.class);
        this.configuration.addAnnotatedClass(Czytelnicy.class);
        this.configuration.addAnnotatedClass(Ksiazki.class);
        this.configuration.addAnnotatedClass(Egzemplarze.class);
        this.configuration.addAnnotatedClass(Wypozyczenia.class);

        this.factory = this.configuration.buildSessionFactory();
        this.session = this.factory.openSession();
        this.transaction = this.session.beginTransaction();
    }

    public void closeConnection(){
        this.transaction.commit();
        this.session.close();
        this.factory.close();
    }


    public void rejestracjaCzyt(Czytelnicy czytelnik){

        this.session.save(czytelnik);
        System.out.println("~~~~~Transaction completed~~~~~~");
    }


    public void rejestracjaPrac(Pracownicy pracownik){

        this.session.save(pracownik);
        System.out.println("~~~~~Transaction completed~~~~~~");
    }



    public boolean login(String email, String haslo) {

        //sprawdza czy  to pracownik, jezeli ta zwroci true
        try {

            Criteria criteria = this.session.createCriteria(Pracownicy.class);
            criteria.add(Restrictions.eq("email", email));
            criteria.add(Restrictions.and(Restrictions.eq("haslo", haslo)));

            this.pracownik = (Pracownicy) criteria.uniqueResult();
            System.out.println("ID " + this.pracownik.getId_pracownika() + ", Name " + this.pracownik.getName()
                    + ", Last name " + this.pracownik.getLast_name()
                    + ", pesel" + this.pracownik.getPesel() + ", haslo "
                    + this.pracownik.getHaslo() + ", email " + this.pracownik.getEmail());

            return true;

        } catch (Exception e) {
            System.out.println("Nie ma takiego pracownika");
        }
        return false;
    }



    public void newBook(String name, int rok, String autor, int iloscEgzemplarze){

        Criteria criteria = this.session.createCriteria(Ksiazki.class);
        criteria.add(Restrictions.eq("nazwa", name));
        criteria.add(Restrictions.and(Restrictions.eq("rok_wydania",rok)));
        criteria.add(Restrictions.and(Restrictions.eq("autor", autor)));

        List<Ksiazki> rezult = criteria.list();

        System.out.println("Size rezult list = " + rezult.size());

        //sprawdza czy sa takie ksiazki
        //jezeli sa, to dodaje do 1 nowe egzemplarze
        if (rezult.size()>0){

            Ksiazki ks = rezult.get(0);
            List<Egzemplarze> exampl_result = new ArrayList<>();

            for (int i =0;i<iloscEgzemplarze;i++){

                Egzemplarze egzemplarze = new Egzemplarze(true);

                egzemplarze.setKsiazka(ks);
                this.session.save(egzemplarze);
                exampl_result.add(egzemplarze);
                System.out.println("Item saved");
            }

            ks.addEgzemplarz(exampl_result);
            //mozliwe tutaj trzeba dodac this.session.save(ks);

        }else {
            //jezeli nie ma takiej ksiazk to stwarza nowa

            Ksiazki ksiazka = new Ksiazki(name, rok, autor);
            List<Egzemplarze> example = new ArrayList<>();

            for (int i = 0; i < iloscEgzemplarze; i++) {

                Egzemplarze egzemplarze = new Egzemplarze(true);
                egzemplarze.setKsiazka(ksiazka);

                this.session.save(egzemplarze);

                example.add(egzemplarze);
                System.out.println("Item saved");
            }

            ksiazka.setEgzemplarz(example);
            this.session.save(ksiazka);
        }
    }





//
//    public int findCzytelnik(String name, String  lastName, long id){
//        try {
//            configuration.addAnnotatedClass(CzytelnikController.class);
//
//            SessionFactory factory = configuration.buildSessionFactory();
//            Session session = factory.openSession();
//
//            Criteria criteria = session.createCriteria(Czytelnicy.class);
//            criteria.add(Restrictions.eq("id_czytelnika", id));
//            criteria.add(Restrictions.and(Restrictions.eq("name", name)));
//            criteria.add(Restrictions.and(Restrictions.eq("last_name", lastName)));
//
//            Czytelnicy czyt = (Czytelnicy) criteria.uniqueResult();
//            czyt.toString();
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }



    //wydawanie ksiazek
    public String giveBook(Pracownicy pracownik,
                         String peselCzytelnika, String nameCzytelnik, String lastNameCzytelnik,
                         Integer rokBook, String nameBook, String autorBook,
                         Calendar dateZwrotu){



        Criteria czytelnikCrit = this.session.createCriteria(Czytelnicy.class);
        czytelnikCrit.add(Restrictions.eq("pesel", peselCzytelnika));
        czytelnikCrit.add(Restrictions.and(Restrictions.eq("name", nameCzytelnik)));
        czytelnikCrit.add(Restrictions.and(Restrictions.eq("last_name", lastNameCzytelnik)));

        Criteria bookCrit = this.session.createCriteria(Ksiazki.class);
        bookCrit.add(Restrictions.eq("rok_wydania",rokBook));
        bookCrit.add(Restrictions.and(Restrictions.eq("nazwa",nameBook)));
        bookCrit.add(Restrictions.and(Restrictions.eq("autor", autorBook)));

        Czytelnicy czytelnik = (Czytelnicy) czytelnikCrit.uniqueResult();
        Ksiazki ksiazka = (Ksiazki) bookCrit.uniqueResult();


        if (!czytelnik.equals(null)){
            if (!ksiazka.equals(null)){

                Wypozyczenia wypozyczenia = new Wypozyczenia();

                wypozyczenia.setPracownik(pracownik);
                wypozyczenia.setCzytelnik(czytelnik);
                wypozyczenia.setData_wypozyczenia(Calendar.getInstance());
                wypozyczenia.setTermin(dateZwrotu);

                //sprawdza dostepnosc egzemplarze
                for (int i = 0; i<ksiazka.getEgzemplarz().size(); i++) {

                    if (ksiazka.getEgzemplarz().get(i).isStan() == true) {

                        Egzemplarze egzemplarz = ksiazka.getEgzemplarz().get(i);
                        ksiazka.getEgzemplarz().get(i).setStan(false);
                        wypozyczenia.setEgzemplarz(egzemplarz);
                        break;
                    }
                }
                if (wypozyczenia.getEgzemplarz() != null){

                    this.session.save(wypozyczenia);
                    System.out.println("Wypozyczenia zapisane");
                    return "Wypozyczenia zapisane";

                }else { System.out.println("~~~~Nie ma dostepnych egzemplarze~~~~~~~~~");
                return "Nie ma dostepnych egzemplarze"; }

            }else { System.out.println("~~~Eror book~~~~~");
                return "błąd ksiązki"; }

        }else { System.out.println("~~~~~Nie ma takiej osoby~~~~~~");
            return "Nie ma takiej osoby";}

    }


    //Population tableview of Ksiazki
    public ObservableList<Ksiazki> populationTableBook(String name, String autor, Integer rok) {

        ObservableList<Ksiazki> data;
        data = FXCollections.observableArrayList();

        Criteria criteria = this.session.createCriteria(Ksiazki.class);

        if (!name.isEmpty()){

            criteria.add(Restrictions.like("nazwa",name+"%"));
        }else if (!autor.isEmpty()){

            criteria.add(Restrictions.and(Restrictions.like("autor",autor+"%")));
        }else if (!rok.equals(null)){

            criteria.add(Restrictions.and(Restrictions.like("rok_wydania",rok)));
        }

        List<Ksiazki> examples = criteria.list();
        System.out.println("List  size = " + examples.size());

        for (int i = 0; i < examples.size(); i++) {

            Ksiazki ksiazki = new Ksiazki();
            ksiazki.setNazwa(examples.get(i).getNazwa());
            ksiazki.setAutor(examples.get(i).getAutor());
            ksiazki.setRok_wydania(examples.get(i).getRok_wydania());
            data.add(ksiazki);
            System.out.println("Book ("+i+") "+ksiazki.toString());
        }
        System.out.println("~~~~~Returned data~~~~~~~");
        return data;
    }


    //Population tableview of Czytelniki
    public ObservableList<Czytelnicy> populationTableCzytelnicy(String name, String lastName, String pesel, String email) {
        ObservableList<Czytelnicy> data;
        data = FXCollections.observableArrayList();

        Criteria criteria = this.session.createCriteria(Czytelnicy.class);

        if (!name.isEmpty()) {
            criteria.add(Restrictions.like("name", name + "%"));
        }else if (!lastName.isEmpty()) {
            criteria.add(Restrictions.and(Restrictions.like("last_name", lastName + "%")));
        }else if (!pesel.isEmpty()) {
            criteria.add(Restrictions.and(Restrictions.like("pesel", pesel + "%")));
        }else if (!email.isEmpty()){
            criteria.add(Restrictions.and(Restrictions.like("email", email + "%")));

        }

        List<Czytelnicy> czytelnicy = criteria.list();
        System.out.println("List  size = " + czytelnicy.size());

        for (int i = 0; i < czytelnicy.size(); i++) {

            Czytelnicy czytelnik = new Czytelnicy();
            czytelnik.setPesel(czytelnicy.get(i).getPesel());
            czytelnik.setName(czytelnicy.get(i).getName());
            czytelnik.setLast_name(czytelnicy.get(i).getLast_name());
            czytelnik.setEmail(czytelnicy.get(i).getEmail());
            data.add(czytelnik);
            System.out.println("Czytelnik ("+i+") "+czytelnicy.toString());
        }
        System.out.println("~~~~~Returned data~~~~~~~");
        return data;
    }

    //Population tableview of Pracownikow
    public ObservableList<Pracownicy> populationTablePracownicy(String name, String lastName, String pesel, String email) {
        ObservableList<Pracownicy> data;
        data = FXCollections.observableArrayList();

        Criteria criteria = this.session.createCriteria(Pracownicy.class);

        if (!name.isEmpty()) {
            criteria.add(Restrictions.like("name", name + "%"));
        }else if (!lastName.isEmpty()) {
            criteria.add(Restrictions.and(Restrictions.like("last_name", lastName + "%")));
        }else if (!pesel.isEmpty()) {
            criteria.add(Restrictions.and(Restrictions.like("pesel", pesel + "%")));
        }else if (!email.isEmpty()){
            criteria.add(Restrictions.and(Restrictions.like("email", email + "%")));

        }

        List<Pracownicy> pracownicies = criteria.list();
        System.out.println("List  size = " + pracownicies.size());

        for (int i = 0; i < pracownicies.size(); i++) {

            Pracownicy pracownik = new Pracownicy();
            pracownik.setPesel(pracownicies.get(i).getPesel());
            pracownik.setName(pracownicies.get(i).getName());
            pracownik.setLast_name(pracownicies.get(i).getLast_name());
            pracownik.setEmail(pracownicies.get(i).getEmail());
            data.add(pracownik);
            System.out.println("Pracownik ("+i+") "+pracownik.toString());
        }
        System.out.println("~~~~~Returned data~~~~~~~");
        return data;
    }


    public ObservableList<Wypozyczenia> findHistory(String name,String lastName){
        ObservableList<Wypozyczenia> data = FXCollections.observableArrayList();

        Criteria czytCriteria  = this.session.createCriteria(Czytelnicy.class);
        if (!name.isEmpty()) {
            czytCriteria.add(Restrictions.like("name", name+"%"));
        }else if (!lastName.isEmpty()) {
            czytCriteria.add(Restrictions.and(Restrictions.like("last_name", lastName+"%")));
        }

        List<Czytelnicy> czytelnik = czytCriteria.list();
        List<Wypozyczenia> wypozyczenia = czytelnik.get(0).getWypozyczenia();

        for (int i = 0; i < wypozyczenia.size(); i++){
            Wypozyczenia wypoz = new Wypozyczenia();

            wypoz.setData_wypozyczenia(wypozyczenia.get(i).getData_wypozyczenia());
            wypoz.setTermin(wypozyczenia.get(i).getTermin());
            wypoz.setData_zwrotu(wypozyczenia.get(i).getData_zwrotu());
            wypoz.setId_wypozyczenia(wypozyczenia.get(i).getId_wypozyczenia());
            data.add(wypoz);
        }
        return data;
    }


    public Wypozyczenia findHistoryDetails(Long idWypozyczenia){
        Criteria criteria = this.session.createCriteria(Wypozyczenia.class);
        criteria.add(Restrictions.eq("id_wypozyczenia", idWypozyczenia));

        List<Wypozyczenia> wypozyczenia = criteria.list();
        return wypozyczenia.get(0);
    }


    public Egzemplarze stateEgzemplarze(String name, String autor, Integer index){

        Criteria criteria = this.session.createCriteria(Ksiazki.class);
        criteria.add(Restrictions.eq("nazwa",name));
        criteria.add(Restrictions.and(Restrictions.eq("autor", autor)));


        Ksiazki book = (Ksiazki) criteria.uniqueResult();
        List<Egzemplarze> exampls = book.getEgzemplarz();
        return exampls.get(index);
    }



    public void editBook(Ksiazki ksiazka, int selectedExample){
        //pobranie ksiazki
        Criteria bookcrit = this.session.createCriteria(Ksiazki.class);

        bookcrit.add(Restrictions.eq("nazwa", ksiazka.getNazwa()));
        bookcrit.add(Restrictions.and(Restrictions.eq("autor",ksiazka.getAutor())));
        bookcrit.add(Restrictions.and(Restrictions.eq("rok_wydania",ksiazka.getRok_wydania())));

        List<Ksiazki> ksiazki = bookcrit.list();
        Ksiazki ksiazkaOne = ksiazki.get(0);

        List<Egzemplarze> examples = ksiazkaOne.getEgzemplarz();

        examples.remove(selectedExample);
    }

    public void deleteBook(Ksiazki ksiazka,int index){
        Criteria criteria = this.session.createCriteria(Ksiazki.class);
        criteria.add(Restrictions.eq("nazwa", ksiazka.getNazwa()));
        criteria.add(Restrictions.and(Restrictions.eq("autor", ksiazka.getAutor())));
        criteria.add(Restrictions.and(Restrictions.eq("rok_wydania", ksiazka.getRok_wydania())));

        Ksiazki ksiazki = (Ksiazki) criteria.uniqueResult();
        List<Egzemplarze> egzemplarzes =ksiazki.getEgzemplarz();

        if (egzemplarzes.size() > 1) {

            egzemplarzes.remove(index);
            System.out.println("Deleted exampl");

        }else if (egzemplarzes.size() == 1){

            egzemplarzes.remove(index);
            System.out.println("Deleted exampl");
            try {
                this.session.delete(ksiazki);
                System.out.println("deleted book ");
            }catch (Exception e){
                System.out.println("eror delete book");
                e.printStackTrace();
            }
        }
    }

    public void extendWypozyczenie(Wypozyczenia wypozyczenie, Calendar date){
        try {
            wypozyczenie.setTermin(date);
            this.session.update(wypozyczenie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean takeWypozyczenie(Wypozyczenia wypozyczenia){
        try{
            wypozyczenia.setData_zwrotu(Calendar.getInstance());
            wypozyczenia.getEgzemplarz().setStan(true);
            this.session.update(wypozyczenia);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeCzytelnik(String imieOld, String nazwiskoOld, String emailOld, String peselOld, String type,
                                   String name, String lastName, String email, String pesel){
        Criteria criteria= null;
        Czytelnicy personCzyt= null;
        Pracownicy personPrac = null;

        if (type.equals("czytelnik")){
            criteria = this.session.createCriteria(Czytelnicy.class);
            System.out.println("To czytelnik");

        }else if (type.equals("pracownik")){
            criteria = this.session.createCriteria(Pracownicy.class);
            System.out.println("To pracownik");
        }

        criteria.add(Restrictions.eq("name", imieOld));
        criteria.add(Restrictions.and(Restrictions.eq("last_name", nazwiskoOld)));
        criteria.add(Restrictions.and(Restrictions.eq("email", emailOld)));
        criteria.add(Restrictions.and(Restrictions.eq("pesel", peselOld)));

        if (type.equals("czytelnik")){
            personCzyt = (Czytelnicy) criteria.uniqueResult();

            if (!name.isEmpty()) {
                personCzyt.setName(name);
            }else if (!lastName.isEmpty()) {
                personCzyt.setLast_name(lastName);
            }else  if (!email.isEmpty()) {
                personCzyt.setEmail(email);
            }else if (!pesel.isEmpty()) {
                personCzyt.setPesel(pesel);
            }

            this.session.update(personCzyt);
            return true;

        }else if (type.equals("pracownik")){
            personPrac = (Pracownicy)criteria.uniqueResult();

            if (!name.isEmpty()) {
                personPrac.setName(name);
            }else if (!lastName.isEmpty()) {
                personPrac.setLast_name(lastName);
            }else  if (!email.isEmpty()) {
                personPrac.setEmail(email);
            }else if (!pesel.isEmpty()) {
                personPrac.setPesel(pesel);
            }

            this.session.update(personPrac);
            return true;
        }
        return false;
    }

    public int deletePerson(String name, String lastName, String email, String pesel, String type){
        Criteria criteria= null;
        Czytelnicy personCzyt= null;
        Pracownicy personPrac = null;

        if (type.equals("czytelnik")){
            criteria = this.session.createCriteria(Czytelnicy.class);
            System.out.println("To czytelnik");

        }else if (type.equals("pracownik")){
            criteria = this.session.createCriteria(Pracownicy.class);
            System.out.println("To pracownik");
        }

        criteria.add(Restrictions.eq("name", name));
        criteria.add(Restrictions.and(Restrictions.eq("last_name", lastName)));
        criteria.add(Restrictions.and(Restrictions.eq("email", email)));
        criteria.add(Restrictions.and(Restrictions.eq("pesel", pesel)));



        if (type.equals("czytelnik")) {
            personCzyt = (Czytelnicy) criteria.uniqueResult();
            int ilosc = 0;

            for (int i = 0; i < personCzyt.getWypozyczenia().size(); i++) {

                if (personCzyt.getWypozyczenia().get(i).getEgzemplarz().isStan() == false) { //sprawdza ile jest nieoddanych
                    ilosc++;
                }
            }

            if (ilosc > 0) {       //jezeli sa nieoddane
                return ilosc;

            } else if (ilosc == 0) {
                List<Wypozyczenia> wypoz = personCzyt.getWypozyczenia();

                for (int i = 0; i < personCzyt.getWypozyczenia().size(); i++) {

                    this.session.remove(personCzyt.getWypozyczenia().get(i));
                    System.out.println("Wypozyczenie usuniete");
                }
                this.session.delete(personCzyt);
                System.out.println("czytelnik usuniety");
                return 0;
            }


        } else if (type.equals("pracownik")) {
            personPrac = (Pracownicy) criteria.uniqueResult();

            for (int i = 0; i < personPrac.getWypozyczenia().size(); i++) {

                this.session.remove(personPrac.getWypozyczenia().get(i));
                System.out.println("Wypozyczenie usuniete");
            }

            this.session.delete(personPrac);
            System.out.println("pracownik usuniety");
            return 0;
        }
        return -1;
    }


    public Pracownicy getPracownik() {
        return pracownik;
    }
}
