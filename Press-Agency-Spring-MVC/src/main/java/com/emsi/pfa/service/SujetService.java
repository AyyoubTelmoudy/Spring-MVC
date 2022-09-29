package com.emsi.pfa.service;

import com.emsi.pfa.entity.Sujet;

import java.util.List;

public interface SujetService {

    List<Sujet> getAllSujets();

    void saveSujet(Sujet sujet);

    Sujet getSujet(String subjectPublicId);

    void updateSujet(Sujet sujet, String subjectPublicId);

    void deleteSujet(String subjectPublicId);
}
