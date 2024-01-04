package com.tpo.fizio.entity.vnos.impl.service;

import com.tpo.fizio.entity.dto.VnosMetaDto;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface VnosService {
    List<VnosMetaDto> getFizioplansVnosiMetaData(Integer fizioplanId);

    VnosDto getVnosById(Integer vnosId);


    VnosActionInformation updateKomentarVnosa(Integer vnosId, String komentar);
}
