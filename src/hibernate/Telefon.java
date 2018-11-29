package hibernate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Telefon implements Serializable {
    private static final long serialVersionUID = -300030L;

    @Id
    @GeneratedValue
    private int id_telefonu;
    private String typ;
    private long numer;

    @ManyToOne
    @JoinColumn(name = "id_studenta")
    private Student student;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId_telefonu() {
        return id_telefonu;
    }

    public void setId_telefonu(int id_telefonu) {
        this.id_telefonu = id_telefonu;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public long getNumer() {
        return numer;
    }

    public void setNumer(long numer) {
        this.numer = numer;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Telefon{" +
                "id_telefonu=" + id_telefonu +
                ", typ='" + typ + '\'' +
                ", numer=" + numer +
                ", student=" + student +
                '}';
    }
}
