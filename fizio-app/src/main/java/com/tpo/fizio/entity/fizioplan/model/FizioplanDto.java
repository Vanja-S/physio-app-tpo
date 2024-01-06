package com.tpo.fizio.entity.fizioplan.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * @author Tadej Delopst
 */
public class FizioplanDto {
    private Integer id;
    private String naslov;
    private String poskodba;
    private OffsetDateTime trajanjeOd;
    private OffsetDateTime trajanjeDo;
    private String opis;

    public FizioplanDto() {
    }

    public FizioplanDto(Integer id, String naslov, String poskodba, OffsetDateTime trajanjeOd, OffsetDateTime trajanjeDo, String opis) {
        this.id = id;
        this.naslov = naslov;
        this.poskodba = poskodba;
        this.trajanjeOd = trajanjeOd;
        this.trajanjeDo = trajanjeDo;
        this.opis = opis;
    }

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

    public OffsetDateTime getTrajanjeOd() {
        return trajanjeOd;
    }

    public void setTrajanjeOd(OffsetDateTime trajanjeOd) {
        this.trajanjeOd = trajanjeOd;
    }

    public OffsetDateTime getTrajanjeDo() {
        return trajanjeDo;
    }

    public void setTrajanjeDo(OffsetDateTime trajanjeDo) {
        this.trajanjeDo = trajanjeDo;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
