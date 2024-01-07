package com.tpo.fizio.entity.termin.impl.service.impl;

import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtActionInformation;
import com.tpo.fizio.entity.fizioterapevt.model.FizioterapevtDto;
import com.tpo.fizio.entity.termin.impl.dao.TerminDao;
import com.tpo.fizio.entity.termin.impl.service.TerminService;
import com.tpo.fizio.entity.termin.model.Termin;
import com.tpo.fizio.entity.termin.model.TerminActionInformation;
import com.tpo.fizio.entity.termin.model.TerminDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Tadej Delopst
 */
@Service
public class TerminServiceImpl implements TerminService {
    private TerminDao terminDao;

    @Autowired
    public TerminServiceImpl(TerminDao terminDao) {
        this.terminDao = terminDao;
    }

    @Override
    public TerminDto getNextTerminForPacient(String username) {
        List<Termin> termini = terminDao.getNextPatientTermin(username);
        if (termini == null) {
            return null;
        }
        Termin termin = termini.get(0);
        return new TerminDto(
                termin.getId(),
                termin.getZacetek(),
                termin.getKonec(),
                termin.getJeZaseden(),
                termin.getFizioterapevt().getUsername(),
                termin.getFizioterapevt().getIme() + " " + termin.getFizioterapevt().getPriimek()
        );
    }

    @Override
    public List<TerminDto> getAvailableTerminiOnGivenDate(LocalDate datum, String pacientUsername) {
        List<Termin> termini = terminDao.getAvailableTerminiForDate(datum, pacientUsername);
        if (termini == null) {
            return null;
        }
        return termini.stream().map(t -> new TerminDto(
                t.getId(),
                t.getZacetek(),
                t.getKonec(),
                t.getJeZaseden(),
                t.getFizioterapevt().getUsername(),
                t.getFizioterapevt().getIme() + " " + t.getFizioterapevt().getPriimek()
        )).toList();
    }

    @Override
    @Transactional
    public TerminActionInformation bookTermin(Integer terminId, String pacientUsername) {
        return terminDao.bookTermin(terminId, pacientUsername);
    }

    @Override
    @Transactional
    public TerminActionInformation cancelTermin(Integer terminId) {
        return terminDao.cancelTermin(terminId);
    }

    @Override
    public List<TerminDto> getAllPacientTermini(String username) {
        List<Termin> termini = terminDao.getAllPacientTermini(username);
        if (termini == null) {
            return null;
        }
        return termini.stream().map(t -> new TerminDto(
                t.getId(),
                t.getZacetek(),
                t.getKonec(),
                t.getJeZaseden(),
                t.getFizioterapevt().getUsername(),
                t.getFizioterapevt().getIme() + " " + t.getFizioterapevt().getPriimek()
        )).toList();
    }

    @Override
    public List<TerminDto> getTermini() {
        List<Termin> termini = terminDao.getTermini();
        if (termini != null) {
            return termini.stream().map(t -> new TerminDto(
                    t.getId(),
                    t.getZacetek(),
                    t.getKonec(),
                    t.getJeZaseden(),
                    t.getFizioterapevt().getUsername(),
                    t.getFizioterapevt().getIme() + " " + t.getFizioterapevt().getPriimek()
            )).toList();
        }
        return null;
    }

    @Override
    public TerminDto getTermin(Integer terminId) {
        Termin termin = terminDao.getTermin(terminId);
        if (termin != null) {
            return new TerminDto(
                    termin.getId(),
                    termin.getZacetek(),
                    termin.getKonec(),
                    termin.getJeZaseden(),
                    termin.getFizioterapevt().getUsername(),
                    termin.getFizioterapevt().getIme() + " " + termin.getFizioterapevt().getPriimek()
            );
        }
        return null;
    }

    @Override
    @Transactional
    public TerminActionInformation updateTermin(TerminDto dto) {
        TerminActionInformation information = terminDao.updateTermin(dto);
        return information;
    }
}
