package com.tpo.fizio.entity.dto;

/**
 * @author Tadej Delopst
 */
public class VnosMetaDto {
    private Integer vnosId;
    private String imeVaje;
    private String ponovitve;
    private String opombaVnosa;

    public VnosMetaDto() {
    }

    public VnosMetaDto(Integer vnosId, String imeVaje, String ponovitve, String opombaVnosa) {
        this.vnosId = vnosId;
        this.imeVaje = imeVaje;
        this.ponovitve = ponovitve;
        this.opombaVnosa = opombaVnosa;
    }

    public Integer getVnosId() {
        return vnosId;
    }

    public void setVnosId(Integer vnosId) {
        this.vnosId = vnosId;
    }

    public String getImeVaje() {
        return imeVaje;
    }

    public void setImeVaje(String imeVaje) {
        this.imeVaje = imeVaje;
    }

    public String getPonovitve() {
        return ponovitve;
    }

    public void setPonovitve(String ponovitve) {
        this.ponovitve = ponovitve;
    }

    public String getOpombaVnosa() {
        return opombaVnosa;
    }

    public void setOpombaVnosa(String opombaVnosa) {
        this.opombaVnosa = opombaVnosa;
    }
}
