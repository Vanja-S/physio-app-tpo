package com.tpo.fizio.entity.obvestilo.impl.service.impl;

import com.tpo.fizio.entity.obvestilo.impl.dao.ObvestiloDao;
import com.tpo.fizio.entity.obvestilo.impl.service.ObvestiloService;
import com.tpo.fizio.entity.obvestilo.model.Obvestilo;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloActionInformation;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public List<ObvestiloDto> getObvestila() {
        List<Obvestilo> obvestila = obvestiloDao.getObvestila();
        if (obvestila != null) {
            return obvestila.stream().map(obv -> new ObvestiloDto(
                    obv.getId(),
                    obv.getNaslov(),
                    obv.getTs(),
                    obv.getVsebina()
            )).toList();
        }
        return null;
    }

    @Override
    public ObvestiloDto getObvestilo(Integer obvestiloId) {
        Obvestilo obvestilo = obvestiloDao.getObvestilo(obvestiloId);
        if (obvestiloId != null) {
            return new ObvestiloDto(
                    obvestilo.getId(),
                    obvestilo.getNaslov(),
                    obvestilo.getTs(),
                    obvestilo.getVsebina()
            );
        }
        return null;
    }

    @Override
    @Transactional
    public ObvestiloActionInformation updateObvestilo(ObvestiloDto dto) {
        ObvestiloActionInformation information = obvestiloDao.updateObvestilo(dto);
        return information;
    }
}
