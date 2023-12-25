package com.tpo.fizio.entity.fizioplan.model;

import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.vnos.model.Vnos;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "FIZIOPLAN")
public class FizioPlan {

    @Id
    @Column(name = "ID_FIZIOPLANA", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NASLOV_FIZIOPLANA", length = 150)
    private String naslov;

    @Column(name = "POSKODBA", length = 100)
    private String poskodba;

    @Column(name = "TRAJANJE_OD")
    private LocalDate trajanjeOd;

    @Column(name = "TRAJANJE_DO")
    private LocalDate trajanjeDo;

    @Column(name = "OPIS_FIZIOPLANA", length = 5000)
    private String opis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME_PACIENTA", nullable = false)
    private Pacient pacient;

    @OneToMany(mappedBy = "fizioPlan", cascade = CascadeType.ALL)
    private List<Vnos> vnosi;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getPoskodba() {
        return poskodba;
    }

    public void setPoskodba(String poskodba) {
        this.poskodba = poskodba;
    }

    public LocalDate getTrajanjeOd() {
        return trajanjeOd;
    }

    public void setTrajanjeOd(LocalDate trajanjeOd) {
        this.trajanjeOd = trajanjeOd;
    }

    public LocalDate getTrajanjeDo() {
        return trajanjeDo;
    }

    public void setTrajanjeDo(LocalDate trajanjeDo) {
        this.trajanjeDo = trajanjeDo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public List<Vnos> getVnosi() {
        return vnosi;
    }

    public void setVnosi(List<Vnos> vnosi) {
        this.vnosi = vnosi;
    }
}
