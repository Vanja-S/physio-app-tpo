package com.tpo.fizio.entity.vnos.impl.dao;

import com.tpo.fizio.entity.vnos.model.Vnos;
import com.tpo.fizio.entity.vnos.model.VnosActionInformation;

import java.util.List;

/**
 * @author Tadej Delopst
 */
public interface VnosDao {
    List<Vnos> getVnosiByFizioplanId(Integer fizioplanId);

    Vnos findById(Integer vnosId);

    VnosActionInformation updateKomentarVnosa(Integer vnosId, String komentar);
}
