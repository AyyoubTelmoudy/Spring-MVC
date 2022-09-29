package com.emsi.pfa.service.impl;

import com.emsi.pfa.entity.SuivreJournaliste;
import com.emsi.pfa.repository.SuivreJournalisteRepository;
import com.emsi.pfa.service.SuirvreJournalisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuivreJournalisteServiceImpl implements SuirvreJournalisteService {
    @Autowired
    SuivreJournalisteRepository suivreJournalisteRepository;

    @Override
    public void deleteSuivreJournalisteService(SuivreJournaliste suivreJournaliste) {
        suivreJournalisteRepository.deleteByPublicId(suivreJournaliste.getPublicId());
    }
}
