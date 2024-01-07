package com.tpo.fizio.entity.fizioterapevt.impl.dao.impl;

import com.tpo.fizio.entity.fizioterapevt.impl.dao.FizioterapevtDao;
import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtActionInformation;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
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
public class FizioterapevtDaoImpl implements FizioterapevtDao {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Fizioterapevt> getFizioterapevts() {
        TypedQuery<Fizioterapevt> theQuery = entityManager.createQuery("from Fizioterapevt ", Fizioterapevt.class);

        List<Fizioterapevt> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public Fizioterapevt getFizioterapevt(String fizioterapevtUsername) {
        return entityManager.find(Fizioterapevt.class, fizioterapevtUsername);
    }

    @Override
    public FizioterapevtActionInformation updateFizioterapevt(FizioterapevtDto dto) {
        //iz dtoja vzames ID
        String id = dto.getUsername();
        //poisces objekt
        Fizioterapevt fizioterapevt = entityManager.find(Fizioterapevt.class, id);
        //ce obstaja posodobis
        if (fizioterapevt != null) {
            fizioterapevt.setIme(dto.getIme());
            fizioterapevt.setPriimek(dto.getPriimek());
            fizioterapevt.setKraj(dto.getKraj());
            fizioterapevt.setHisnaStevilka(dto.getHisnaStevilka());
            fizioterapevt.setUlica(dto.getUlica());
            //fizioterapevt.getPostnaStevilka(dto.getPostnaStevilka());
            return new FizioterapevtActionInformation(1, true, false);
        }
        //v primeru da ne obstaja vrnes isto sam da neuspesno
        return new FizioterapevtActionInformation(0, false, false);
    }
}
