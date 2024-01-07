package com.tpo.fizio.entity.obvestilo.model;

public class ObvestiloActionInformation {
    private String status;
    private int affectedObvestila;

    public ObvestiloActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedObvestila = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "obvestila." : "obvestilo.");
        } else {
            this.status = "Failed to update" + (multiple ? "obvestila." : "obvestilo.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedObvestila() {
        return affectedObvestila;
    }

    public void setAffectedObvestila(int affectedObvestila) {
        this.affectedObvestila = affectedObvestila;
    }
}
