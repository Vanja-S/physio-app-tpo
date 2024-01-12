package com.tpo.fizio.entity.fizioterapevt.impl.dao;

import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtActionInformation;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface FizioterapevtDao {
    List<Fizioterapevt> getFizioterapevts();

    Fizioterapevt getFizioterapevt(String fizioterapevtUsername);

    FizioterapevtActionInformation updateFizioterapevt(FizioterapevtDto dto);
}
