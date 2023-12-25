package com.tpo.fizio.entity.pacient.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public class PacientDto {
    private String username;
    private String ime;
    private String priimek;
    private OffsetDateTime datumRojstva;
    private String fizioterapevtUsername;
    private List<Integer> fizioPlaniIds;
    private List<Integer> terminiIds;

    public PacientDto() {
    }

    public PacientDto(String username, String ime, String priimek, OffsetDateTime datumRojstva, String fizioterapevtUsername, List<Integer> fizioPlaniIds, List<Integer> terminiIds) {
        this.username = username;
        this.ime = ime;
        this.priimek = priimek;
        this.datumRojstva = datumRojstva;
        this.fizioterapevtUsername = fizioterapevtUsername;
        this.fizioPlaniIds = fizioPlaniIds;
        this.terminiIds = terminiIds;
    }

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

    public String getFizioterapevtUsername() {
        return fizioterapevtUsername;
    }

    public void setFizioterapevtUsername(String fizioterapevtUsername) {
        this.fizioterapevtUsername = fizioterapevtUsername;
    }

    public List<Integer> getFizioPlaniIds() {
        return fizioPlaniIds;
    }

    public void setFizioPlaniIds(List<Integer> fizioPlaniIds) {
        this.fizioPlaniIds = fizioPlaniIds;
    }

    public List<Integer> getTerminiIds() {
        return terminiIds;
    }

    public void setTerminiIds(List<Integer> terminiIds) {
        this.terminiIds = terminiIds;
    }
}
