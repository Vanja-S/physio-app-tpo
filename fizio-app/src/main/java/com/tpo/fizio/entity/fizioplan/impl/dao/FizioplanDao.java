package com.tpo.fizio.entity.fizioplan.impl.dao;

import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.fizioplan.model.FizioplanActionInformation;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface FizioplanDao {
    FizioPlan findById(Integer id);
    FizioPlan getFizioplanByPacientUsername(String pacientUsername);

    List<FizioPlan> getAllForPacient(String username);

    List<FizioPlan> getFizioplans();

    FizioPlan getFizioplan(Integer fizioplanID);

    FizioplanActionInformation updateFizioplan(FizioplanDto dto);
}
