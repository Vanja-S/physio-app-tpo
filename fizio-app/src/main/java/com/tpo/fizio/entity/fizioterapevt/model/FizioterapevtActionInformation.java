package com.tpo.fizio.entity.fizioterapevt.model;

public class FizioterapevtActionInformation {
    private String status;
    private int affectedFizioterapevti;

    public FizioterapevtActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedFizioterapevti = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "fizioterapevti." : "fizioterapevt.");
        } else {
            this.status = "Failed to update" + (multiple ? "fizioterapevti." : "fizioterapevt.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedFizioterapevti() {
        return affectedFizioterapevti;
    }

    public void setAffectedFizioterapevti(int affectedFizioterapevti) {
        this.affectedFizioterapevti = affectedFizioterapevti;
    }
}
