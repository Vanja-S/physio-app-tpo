package com.tpo.fizio.entity.obvestilo.model;

import com.tpo.fizio.entity.termin.model.Termin;

import javax.persistence.*;
import java.time.OffsetDateTime;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "OBVESTILO")
public class Obvestilo {

    @Id
    @Column(name = "ID_OBVESTILA", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TS")
    private OffsetDateTime ts;

    @Column(name = "VSEBINA", length = 150)
    private String vsebina;

    @ManyToOne
    @JoinColumn(name = "ID_TERMINA", nullable = false)
    private Termin termin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OffsetDateTime getTs() {
        return ts;
    }

    public void setTs(OffsetDateTime ts) {
        this.ts = ts;
    }

    public String getVsebina() {
        return vsebina;
    }

    public void setVsebina(String vsebina) {
        this.vsebina = vsebina;
    }

    public Termin getTermin() {
        return termin;
    }

    public void setTermin(Termin termin) {
        this.termin = termin;
    }
}
