package com.tpo.fizio.entity.fizioterapevt.model;

import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.termin.model.Termin;

import javax.persistence.*;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "FIZIOTERAPEVT")
public class Fizioterapevt {

    @Id
    @Column(name = "USERNAME_FIZIOTERAPEVTA", nullable = false, updatable = false, length = 100)
    private String username;

    @Column(name = "IME_FIZIOTERAPEVTA", length = 100)
    private String ime;

    @Column(name = "PRIIMEK_FIZIOTERAPEVTA", length = 100)
    private String priimek;

    @Column(name = "ULICA", length = 50)
    private String ulica;

    @Column(name = "HISNA_STEVILKA", length = 10)
    private String hisnaStevilka;

    @Column(name = "POSTNA_STEVILKA")
    private Integer postnaStevilka;

    @Column(name = "KRAJ", length = 50)
    private String kraj;

    @OneToMany(mappedBy = "fizioterapevt", cascade = CascadeType.ALL)
    private List<Pacient> pacienti;

    @OneToMany(mappedBy = "fizioterapevt", cascade = CascadeType.ALL)
    private List<Termin> termini;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getHisnaStevilka() {
        return hisnaStevilka;
    }

    public void setHisnaStevilka(String hisnaStevilka) {
        this.hisnaStevilka = hisnaStevilka;
    }

    public Integer getPostnaStevilka() {
        return postnaStevilka;
    }

    public void setPostnaStevilka(Integer postnaStevilka) {
        this.postnaStevilka = postnaStevilka;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public List<Pacient> getPacienti() {
        return pacienti;
    }

    public void setPacienti(List<Pacient> pacienti) {
        this.pacienti = pacienti;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}