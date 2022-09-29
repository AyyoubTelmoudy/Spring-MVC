package com.emsi.pfa.service.impl;

import com.emsi.pfa.repository.AdministrateurRepository;
import com.emsi.pfa.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurServiceImpl implements AdministrateurService {
    @Autowired
    AdministrateurRepository administrateurRepository;

}
