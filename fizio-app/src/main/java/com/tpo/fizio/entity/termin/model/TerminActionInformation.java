package com.tpo.fizio.entity.termin.model;

/**
 * @author Tadej Delopst
 */
public class TerminActionInformation {
    private String status;
    private int affectedTermini;

    public TerminActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedTermini = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "termini." : "termin.");
        } else {
            this.status = "Failed to update" + (multiple ? "termini." : "termin.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedTermini() {
        return affectedTermini;
    }

    public void setAffectedTermini(int affectedTermini) {
        this.affectedTermini = affectedTermini;
    }
}
