package application;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Pracownicy")
public class Pracownicy implements Serializable {

    private static final long SerialVersionUId = -300040L;


    @Column(name = "id_pracownika")
    @Id
    @GeneratedValue
    private long id_pracownika;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "email")
    private String email;

    @Column(name = "haslo")
    private String haslo;

    @OneToMany(mappedBy = "pracownik",fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST})
    private List<Wypozyczenia> wypozyczenia;

    public Pracownicy(String name, String last_name, String pesel, String email, List<Wypozyczenia> wypozyczenia) {
        this.name = name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.wypozyczenia = wypozyczenia;
    }

    public Pracownicy(String name, String last_name, String pesel, String email, String haslo) {
        this.name = name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.haslo = haslo;
    }

    public Pracownicy(){}

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public long getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }

    @Override
    public String toString() {
        return "Pracownicy{" +
                "id_pracownika=" + id_pracownika +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", email='" + email + '\'' +
                ", wypozyczenia=" + wypozyczenia +
                '}';
    }
}
