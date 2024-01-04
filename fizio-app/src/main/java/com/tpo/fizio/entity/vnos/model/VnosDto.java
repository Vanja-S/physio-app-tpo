package com.tpo.fizio.entity.vnos.model;

/**
 * @author Tadej Delopst
 */
public class VnosDto {
    private Integer id;
    private String ponovitve;
    private String komentar;
    private String imeVaje;
    private String opisVaje;
    private String url;

    public VnosDto() {
    }

    public VnosDto(Integer id, String ponovitve, String komentar, String imeVaje, String opisVaje, String url) {
        this.id = id;
        this.ponovitve = ponovitve;
        this.komentar = komentar;
        this.imeVaje = imeVaje;
        this.opisVaje = opisVaje;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPonovitve() {
        return ponovitve;
    }

    public void setPonovitve(String ponovitve) {
        this.ponovitve = ponovitve;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public String getImeVaje() {
        return imeVaje;
    }

    public void setImeVaje(String imeVaje) {
        this.imeVaje = imeVaje;
    }

    public String getOpisVaje() {
        return opisVaje;
    }

    public void setOpisVaje(String opisVaje) {
        this.opisVaje = opisVaje;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
