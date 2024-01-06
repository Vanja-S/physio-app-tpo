package com.tpo.fizio.entity.fizioterapevt.impl.dao;

import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface FizioterapevtDao {
    List<Fizioterapevt> getFizioterapevts();

    Fizioterapevt getFizioterapevt(String fizioterapevtUsername);
}
