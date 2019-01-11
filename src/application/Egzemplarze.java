package application;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Egzemplarze")
public class Egzemplarze implements Serializable {

    private static final long SerialVersionUId = -300033L;

    @Id
    @GeneratedValue
    private long id_egzemplarza;
    private boolean stan = true;

    @ManyToOne
    @JoinColumn(name = "id_ksiazki")
    private Ksiazki ksiazka;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Wypozyczenia wypozyczenia;

    public Egzemplarze(boolean stan) {
        this.stan = stan;
    }

    public Egzemplarze(){}

    @Override
    public String toString() {
        return "Egzemplarze{" +
                "id_egzemplarza=" + id_egzemplarza +
                ", stan=" + stan +
                ", ksiazka=" + ksiazka +
                ", wypozyczenia=" + wypozyczenia +
                '}';
    }

    public long getId_egzemplarza() {
        return id_egzemplarza;
    }

    public void setId_egzemplarza(long id_egzemplarza) {
        this.id_egzemplarza = id_egzemplarza;
    }

    public boolean isStan() {
        return stan;
    }

    public void setStan(boolean stan) {
        this.stan = stan;
    }

    public Ksiazki getKsiazka() {
        return ksiazka;
    }

    public void setKsiazka(Ksiazki ksiazka) {
        this.ksiazka = ksiazka;
    }

    public Wypozyczenia getWypozyczenia() {
        return wypozyczenia;
    }

    public void setWypozyczenia(Wypozyczenia wypozyczenia) {
        this.wypozyczenia = wypozyczenia;
    }
}
