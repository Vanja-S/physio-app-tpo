package com.tpo.fizio.entity.vnos.impl.dao.impl;

import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.vnos.impl.dao.VnosDao;
import com.tpo.fizio.entity.vnos.model.Vnos;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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

    @Override
    public List<Vnos> getVnosi() {
        TypedQuery<Vnos> theQuery = entityManager.createQuery(
                "select v FROM Vnos v", Vnos.class);
        List<Vnos> resultList;

        try {
            resultList = theQuery.getResultList();
        } catch (Exception e) {
            resultList = null;
        }
        return resultList;
    }

    //isto ko prej code -> imeplement method

    @Override
    public VnosActionInformation updateVnos(VnosDto dto) {
        //iz dtoja vzames ID
        Integer id = dto.getId();
        //poisces objekt
        Vnos vnos = entityManager.find(Vnos.class, id);
        //ce obstaja posodobis
        if (vnos != null) {
            vnos.setKomentar(dto.getKomentar());
            // 12 x 3
            vnos.setStPonovitev(Integer.valueOf(dto.getPonovitve().split("x")[0].strip()));
            vnos.setStSetov(Integer.valueOf(dto.getPonovitve().split("x")[1].strip()));
            return new VnosActionInformation(1, true, false);
        }
        //v primeru da ne obstaja vrnes isto sam da neuspesno
        return new VnosActionInformation(0, false, false);
    }
}
