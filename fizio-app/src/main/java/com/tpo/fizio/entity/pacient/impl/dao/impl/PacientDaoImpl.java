package com.tpo.fizio.entity.pacient.impl.dao.impl;

import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import com.tpo.fizio.entity.pacient.model.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Repository
public class PacientDaoImpl implements PacientDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Pacient> getAll() {
        TypedQuery<Pacient> theQuery = entityManager.createQuery("from Pacient", Pacient.class);

        List<Pacient> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }
}
