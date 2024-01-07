package com.tpo.fizio.entity.vaja.impl.service.impl;

import com.tpo.fizio.entity.obvestilo.model.ObvestiloActionInformation;
import com.tpo.fizio.entity.obvestilo.model.ObvestiloDto;
import com.tpo.fizio.entity.vaja.impl.dao.VajaDao;
import com.tpo.fizio.entity.vaja.impl.service.VajaService;
import com.tpo.fizio.entity.vaja.model.Vaja;
import com.tpo.fizio.entity.vaja.model.VajaActionInformation;
import com.tpo.fizio.entity.vaja.model.VajaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class VajaServiceImpl implements VajaService {
    private VajaDao vajaDao;

    @Autowired
    public VajaServiceImpl(VajaDao vajaDao) {
        this.vajaDao = vajaDao;
    }

    @Override
    public VajaDto getVaja(Long vajaId) {
        Vaja vaja = vajaDao.getVaja(vajaId);
        if (vaja!= null) {
            return new VajaDto(
                    vaja.getId(),
                    vaja.getIme(),
                    vaja.getOpis(),
                    vaja.getUrl()
            );
        }
        return null;
    }

    @Override
    public List<VajaDto> getVaje() {
        List<Vaja> vaje = vajaDao.getVaje();
        if (vaje!= null) {
            return vaje.stream().map(vaja -> new VajaDto(
                    vaja.getId(),
                    vaja.getIme(),
                    vaja.getOpis(),
                    vaja.getUrl()
            )).toList();
        }
        return null;
    }

    @Override
    @Transactional
    public VajaActionInformation updateVaja(VajaDto dto) {
        VajaActionInformation information = vajaDao.updateVaja(dto);
        return information;
    }
}
