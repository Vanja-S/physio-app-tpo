package com.tpo.fizio.entity.pacient.model;

public class PacientActionInformation {
    private String status;
    private int affectedPacienti;

    public PacientActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedPacienti = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "pacienti." : "pacient.");
        } else {
            this.status = "Failed to update" + (multiple ? "pacienti." : "pacient.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedPacienti() {
        return affectedPacienti;
    }

    public void setAffectedPacienti(int affectedPacienti) {
        this.affectedPacienti = affectedPacienti;
    }
}
