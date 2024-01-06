package com.tpo.fizio.entity.obvestilo.impl.dao.impl;

import com.tpo.fizio.entity.obvestilo.impl.dao.ObvestiloDao;
import com.tpo.fizio.entity.obvestilo.model.Obvestilo;
import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Repository
public class ObvestiloDaoImpl implements ObvestiloDao {
    @Autowired
    private EntityManager entityManager;

    private PacientDao pacientDao;

    @Autowired
    public ObvestiloDaoImpl(PacientDao pacientDao) {
        this.pacientDao = pacientDao;
    }

    @Override
    public List<Obvestilo> getAllObvestilaForPacient(String username) {
        TypedQuery<Obvestilo> theQuery = entityManager.createQuery(
                "SELECT o FROM Obvestilo o JOIN o.termin t JOIN t.pacient p WHERE p.username = :username",
                Obvestilo.class);

        theQuery.setParameter("username", username);

        List<Obvestilo> resultList = null;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public List<Obvestilo> getObvestila() {
        TypedQuery<Obvestilo> theQuery = entityManager.createQuery("from Obvestilo", Obvestilo.class);

        List<Obvestilo> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public Obvestilo getObvestilo(Integer obvestiloId) {
        return entityManager.find(Obvestilo.class, obvestiloId);
    }
}
