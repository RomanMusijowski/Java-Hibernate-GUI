package application;

import hibernate.Przedmioty;
import hibernate.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main_hibernate {
    public static void main(String[] args) {
        Pracownicy prac = new Pracownicy("Roman","Kuszla","12345678901","rkuszla");

        Configuration configuration = new Configuration().configure("cfg/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Pracownicy.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

        SessionFactory factory = configuration.buildSessionFactory();

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println("~~~~~session completed~~~~~~");

        session.save(prac);

        transaction.commit();
        System.out.println("~~~~~Transaction completed~~~~~~");

        factory.close();
        session.close();
    }
}
