package application;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Czytelnicy")
public class Czytelnicy implements Serializable {

    private static final long SerialVersionUId = -300035L;

    @Column(name = "id_czytelnika")
    @Id
    @GeneratedValue
    private long id_czytelnika;

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

    @OneToMany(mappedBy = "czytelnik",fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.PERSIST})
    private List<Wypozyczenia> wypozyczenia;

    public Czytelnicy(String name, String last_name, String pesel, String email, String haslo) {
        this.name = name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
        this.haslo = haslo;
    }





    public Czytelnicy(){}


    public long getId_czytelnika() {
        return id_czytelnika;
    }

    public void setId_czytelnika(long id_czytelnika) {
        this.id_czytelnika = id_czytelnika;
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

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public List<Wypozyczenia> getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(List<Wypozyczenia> wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }
}
