package com.tpo.fizio.entity.pacient.impl.service.impl;

import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
import com.tpo.fizio.entity.pacient.impl.dao.PacientDao;
import com.tpo.fizio.entity.pacient.impl.service.PacientService;
import com.tpo.fizio.entity.pacient.model.Pacient;
import com.tpo.fizio.entity.pacient.model.PacientDto;
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
    public List<PacientDto> getPacients() {
        List<Pacient> pacients = pacientDao.getPacients();
        if (pacients != null) {
            return pacients.stream().map(
                    pacient -> new PacientDto(
                            pacient.getUsername(),
                            pacient.getIme(),
                            pacient.getPriimek(),
                            pacient.getDatumRojstva(),
                            pacient.getFizioterapevt().getUsername()
                    )
            ).toList();
        }
        return null;
    }

    @Override
    public PacientDto getPacient(String pacientUsername) {
        Pacient pacient = pacientDao.getByusername(pacientUsername);
        if (pacient != null) {
            return new PacientDto(
                    pacient.getUsername(),
                    pacient.getIme(),
                    pacient.getPriimek(),
                    pacient.getDatumRojstva(),
                    pacient.getFizioterapevt().getUsername()
            );
        }
        return null;
    }

    @Override
    public FizioterapevtDto getPacientsFizioterapevt(String pacientUsername) {
        Fizioterapevt fizioterapevt = pacientDao.getPacientsFizioterapevt(pacientUsername);
        if (fizioterapevt != null) {
            return new FizioterapevtDto(
                    fizioterapevt.getUsername(),
                    fizioterapevt.getIme(),
                    fizioterapevt.getPriimek(),
                    fizioterapevt.getUlica(),
                    fizioterapevt.getHisnaStevilka(),
                    fizioterapevt.getKraj()
            );
        }
        return null;
    }
}
