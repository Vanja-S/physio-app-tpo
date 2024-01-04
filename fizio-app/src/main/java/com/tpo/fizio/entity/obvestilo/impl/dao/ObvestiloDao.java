package com.tpo.fizio.entity.obvestilo.impl.dao;

import com.tpo.fizio.entity.obvestilo.model.Obvestilo;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface ObvestiloDao {
    List<Obvestilo> getAllObvestilaForPacient(String username);
}
