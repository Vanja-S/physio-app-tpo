package com.tpo.fizio.entity.pacient.impl.service.impl;

import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import com.tpo.fizio.entity.pacient.impl.service.PacientService;
import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.pacient.model.PacientDto;
import com.tpo.fizio.entity.termin.model.Termin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class PacientServiceImpl implements PacientService {

    private PacientDao pacientDao;

    @Autowired
    public PacientServiceImpl(PacientDao pacientDao) {
        this.pacientDao = pacientDao;
    }

    @Override
    public List<PacientDto> getAllPacients() {
        List<Pacient> pacienti = pacientDao.getAll();
        return pacienti.stream().map(p -> new PacientDto(
                p.getUsername(),
                p.getIme(),
                p.getPriimek(),
                p.getDatumRojstva(),
                p.getFizioterapevt().getUsername(),
                p.getFizioplani().stream().map(FizioPlan::getId).toList(),
                p.getTermini().stream().map(Termin::getId).toList()
        )).toList();
    }
}
