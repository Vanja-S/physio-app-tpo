package com.tpo.fizio.entity.fizioplan.impl.service;

import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.model.FizioplanActionInformation;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface FizioplanService {
    FizioplanDto getPacientFizioplan(String pacientUsername);

    List<FizioplanDto> getAllPacientFizioplani(String username);

    FizioplanActionInformation updateFizioplan(FizioplanDto dto);

}
