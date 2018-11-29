package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateStudentDB {
    public static void main(String[] args) {

        Student stud1 = new Student("And", "grsgwr", 5439586);
        Student stud2 = new Student("And", "grsgwr", 5439586);

        Telefon telefon1 = new Telefon();
        telefon1.setTyp("mobilny");
        telefon1.setNumer(937485932);
        telefon1.setStudent(stud1);

        Telefon telefon2 = new Telefon();
        telefon2.setTyp("stacjonarny");
        telefon2.setNumer(937485932);
        telefon2.setStudent(stud2);

        Configuration configuration = new Configuration().configure("cfg./hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Telefon.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        session.save(stud1);
        session.save(stud2);
        session.save(telefon1);
        session.save(telefon2);

        transaction.commit();
        System.out.println("~~~~~Transaction completed~~~~~~");
        session.close(); sessionFactory.close();


    }
}
