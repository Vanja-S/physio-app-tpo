package com.tpo.fizio.entity.pacient.impl.dao;

import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.pacient.model.PacientActionInformation;
import com.tpo.fizio.entity.pacient.model.PacientDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface PacientDao {
    Pacient getByusername(String username);

    List<Pacient> getPacients();

    Fizioterapevt getPacientsFizioterapevt(String pacientUsername);

    PacientActionInformation updatePacient(PacientDto dto);
}
