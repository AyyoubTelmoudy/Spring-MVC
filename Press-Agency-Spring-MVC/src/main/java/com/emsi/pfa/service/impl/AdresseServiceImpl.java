package com.emsi.pfa.service.impl;

import com.emsi.pfa.repository.AdresseRepository;
import com.emsi.pfa.entity.Adresse;
import com.emsi.pfa.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdresseServiceImpl implements AdresseService {
    @Autowired
    AdresseRepository adresseRepository;


    @Override
    public List<Adresse> getAllAdresses() {
        return null;
    }
}
