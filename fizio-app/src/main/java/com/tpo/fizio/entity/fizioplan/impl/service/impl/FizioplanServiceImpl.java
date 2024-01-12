package com.tpo.fizio.entity.fizioplan.impl.service.impl;

import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.impl.service.FizioplanService;
import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.fizioplan.model.FizioplanActionInformation;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    public List<FizioplanDto> getFizioplans() {
        List<FizioPlan> fizioplani = fizioplanDao.getFizioplans();
        if (fizioplani != null) {
            return fizioplani.stream().map(plan -> new FizioplanDto(
                    plan.getId(),
                    plan.getNaslov(),
                    plan.getPoskodba(),
                    plan.getTrajanjeOd(),
                    plan.getTrajanjeDo(),
                    plan.getOpis()
            )).toList();
        }
        return null;
    }

    @Override
    public FizioplanDto getFizioplan(Integer fizioplanID) {
        FizioPlan fizioPlan = fizioplanDao.getFizioplan(fizioplanID);
        if (fizioPlan != null) {
            return new FizioplanDto(
                    fizioPlan.getId(),
                    fizioPlan.getNaslov(),
                    fizioPlan.getPoskodba(),
                    fizioPlan.getTrajanjeOd(),
                    fizioPlan.getTrajanjeDo(),
                    fizioPlan.getOpis()
            );
        }
        return null;
    }

    @Override
    @Transactional
    public FizioplanActionInformation updateFizioplan(FizioplanDto dto) {
        return fizioplanDao.updateFizioplan(dto);
    }

}
