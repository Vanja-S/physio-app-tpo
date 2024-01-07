package com.tpo.fizio.entity.vnos.impl.service.impl;

import com.tpo.fizio.entity.dto.VnosMetaDto;
import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.vnos.impl.dao.VnosDao;
import com.tpo.fizio.entity.vnos.impl.service.VnosService;
import com.tpo.fizio.entity.vnos.model.Vnos;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class VnosServiceImpl implements VnosService {
    private VnosDao vnosDao;
    private FizioplanDao fizioplanDao;

    @Autowired
    public VnosServiceImpl(VnosDao vnosDao, FizioplanDao fizioplanDao) {
        this.vnosDao = vnosDao;
        this.fizioplanDao = fizioplanDao;
    }

    @Override
    public List<VnosMetaDto> getFizioplansVnosiMetaData(Integer fizioplanId) {
        List<Vnos> vnosiFiziplana = vnosDao.getVnosiByFizioplanId(fizioplanId);
        if (vnosiFiziplana == null) {
            return null;
        }
        return vnosiFiziplana.stream().map(
                vnos -> new VnosMetaDto(
                        vnos.getId(),
                        vnos.getVaja().getIme(),
                        vnos.getStSetov() + " x " + vnos.getStPonovitev(),
                        vnos.getKomentar()
                )
        ).toList();
    }

    @Override
    public VnosDto getVnosById(Integer vnosId) {
        Vnos vnos = vnosDao.findById(vnosId);
        if (vnos == null) {
            return null;
        }
        return new VnosDto(
                vnos.getId(),
                vnos.getStSetov() + " x " + vnos.getStPonovitev(),
                vnos.getKomentar(),
                vnos.getVaja().getIme(),
                vnos.getVaja().getOpis(),
                vnos.getVaja().getUrl()
        );
    }

    @Override
    @Transactional
    public VnosActionInformation updateKomentarVnosa(Integer vnosId, String komentar) {
        return vnosDao.updateKomentarVnosa(vnosId, komentar);
    }

    @Override
    public List<VnosDto> getVnosi() {
        List<Vnos> vnosi = vnosDao.getVnosi();
        if (vnosi != null) {
            return vnosi.stream().map(
                    vnos -> new VnosDto(
                            vnos.getId(),
                            vnos.getStSetov() + " x " + vnos.getStPonovitev(),
                            vnos.getKomentar(),
                            vnos.getVaja().getIme(),
                            vnos.getVaja().getOpis(),
                            vnos.getVaja().getUrl()
                    )
            ).toList();
        }
        return null;
    }

    @Override
    @Transactional
    public VnosActionInformation updateVnos(VnosDto dto) {
        return vnosDao.updateVnos(dto);
    }
}
