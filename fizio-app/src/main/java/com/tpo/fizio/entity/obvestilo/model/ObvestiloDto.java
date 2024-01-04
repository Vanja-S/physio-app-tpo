package com.tpo.fizio.entity.obvestilo.model;

import java.time.OffsetDateTime;

/**
 * @author Tadej Delopst
 */
public class ObvestiloDto {
    private Integer obvestiloID;
    private String naslov;
    private OffsetDateTime ts;
    private String vsebina;

    public ObvestiloDto() {
    }

    public ObvestiloDto(Integer obvestiloID, String naslov, OffsetDateTime ts, String vsebina) {
        this.obvestiloID = obvestiloID;
        this.naslov = naslov;
        this.ts = ts;
        this.vsebina = vsebina;
    }

    public Integer getObvestiloID() {
        return obvestiloID;
    }

    public void setObvestiloID(Integer obvestiloID) {
        this.obvestiloID = obvestiloID;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
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
}
