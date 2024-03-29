package com.tpo.fizio.entity.fizioplan.impl.service;

import com.tpo.fizio.entity.fizioplan.model.FizioplanActionInformation;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface FizioplanService {
    FizioplanDto getPacientFizioplan(String pacientUsername);

    List<FizioplanDto> getAllPacientFizioplani(String username);

    List<FizioplanDto> getFizioplans();

    FizioplanDto getFizioplan(Integer fizioplanID);

    FizioplanActionInformation updateFizioplan(FizioplanDto dto);

}
