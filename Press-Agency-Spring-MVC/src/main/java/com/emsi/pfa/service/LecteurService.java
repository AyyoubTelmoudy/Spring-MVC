package com.emsi.pfa.service;

import com.emsi.pfa.entity.Lecteur;

public interface LecteurService {
    Lecteur saveLecteur(Lecteur lecteur);
    void followJournalist(String articlePublicId);

    Lecteur getLecteur(String email);

    void unfollowJournalist(String articlePublicId);
}
