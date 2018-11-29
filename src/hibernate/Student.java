package hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="student")
public class Student implements Serializable {
    private static final long serialVersionUID = -300025L;


    @Column(name = "id_studenta", unique = true)
    @Id
    @GeneratedValue
    private int studentId;

    @Column(name = "imie_studenta")
    private String imie;

    @Column(name = "nazwisko_studenta")
    private String nazwisko;

    @Column(name = "nr_indeksu")
    private long nrIndeksu;

    @OneToMany(mappedBy = "student")
//    private List<Przedmioty> przedmioty;
    private List<Telefon> telefony;

    public Student(String imie, String nazwisko, long nrIndeksu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", nrIndeksu=" + nrIndeksu +
                '}';
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public long getNrIndeksu() {
        return nrIndeksu;
    }

    public void setNrIndeksu(long nrIndeksu) {
        this.nrIndeksu = nrIndeksu;
    }

}


