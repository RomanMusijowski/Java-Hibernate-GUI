package sample;

import application.Czytelnicy;
import application.Pracownicy;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class ConnectionDB{
    private String url = "cfg/hibernate.cfg.xml";
    private Configuration configuration = null;


    public ConnectionDB() {
        this.configuration = new Configuration().configure(url);
    }



    public void login(String email, String pesel){

        configuration.addAnnotatedClass(Czytelnicy.class);
        configuration.addAnnotatedClass(Pracownicy.class);

        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();


        Criteria criteria = session.createCriteria(Pracownicy.class);

        criteria.add(Restrictions.eq("email", email));
        criteria.add(Restrictions.and(Restrictions.eq("pesel", pesel)));
        Pracownicy pracownicy = (Pracownicy) criteria.uniqueResult();


        System.out.println(pracownicy.toString());





        session.close();

        factory.close();
    }
}
