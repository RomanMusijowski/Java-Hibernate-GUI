package hibernate;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Roman Local
 */

@Entity
@Table(name="przedmioty")
public class Przedmioty implements Serializable {

    private static final long SerialVersionUId = -300040L;

    @Id
    @GeneratedValue
    private int idPrzedmiotu;
    private String nazwaPrzedmiotu;

    @ManyToMany(mappedBy="przedmioty")
    private List<Student> studenci;

    public Przedmioty(int idPrzedmiotu, String nazwaPrzedmiotu, List<Student> studenci) {
        this.idPrzedmiotu = idPrzedmiotu;
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.studenci = studenci;
    }

    public Przedmioty(){};

    public int getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public void setIdPrzedmiotu(int idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }

    public List<Student> getStudenci() {
        return studenci;
    }

    public void setStudenci(List<Student> studenci) {
        this.studenci = studenci;
    }

    @Override
    public String toString() {
        return "Przedmioty{" + "idPrzedmiotu=" + idPrzedmiotu + ", nazwaPrzedmiotu=" + nazwaPrzedmiotu + ", studenci=" + studenci + '}';
    }

}
