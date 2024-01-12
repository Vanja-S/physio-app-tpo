package com.tpo.fizio.entity.vnos.impl.dao;

import com.tpo.fizio.entity.vnos.model.Vnos;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;
import com.tpo.fizio.entity.vnos.model.VnosDto;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface VnosDao {
    List<Vnos> getVnosiByFizioplanId(Integer fizioplanId);

    Vnos findById(Integer vnosId);

    VnosActionInformation updateKomentarVnosa(Integer vnosId, String komentar);

    List<Vnos> getVnosi();

    VnosActionInformation updateVnos(VnosDto dto);
}
