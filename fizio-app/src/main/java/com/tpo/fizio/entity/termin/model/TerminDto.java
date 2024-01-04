package com.tpo.fizio.entity.termin.model;

import java.time.OffsetDateTime;

/**
 * @author Tadej Delopst
 */
public class TerminDto {
    private Integer terminId;
    private OffsetDateTime zacetek;
    private OffsetDateTime konec;
    private Boolean jeZaseden;
    private String fizioterapevtUsername;
    private String fizioterapevtFullName;

    public TerminDto() {
    }

    public TerminDto(Integer terminId, OffsetDateTime zacetek, OffsetDateTime konec, Boolean jeZaseden, String fizioterapevtUsername, String fizioterapevtFullName) {
        this.terminId = terminId;
        this.zacetek = zacetek;
        this.konec = konec;
        this.jeZaseden = jeZaseden;
        this.fizioterapevtUsername = fizioterapevtUsername;
        this.fizioterapevtFullName = fizioterapevtFullName;
    }

    public Integer getTerminId() {
        return terminId;
    }

    public void setTerminId(Integer terminId) {
        this.terminId = terminId;
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

    public String getFizioterapevtUsername() {
        return fizioterapevtUsername;
    }

    public void setFizioterapevtUsername(String fizioterapevtUsername) {
        this.fizioterapevtUsername = fizioterapevtUsername;
    }

    public String getFizioterapevtFullName() {
        return fizioterapevtFullName;
    }

    public void setFizioterapevtFullName(String fizioterapevtFullName) {
        this.fizioterapevtFullName = fizioterapevtFullName;
    }
}
