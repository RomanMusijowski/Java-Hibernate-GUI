package application;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "Wypozyczenia")
public class Wypozyczenia implements Serializable {

    private static final long SerialVersionUId = -300035L;

    @Id
    @GeneratedValue
    private long id_wypozyczenia;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_wypozyczenia;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_zwrotu;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar termin;

    @ManyToOne
    @JoinColumn(name = "id_czytelnika")
    private Czytelnicy czytelnik;

    @ManyToOne
    @JoinColumn(name = "id_pracownika")
    private Pracownicy pracownik;

    @OneToOne
    @JoinColumn(name = "id_egzemplarza")
    private Egzemplarze egzemplarz;

    public Wypozyczenia(Calendar data_wypozyczenia, Calendar termin, Czytelnicy czytelnik, Pracownicy pracownik, Egzemplarze egzemplarz) {
        this.data_wypozyczenia = data_wypozyczenia;
        this.termin = termin;
        this.czytelnik = czytelnik;
        this.pracownik = pracownik;
        this.egzemplarz = egzemplarz;
    }

    public Wypozyczenia(){}

    public long getId_wypozyczenia() {
        return id_wypozyczenia;
    }

    public void setId_wypozyczenia(long id_wypozyczenia) {
        this.id_wypozyczenia = id_wypozyczenia;
    }

    public Calendar getData_wypozyczenia() {
        return data_wypozyczenia;
    }

    public void setData_wypozyczenia(Calendar data_wypozyczenia) {
        this.data_wypozyczenia = data_wypozyczenia;
    }

    public Calendar getData_zwrotu() {
        return data_zwrotu;
    }

    public void setData_zwrotu(Calendar data_zwrotu) {
        this.data_zwrotu = data_zwrotu;
    }

    public Calendar getTermin() {
        return termin;
    }

    public void setTermin(Calendar termin) {
        this.termin = termin;
    }

    public Czytelnicy getCzytelnik() {
        return czytelnik;
    }

    public void setCzytelnik(Czytelnicy czytelnik) {
        this.czytelnik = czytelnik;
    }

    public Pracownicy getPracownik() {
        return pracownik;
    }

    public void setPracownik(Pracownicy pracownik) {
        this.pracownik = pracownik;
    }

    public Egzemplarze getEgzemplarz() {
        return egzemplarz;
    }

    public void setEgzemplarz(Egzemplarze egzemplarz) {
        this.egzemplarz = egzemplarz;
    }

    @Override
    public String toString() {
        return "Wypozyczenia{" +
                "id_wypozyczenia=" + id_wypozyczenia +
                ", data_wypozyczenia=" + data_wypozyczenia +
                ", data_zwrotu=" + data_zwrotu +
                ", termin=" + termin +
                ", czytelnik=" + czytelnik +
                ", pracownik=" + pracownik +
                ", egzemplarz=" + egzemplarz +
                '}';
    }
}
