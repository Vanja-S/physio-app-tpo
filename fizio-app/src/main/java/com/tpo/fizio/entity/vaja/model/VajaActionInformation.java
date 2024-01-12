package com.tpo.fizio.entity.vaja.model;

public class VajaActionInformation {
    private String status;
    private int affectedVaje;

    public VajaActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedVaje = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "vaje." : "vaja.");
        } else {
            this.status = "Failed to update" + (multiple ? "vaje." : "vaja.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedVaje() {
        return affectedVaje;
    }

    public void setAffectedVaje(int affectedVaje) {
        this.affectedVaje = affectedVaje;
    }
}
