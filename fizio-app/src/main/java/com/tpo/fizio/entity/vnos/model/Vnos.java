package com.tpo.fizio.entity.vnos.model;

import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.vaja.model.Vaja;

import javax.persistence.*;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "VNOS")
public class Vnos {

    @Id
    @Column(name = "ID_VNOSA", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ST_SETOV")
    private Integer stSetov;

    @Column(name = "ST_PONOVITEV")
    private Integer stPonovitev;

    @Column(name = "KOMENTAR", length = 5000)
    private String komentar;

    @ManyToOne
    @JoinColumn(name = "ID_VAJE", nullable = false)
    private Vaja vaja;

    @ManyToOne
    @JoinColumn(name = "ID_FIZIOPLANA", nullable = false)
    private FizioPlan fizioPlan;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStSetov() {
        return stSetov;
    }

    public void setStSetov(Integer stSetov) {
        this.stSetov = stSetov;
    }

    public Integer getStPonovitev() {
        return stPonovitev;
    }

    public void setStPonovitev(Integer stPonovitev) {
        this.stPonovitev = stPonovitev;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Vaja getVaja() {
        return vaja;
    }

    public void setVaja(Vaja vaja) {
        this.vaja = vaja;
    }

    public FizioPlan getFizioPlan() {
        return fizioPlan;
    }

    public void setFizioPlan(FizioPlan fizioPlan) {
        this.fizioPlan = fizioPlan;
    }
}