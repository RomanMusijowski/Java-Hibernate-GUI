package application;

import javax.persistence.*;

@Entity
@Table(name = "Pracownicy")
public class Pracownicy {

    @Column(name = "id_pracownika", unique = true)
    @Id
    @GeneratedValue
    private int id_pracownika;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "email")
    private String email;

    public Pracownicy(String name, String last_name, String pesel, String email) {
        this.name = name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
    }

    public Pracownicy(){

    }

    public int getId_pracownika() {
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

    @Override
    public String toString() {
        return "Pracowniki{" +
                "id_pracownika=" + id_pracownika +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
