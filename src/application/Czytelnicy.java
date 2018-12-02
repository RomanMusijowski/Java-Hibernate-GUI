package application;


import javax.persistence.*;

@Entity
@Table(name = "Czytelnicy")
public class Czytelnicy {

    @Column(name = "id_czytelnika", unique = true)
    @Id
    @GeneratedValue
    private int id_czytelnika;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "email")
    private String email;

    public Czytelnicy(String name, String last_name, String pesel, String email) {
        this.name = name;
        this.last_name = last_name;
        this.pesel = pesel;
        this.email = email;
    }

    public Czytelnicy(){}

    public int getId_czytelnika() {
        return id_czytelnika;
    }

    public void setId_czytelnika(int id_czytelnika) {
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

    @Override
    public String toString() {
        return "Czytelnicy{" +
                "id_czytelnika=" + id_czytelnika +
                ", name='" + name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pesel='" + pesel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
