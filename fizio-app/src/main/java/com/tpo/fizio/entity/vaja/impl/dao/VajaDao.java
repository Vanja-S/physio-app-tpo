package com.tpo.fizio.entity.vaja.impl.dao;

import com.tpo.fizio.entity.vaja.model.Vaja;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface VajaDao {
    List<Vaja> getVaje();

    Vaja getVaja(Long vajaId);
}
