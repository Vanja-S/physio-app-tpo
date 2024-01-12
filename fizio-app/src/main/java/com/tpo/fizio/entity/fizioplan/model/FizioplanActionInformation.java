package com.tpo.fizio.entity.fizioplan.model;

public class FizioplanActionInformation {
    private String status;
    private int affectedFizioplani;

    public FizioplanActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedFizioplani = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "fizioplani." : "fizioplan.");
        } else {
            this.status = "Failed to update" + (multiple ? "fizioplani." : "fizioplan.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedFizioplani() {
        return affectedFizioplani;
    }

    public void setAffectedFizioplani(int affectedFizioplani) {
        this.affectedFizioplani = affectedFizioplani;
    }
}
