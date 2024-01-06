package com.tpo.fizio.entity.obvestilo.impl.dao;

import com.tpo.fizio.entity.obvestilo.model.Obvestilo;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface ObvestiloDao {
    List<Obvestilo> getAllObvestilaForPacient(String username);

    List<Obvestilo> getObvestila();

    Obvestilo getObvestilo(Integer obvestiloId);
}
