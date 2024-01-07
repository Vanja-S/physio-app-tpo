package com.tpo.fizio.entity.termin.impl.dao.impl;

import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtActionInformation;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.termin.impl.dao.TerminDao;
import com.tpo.fizio.entity.termin.model.Termin;
import com.tpo.fizio.entity.termin.model.TerminActionInformation;
import com.tpo.fizio.entity.termin.model.TerminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Repository
public class TerminDaoImpl implements TerminDao {
    @Autowired
    private EntityManager entityManager;
    private PacientDao pacientDao;

    @Autowired
    public TerminDaoImpl(PacientDao pacientDao) {
        this.pacientDao = pacientDao;
    }

    @Override
    @Transactional
    public List<Termin> getNextPatientTermin(String pacientUsername) {
        TypedQuery<Termin> theQuery = entityManager.createQuery(
                "SELECT t FROM Termin t JOIN t.pacient p WHERE p.username = :username",
                Termin.class);

        theQuery.setParameter("username", pacientUsername);

        List<Termin> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public List<Termin> getAvailableTerminiForDate(LocalDate datum, String pacientUsername) {
        Pacient pacient = pacientDao.getByusername(pacientUsername);
        if (pacient == null) {
            return null;
        }

        TypedQuery<Termin> theQuery = entityManager.createQuery(
                "SELECT t FROM Termin t JOIN t.fizioterapevt f WHERE f.username = :username AND t.jeZaseden = false",
                Termin.class);

        theQuery.setParameter("username", pacient.getFizioterapevt().getUsername());

        List<Termin> resultList;

        try {
            resultList = theQuery.getResultList();
            resultList = resultList.stream().filter(term -> {
                LocalDate start = term.getZacetek().toLocalDate();
                return !(start.isAfter(datum) || start.isBefore(datum));
            }).toList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public TerminActionInformation bookTermin(Integer terminId, String pacientUsername) {
        Pacient pacient = pacientDao.getByusername(pacientUsername);
        Termin termin = entityManager.find(Termin.class, terminId);

        if (termin == null || pacient == null) {
            return new TerminActionInformation(0, false, false);
        }

        termin.setJeZaseden(true);
        termin.setPacient(pacient);

        return new TerminActionInformation(1, true, false);
    }

    @Override
    public TerminActionInformation cancelTermin(Integer terminId) {
        Termin termin = entityManager.find(Termin.class, terminId);

        if (termin == null) {
            return new TerminActionInformation(0, false, false);
        }

        termin.setJeZaseden(false);
        termin.setPacient(null);

        return new TerminActionInformation(1, true, false);
    }

    @Override
    public List<Termin> getAllPacientTermini(String username) {
        TypedQuery<Termin> theQuery = entityManager.createQuery(
                "SELECT t FROM Termin t JOIN t.pacient p WHERE p.username = :username",
                Termin.class);

        theQuery.setParameter("username", username);

        List<Termin> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public List<Termin> getTermini() {
        TypedQuery<Termin> theQuery = entityManager.createQuery(
                "SELECT t FROM Termin t",
                Termin.class);

        List<Termin> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public Termin getTermin(Integer terminId) {
        return entityManager.find(Termin.class, terminId);
    }

    @Override
    public TerminActionInformation updateTermin(TerminDto dto) {
        //iz dtoja vzames ID
        Integer id = dto.getTerminId();
        //poisces objekt
        Termin termin = entityManager.find(Termin.class, id);
        //ce obstaja posodobis
        if (termin != null) {
            termin.setJeZaseden(dto.getJeZaseden());
            termin.setKonec(dto.getKonec());
            termin.setZacetek(dto.getZacetek());
            return new TerminActionInformation(1, true, false);
        }
        //v primeru da ne obstaja vrnes isto sam da neuspesno
        return new TerminActionInformation(0, false, false);
    }
}
