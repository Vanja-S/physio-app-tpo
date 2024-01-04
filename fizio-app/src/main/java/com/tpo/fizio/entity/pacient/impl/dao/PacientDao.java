package com.tpo.fizio.entity.pacient.impl.dao;

import com.tpo.fizio.entity.pacient.model.Pacient;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface PacientDao {
    List<Pacient> getAll();

    Pacient getByusername(String username);
}
