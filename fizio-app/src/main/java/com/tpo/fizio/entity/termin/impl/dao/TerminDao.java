package com.tpo.fizio.entity.termin.impl.dao;

import com.tpo.fizio.entity.termin.model.Termin;
import com.tpo.fizio.entity.termin.model.TerminActionInformation;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface TerminDao {
    List<Termin> getNextPatientTermin(String pacientUsername);

    List<Termin> getAvailableTerminiForDate(LocalDate datum, String pacientUsername);

    TerminActionInformation bookTermin(Integer terminId, String pacientUsername);

    TerminActionInformation cancelTermin(Integer terminId);

    List<Termin> getAllPacientTermini(String username);
}
