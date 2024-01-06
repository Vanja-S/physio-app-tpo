package com.tpo.fizio.entity.fizioterapevt.model;

/**
 * @author Tadej Delopst
 */
public class FizioterapevtDto {
    private String username;
    private String ime;
    private String priimek;
    private String ulica;
    private String hisnaStevilka;
    private String kraj;

    public FizioterapevtDto() {
    }

    public FizioterapevtDto(String username, String ime, String priimek, String ulica, String hisnaStevilka, String kraj) {
        this.username = username;
        this.ime = ime;
        this.priimek = priimek;
        this.ulica = ulica;
        this.hisnaStevilka = hisnaStevilka;
        this.kraj = kraj;
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

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getHisnaStevilka() {
        return hisnaStevilka;
    }

    public void setHisnaStevilka(String hisnaStevilka) {
        this.hisnaStevilka = hisnaStevilka;
    }

    public String getKraj() {
        return kraj;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }
}
