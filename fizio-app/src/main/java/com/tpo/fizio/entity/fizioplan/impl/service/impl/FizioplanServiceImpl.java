package com.tpo.fizio.entity.fizioplan.impl.service.impl;

import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.impl.service.FizioplanService;
import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import com.tpo.fizio.entity.pacient.impl.exception.PacientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class FizioplanServiceImpl implements FizioplanService {
    private FizioplanDao fizioplanDao;

    @Autowired
    public FizioplanServiceImpl(FizioplanDao fizioplanDao) {
        this.fizioplanDao = fizioplanDao;
    }

    @Override
    public FizioplanDto getPacientFizioplan(String pacientUsername){
        FizioPlan fizioplan = fizioplanDao.getFizioplanByPacientUsername(pacientUsername);
        FizioplanDto dto = null;
        if (fizioplan != null) {
            dto = new FizioplanDto(
                    fizioplan.getId(),
                    fizioplan.getNaslov(),
                    fizioplan.getPoskodba(),
                    fizioplan.getTrajanjeOd(),
                    fizioplan.getTrajanjeDo(),
                    fizioplan.getOpis()
            );
        }
        return dto;
    }

    @Override
    public List<FizioplanDto> getAllPacientFizioplani(String username) {
        List<FizioPlan> fizioplani = fizioplanDao.getAllForPacient(username);
        if (fizioplani == null) {
            return null;
        }
        return fizioplani.stream().map(
                fizioPlan -> new FizioplanDto(
                        fizioPlan.getId(),
                        fizioPlan.getNaslov(),
                        fizioPlan.getPoskodba(),
                        fizioPlan.getTrajanjeOd(),
                        fizioPlan.getTrajanjeDo(),
                        fizioPlan.getOpis()
                )
        ).toList();
    }
}
