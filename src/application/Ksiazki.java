package application;

import javafx.scene.control.Button;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Ksiazki")
public class Ksiazki  implements Serializable {

    private static final long SerialVersionUId = -300030L;

    @Id
    @GeneratedValue
    private long id_ksiazki;
    private String nazwa;
    private int rok_wydania;
    private String autor;



    @OneToMany(mappedBy = "ksiazka",fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Egzemplarze> egzemplarz;

    public Ksiazki(String nazwa, int rok_wydania, String autor) {
        this.nazwa = nazwa;
        this.rok_wydania = rok_wydania;
        this.autor = autor;

    }

    public Ksiazki(long id_ksiazki, String nazwa, int rok_wydania, String autor){
        this.id_ksiazki = id_ksiazki;
        this.nazwa = nazwa;
        this.rok_wydania = rok_wydania;
        this.autor = autor;

    }

    public Ksiazki(){}

    public long getId_ksiazki() {
        return id_ksiazki;
    }

    public void setId_ksiazki(long id_ksiazki) {
        this.id_ksiazki = id_ksiazki;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getRok_wydania() {
        return rok_wydania;
    }

    public void setRok_wydania(int rok_wydania) {
        this.rok_wydania = rok_wydania;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Egzemplarze> getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(List<Egzemplarze> egzemplarz) {
        this.egzemplarz = egzemplarz;
    }

    public void addEgzemplarz(List<Egzemplarze> egzemplarz) {
        int size_coll = this.egzemplarz.size();
        this.egzemplarz.addAll(size_coll,egzemplarz);
    }





    @Override
    public String toString() {
        return "Ksiazki{" +
                "id_ksiazki=" + id_ksiazki +
                ", nazwa='" + nazwa + '\'' +
                ", rok_wydania=" + rok_wydania +
                ", autor='" + autor + '\'' +
                ", egzemplarz=" + egzemplarz +
                '}';
    }
}
