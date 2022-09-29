package com.emsi.pfa.service.impl;

import com.emsi.pfa.entity.Journaliste;
import com.emsi.pfa.entity.Lecteur;
import com.emsi.pfa.entity.SuivreJournaliste;
import com.emsi.pfa.entity.UserDetailsImpl;
import com.emsi.pfa.repository.ArticleRepository;
import com.emsi.pfa.repository.LecteurRepository;
import com.emsi.pfa.repository.SuivreJournalisteRepository;
import com.emsi.pfa.service.LecteurService;
import com.emsi.pfa.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class LecteurServiceImpl implements LecteurService {

    @Autowired
    LecteurRepository lecteurRepository;
    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    SuivreJournalisteRepository suivreJournalisteRepository;
    @Autowired
    ArticleRepository articleRepository;


    @Override
    public Lecteur saveLecteur(Lecteur lecteur) {
        lecteur.setPublicId(utils.genereteRandomString(32));
        lecteur.setPassword(bCryptPasswordEncoder.encode(lecteur.getPassword()));
        return this.lecteurRepository.save(lecteur);
    }

    @Override
    public void followJournalist(String articlePublicId) {
        UserDetailsImpl lecteur=(UserDetailsImpl)Utils.getAuthenticatedUser();
        SuivreJournaliste suivreJournaliste=new SuivreJournaliste();
        suivreJournaliste.setJournaliste(articleRepository.findByPublicId(articlePublicId).getJournaliste());
        suivreJournaliste.setLecteur(lecteurRepository.findByEmail(lecteur.getEmail()));
        suivreJournaliste.setPublicId(utils.genereteRandomString(30));
        suivreJournalisteRepository.save(suivreJournaliste);
    }

    @Override
    public Lecteur getLecteur(String email) {
        return lecteurRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public void unfollowJournalist(String articlePublicId) {
        UserDetailsImpl lecteur=(UserDetailsImpl)Utils.getAuthenticatedUser();
        Journaliste journaliste=articleRepository.findByPublicId(articlePublicId).getJournaliste();
        List<SuivreJournaliste> suivreJournalistes=journaliste.getSuivreJournalistes();

        for (SuivreJournaliste suivreJournaliste: suivreJournalistes) {
            if(suivreJournaliste.getJournaliste().getEmail().equals(journaliste.getEmail())&&suivreJournaliste.getLecteur().getEmail().equals(lecteur.getEmail()))
            {
               suivreJournalisteRepository.deleteByPublicId(suivreJournaliste.getPublicId());
            }
        }
    }

}
