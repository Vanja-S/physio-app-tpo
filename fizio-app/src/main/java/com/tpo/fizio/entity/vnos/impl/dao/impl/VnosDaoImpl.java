package com.tpo.fizio.entity.vnos.impl.dao.impl;

import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.vnos.impl.dao.VnosDao;
import com.tpo.fizio.entity.vnos.model.Vnos;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Tadej Delopst
 */
@Repository
public class VnosDaoImpl implements VnosDao {
    @Autowired
    private EntityManager entityManager;

    private FizioplanDao fizioplanDao;

    @Autowired
    public VnosDaoImpl(FizioplanDao fizioplanDao) {
        this.fizioplanDao = fizioplanDao;
    }

    @Override
    public List<Vnos> getVnosiByFizioplanId(Integer fizioplanId) {
        FizioPlan plan = fizioplanDao.findById(fizioplanId);
        if (plan == null) {
            return null;
        }
        return plan.getVnosi();
    }

    @Override
    public Vnos findById(Integer vnosId) {
        return entityManager.find(Vnos.class, vnosId);
    }

    @Override
    public VnosActionInformation updateKomentarVnosa(Integer vnosId, String komentar) {
        Vnos vnos = entityManager.find(Vnos.class, vnosId);
        if (vnos == null) {
            return new VnosActionInformation(0, false, false);
        }
        vnos.setKomentar(komentar);
        return new VnosActionInformation(1, true, false);
    }
}
