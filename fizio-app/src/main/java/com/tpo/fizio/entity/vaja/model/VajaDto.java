package com.tpo.fizio.entity.vaja.model;

/**
 * @author Tadej Delopst
 */
public class VajaDto {
    private long id;
    private String ime;
    private String opis;
    private String url;

    public VajaDto(long id, String ime, String opis, String url) {
        this.id = id;
        this.ime = ime;
        this.opis = opis;
        this.url = url;
    }

    public VajaDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
