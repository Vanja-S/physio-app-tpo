package com.tpo.fizio.entity.termin.impl.service;

import com.tpo.fizio.entity.termin.model.TerminActionInformation;
import com.tpo.fizio.entity.termin.model.TerminDto;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface TerminService {
    TerminDto getNextTerminForPacient(String username);

    List<TerminDto> getAvailableTerminiOnGivenDate(LocalDate datum, String pacientUsername);

    TerminActionInformation bookTermin(Integer terminId, String pacientUsername);

    TerminActionInformation cancelTermin(Integer terminId);

    List<TerminDto> getAllPacientTermini(String username);
}
