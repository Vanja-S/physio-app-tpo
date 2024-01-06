package com.tpo.fizio.entity.pacient.impl.dao.impl;

import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
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
    public Pacient getByusername(String username) {
        return entityManager.find(Pacient.class, username);
    }
    @Override
    public List<Pacient> getPacients() {
        TypedQuery<Pacient> theQuery = entityManager.createQuery("from Pacient", Pacient.class);

        List<Pacient> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public Fizioterapevt getPacientsFizioterapevt(String pacientUsername) {
        TypedQuery<Fizioterapevt> theQuery = entityManager.createQuery(
                "select f from Pacient p JOIN p.fizioterapevt f WHERE p.username = :username",
                Fizioterapevt.class);
        theQuery.setParameter("username", pacientUsername);

        Fizioterapevt result;

        try {
            result = theQuery.getSingleResult();
        } catch (Exception e) {
            result = null;
        }
        return result;
    }
}
