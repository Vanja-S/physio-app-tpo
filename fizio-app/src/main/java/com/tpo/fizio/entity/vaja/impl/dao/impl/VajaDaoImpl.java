package com.tpo.fizio.entity.vaja.impl.dao.impl;

import com.tpo.fizio.entity.vaja.impl.dao.VajaDao;
import com.tpo.fizio.entity.vaja.model.Vaja;
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
}
