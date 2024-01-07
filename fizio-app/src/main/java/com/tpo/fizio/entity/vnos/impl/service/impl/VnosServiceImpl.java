package com.tpo.fizio.entity.vnos.impl.service.impl;

import com.tpo.fizio.entity.dto.VnosMetaDto;
import com.tpo.fizio.entity.fizioplan.impl.dao.FizioplanDao;
import com.tpo.fizio.entity.fizioplan.model.FizioPlan;
import com.tpo.fizio.entity.fizioplan.model.FizioplanDto;
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

    //TAB: Code -> imeplement method
    //Ker delaš update, ki spreminja bazo pod @Override dodaš @Transactional
    @Override
    @Transactional
    public VnosActionInformation updateVnos(VnosDto dto) {
        //uzames return type te metode -> XActionInformation
        //isto ko prej -> z misko gor in create method
        VnosActionInformation information = vnosDao.updateVnos(dto);
        return information;
    }
}
