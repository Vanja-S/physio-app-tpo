package com.tpo.fizio.entity.pacient.model;

import java.time.OffsetDateTime;

public class PacientDto {
    private String username;
    private String ime;
    private String priimek;
    private OffsetDateTime datumRojstva;
    private String fizioterapevtUsername;

    public PacientDto() {
    }

    public PacientDto(String username, String ime, String priimek, OffsetDateTime datumRojstva, String fizioterapevtUsername) {
        this.username = username;
        this.ime = ime;
        this.priimek = priimek;
        this.datumRojstva = datumRojstva;
        this.fizioterapevtUsername = fizioterapevtUsername;
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
}
