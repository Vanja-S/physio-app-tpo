package com.tpo.fizio.entity.vnos.model;

/**
 * @author Tadej Delopst
 */
public class VnosActionInformation {
    private String status;
    private int affectedVnosi;

    public VnosActionInformation(int affected, boolean successful, boolean multiple) {
        this.affectedVnosi = affected;
        if (successful) {
            this.status = "Successfully updated" + (multiple ? "vnosi." : "vnos.");
        } else {
            this.status = "Failed to update" + (multiple ? "vnosi." : "vnos.");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAffectedVnosi() {
        return affectedVnosi;
    }

    public void setAffectedVnosi(int affectedVnosi) {
        this.affectedVnosi = affectedVnosi;
    }
}
