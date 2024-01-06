package com.tpo.fizio.entity.fizioterapevt.impl.service.impl;

import com.tpo.fizio.entity.fizioterapevt.impl.dao.FizioterapevtDao;
import com.tpo.fizio.entity.fizioterapevt.impl.service.FizioterapevtService;
import com.tpo.fizio.entity.fizioterapevt.model.Fizioterapevt;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class FizioterapevtServiceImpl implements FizioterapevtService {
    private FizioterapevtDao fizioterapevtDao;

    @Autowired
    public FizioterapevtServiceImpl(FizioterapevtDao fizioterapevtDao) {
        this.fizioterapevtDao = fizioterapevtDao;
    }

    @Override
    public List<FizioterapevtDto> getFizioterapevts() {
        List<Fizioterapevt> fizioterapevts = fizioterapevtDao.getFizioterapevts();
        if (fizioterapevts != null) {
            return fizioterapevts.stream().map(fizio -> new FizioterapevtDto(
                    fizio.getUsername(),
                    fizio.getIme(),
                    fizio.getPriimek(),
                    fizio.getUlica(),
                    fizio.getHisnaStevilka(),
                    fizio.getKraj()
            )).toList();
        }
        return null;
    }

    @Override
    public FizioterapevtDto getFizioterapevt(String fizioterapevtUsername) {
        Fizioterapevt fizioterapevt = fizioterapevtDao.getFizioterapevt(fizioterapevtUsername);
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
