package com.tpo.fizio.entity.pacient.impl.service;

import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.pacient.model.PacientDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface PacientService {
    List<PacientDto> getAllPacients();
}
