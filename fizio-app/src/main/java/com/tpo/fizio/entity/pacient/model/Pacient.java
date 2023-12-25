package com.tpo.fizio.entity.pacient.model;

import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.termin.model.Termin;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "PACIENT")
public class Pacient {

    @Id
    @Column(name = "USERNAME_PACIENTA", nullable = false, updatable = false, length = 100)
    private String username;

    @Column(name = "IME_PACIENTA", length = 100)
    private String ime;

    @Column(name = "PRIIMEK_PACIENTA", length = 100)
    private String priimek;

    @Column(name = "DATUM_ROJSTVA")
    private OffsetDateTime datumRojstva;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL)
    private List<FizioPlan> fizioplani;

    @ManyToOne
    @JoinColumn(name = "USERNAME_FIZIOTERAPEVTA", nullable = false)
    private Fizioterapevt fizioterapevt;

    @OneToMany(mappedBy = "pacient", cascade = CascadeType.ALL)
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

    public OffsetDateTime getDatumRojstva() {
        return datumRojstva;
    }

    public void setDatumRojstva(OffsetDateTime datumRojstva) {
        this.datumRojstva = datumRojstva;
    }

    public List<FizioPlan> getFizioplani() {
        return fizioplani;
    }

    public void setFizioplani(List<FizioPlan> fizioplani) {
        this.fizioplani = fizioplani;
    }

    public Fizioterapevt getFizioterapevt() {
        return fizioterapevt;
    }

    public void setFizioterapevt(Fizioterapevt fizioterapevt) {
        this.fizioterapevt = fizioterapevt;
    }

    public List<Termin> getTermini() {
        return termini;
    }

    public void setTermini(List<Termin> termini) {
        this.termini = termini;
    }
}

