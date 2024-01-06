package com.tpo.fizio.entity.vaja.impl.service;

import com.tpo.fizio.entity.vaja.model.VajaDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface VajaService {
    VajaDto getVaja(Long vajaId);

    List<VajaDto> getVaje();
}
