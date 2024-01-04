package com.tpo.fizio.entity.obvestilo.impl.service.impl;

import com.tpo.fizio.entity.obvestilo.impl.dao.ObvestiloDao;
import com.tpo.fizio.entity.obvestilo.impl.service.ObvestiloService;
import com.tpo.fizio.entity.obvestilo.model.Obvestilo;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import com.tpo.fizio.entity.vnos.impl.service.VnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class ObvestiloServiceImpl implements ObvestiloService {
    private ObvestiloDao obvestiloDao;

    @Autowired
    public ObvestiloServiceImpl(ObvestiloDao obvestiloDao) {
        this.obvestiloDao = obvestiloDao;
    }

    @Override
    public List<ObvestiloDto> getAllPacientObvestila(String username) {
        List<Obvestilo> obvestila = obvestiloDao.getAllObvestilaForPacient(username);
        if (obvestila == null) return null;
        return obvestila.stream().map(
                obv -> new ObvestiloDto(
                        obv.getId(),
                        obv.getNaslov(),
                        obv.getTs(),
                        obv.getVsebina())
        ).toList();
    }
}
