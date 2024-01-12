package com.tpo.fizio.entity.vaja.impl.dao;

import com.tpo.fizio.entity.vaja.model.Vaja;
import com.tpo.fizio.entity.vaja.model.VajaActionInformation;
import com.tpo.fizio.entity.vaja.model.VajaDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface VajaDao {
    List<Vaja> getVaje();

    Vaja getVaja(Long vajaId);

    VajaActionInformation updateVaja(VajaDto dto);
}
