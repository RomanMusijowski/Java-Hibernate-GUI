package hibernate;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Queue;


public class HibernateStudentDB {
    public static void main(String[] args) {

//        Student stud1 = new Student("And", "grsgwr", 5439586);
//        Student stud2 = new Student("And", "grsgwr", 5439586);
//
//
//
//        Configuration configuration = new Configuration().configure("cfg/hibernate.cfg.xml");
//        configuration.addAnnotatedClass(Student.class);
//        con







//        List<Student> stud= null;
//
//        Configuration configuration = new Configuration().configure("cfg/hibernate.cfg.xml");
//        SessionFactory factory = configuration.buildSessionFactory();
//        Session session = factory.openSession();
//        session.beginTransaction();
//
//        CriteriaBuilder cb = session.getCriteriaBuilder();
//        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
//        Root<Student> root = cq.from(Student.class);
//        cq.select(root);
//
//        Query<Student> query = session.createQuery(cq);
//        List<Student> results = query.getResultList();
//
//
//         session.getTransaction().commit();
//
//        for (Student student : results) {
//            System.out.println(student.toString());
//        }


        Configuration configuration =
                new Configuration().configure("cfg/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Przedmioty.class);


        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.openSession();
        //Zapytanie pobiera z bazy danych encję klasy Student o identyfikatorze 1

        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("imie_studenta", "And"));
        Student student = (Student) criteria.uniqueResult();

        try {
            System.out.println(student.toString());
        }catch (Exception e){
            System.out.println("Nie ma takiego elementy w tablcy Studenci");

        }
         criteria = session.createCriteria(Przedmioty.class);
        criteria.add(Restrictions.eq("nazwaPrzedmiotu", "matematyka"));
        Przedmioty przedmiot = (Przedmioty)criteria.uniqueResult();


//        try{
            System.out.println(przedmiot.toString());
//        }catch (Exception e){
//            System.out.println("niema takiego el w tablicy Przedmioty");
//        }





        session.close();

        factory.close();


    }
}
