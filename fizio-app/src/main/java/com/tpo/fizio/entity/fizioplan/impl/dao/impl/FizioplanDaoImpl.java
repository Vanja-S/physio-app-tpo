package com.tpo.fizio.entity.fizioplan.impl.dao.impl;

import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.fizioplan.model.FizioplanActionInformation;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import com.tpo.fizio.entity.pacient.model.Pacient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Repository
public class FizioplanDaoImpl implements FizioplanDao {

    @Autowired
    private EntityManager entityManager;
    private PacientDao pacientDao;

    @Autowired
    public FizioplanDaoImpl(PacientDao pacientDao) {
        this.pacientDao = pacientDao;
    }

    @Override
    public FizioPlan getFizioplanByPacientUsername(String pacientUsername) {
        Pacient pacient = pacientDao.getByusername(pacientUsername);
        if (pacient == null) {
            return null;
        }
        List<FizioPlan> fizioplans = pacient.getFizioplani();
        FizioPlan latestFizioPlan = null;

        if (fizioplans != null) {
            fizioplans = fizioplans.stream()
                    .filter(p -> p.getTrajanjeOd().isAfter(OffsetDateTime.now()))
                    .toList();
            latestFizioPlan = fizioplans.get(fizioplans.size() - 1);
        }
        return latestFizioPlan;
    }

    @Override
    public FizioPlan findById(Integer fizioplanId) {
        return entityManager.find(FizioPlan.class, fizioplanId);
    }

    @Override
    public List<FizioPlan> getAllForPacient(String username) {
        Pacient pacient = pacientDao.getByusername(username);
        if (pacient == null) {
            return null;
        }
        return pacient.getFizioplani();
    }

    @Override
    public List<FizioPlan> getFizioplans() {
        TypedQuery<FizioPlan> theQuery = entityManager.createQuery("from FizioPlan ", FizioPlan.class);

        List<FizioPlan> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    @Override
    public FizioPlan getFizioplan(Integer fizioplanID) {
        return entityManager.find(FizioPlan.class, fizioplanID);
    }

    @Override
    public FizioplanActionInformation updateFizioplan(FizioplanDto dto) {
        Integer id = dto.getId();
        FizioPlan fizioplan = entityManager.find(FizioPlan.class, id);
        if (fizioplan != null) {
            fizioplan.setNaslov(dto.getNaslov());
            fizioplan.setOpis(dto.getOpis());
            fizioplan.setPoskodba(dto.getPoskodba());
            fizioplan.setTrajanjeOd(dto.getTrajanjeOd());
            fizioplan.setTrajanjeDo(dto.getTrajanjeDo());
            return new FizioplanActionInformation(1, true, false);
        }
        return new FizioplanActionInformation(0, false, false);
    }
}

