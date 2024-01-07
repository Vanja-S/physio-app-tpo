package com.tpo.fizio.entity.vaja.impl.dao.impl;

import com.tpo.fizio.entity.vaja.impl.dao.VajaDao;
import com.tpo.fizio.entity.vaja.model.Vaja;
import com.tpo.fizio.entity.vaja.model.VajaActionInformation;
import com.tpo.fizio.entity.vaja.model.VajaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Repository
public class VajaDaoImpl implements VajaDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Vaja> getVaje() {
        TypedQuery<Vaja> theQuery = entityManager.createQuery("from Vaja", Vaja.class);

        List<Vaja> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public Vaja getVaja(Long vajaId) {
        return entityManager.find(Vaja.class, vajaId);
    }

    @Override
    public VajaActionInformation updateVaja(VajaDto dto) {
        long id = dto.getId();
        Vaja vaja = entityManager.find(Vaja.class, id);
        if (vaja != null) {
            vaja.setIme(dto.getIme());
            vaja.setOpis(dto.getOpis());
            vaja.setUrl(dto.getUrl());
            return new VajaActionInformation(1, true, false);
        }
        return new VajaActionInformation(0, false, false);
    }
}
