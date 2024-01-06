package com.tpo.fizio.entity.fizioterapevt.impl.service;

import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface FizioterapevtService {
    List<FizioterapevtDto> getFizioterapevts();

    FizioterapevtDto getFizioterapevt(String fizioterapevtusername);
}
