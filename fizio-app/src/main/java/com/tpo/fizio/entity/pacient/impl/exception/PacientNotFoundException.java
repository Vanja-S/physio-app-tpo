package com.tpo.fizio.entity.pacient.impl.exception;

/**
 * @author Tadej Delopst
 * <p>
 * Custom exception class for handling Pacient not found exceptions.
 */
public class PacientNotFoundException extends RuntimeException {
    public PacientNotFoundException(String pacientUsername) {
        super("Pacient with username: " + pacientUsername + " does not exist!");
    }
}
