package com.tpo.fizio.entity.obvestilo.impl.service;

import com.tpo.fizio.entity.obvestilo.model.ObvestiloActionInformation;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface ObvestiloService {
    List<ObvestiloDto> getAllPacientObvestila(String username);

    List<ObvestiloDto> getObvestila();

    ObvestiloDto getObvestilo(Integer obvestiloId);

    ObvestiloActionInformation updateObvestilo(ObvestiloDto dto);
}
