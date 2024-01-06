package com.tpo.fizio.entity.termin.model;

import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.obvestilo.model.Obvestilo;
import com.tpo.fizio.entity.pacient.model.Pacient;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "TERMIN")
public class Termin {

    @Id
    @Column(name = "ID_TERMINA", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ZACETEK")
    private OffsetDateTime zacetek;

    @Column(name = "KONEC")
    private OffsetDateTime konec;

    @Column(name = "JE_ZASEDEN")
    private Boolean jeZaseden;

    @OneToMany(mappedBy = "termin", cascade = CascadeType.ALL)
    private List<Obvestilo> obvestila;

    @ManyToOne
    @JoinColumn(name = "USERNAME_PACIENTA")
    private Pacient pacient;

    @ManyToOne
    @JoinColumn(name = "USERNAME_FIZIOTERAPEVTA", nullable = false)
    private Fizioterapevt fizioterapevt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getZacetek() {
        return zacetek;
    }

    public void setZacetek(OffsetDateTime zacetek) {
        this.zacetek = zacetek;
    }

    public OffsetDateTime getKonec() {
        return konec;
    }

    public void setKonec(OffsetDateTime konec) {
        this.konec = konec;
    }

    public Boolean getJeZaseden() {
        return jeZaseden;
    }

    public void setJeZaseden(Boolean jeZaseden) {
        this.jeZaseden = jeZaseden;
    }

    public List<Obvestilo> getObvestila() {
        return obvestila;
    }

    public void setObvestila(List<Obvestilo> obvestila) {
        this.obvestila = obvestila;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Fizioterapevt getFizioterapevt() {
        return fizioterapevt;
    }

    public void setFizioterapevt(Fizioterapevt fizioterapevt) {
        this.fizioterapevt = fizioterapevt;
    }
}
