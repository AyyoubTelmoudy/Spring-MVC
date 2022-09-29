package com.emsi.pfa.service.impl;

import com.emsi.pfa.entity.Journaliste;
import com.emsi.pfa.repository.JournalisteRepository;
import com.emsi.pfa.service.JournalisteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalisteServiceImpl implements JournalisteService {
    @Autowired
    JournalisteRepository journalisteRepository;

    @Override
    public List<Journaliste> getAllJournalistes() {
        return journalisteRepository.findAll();
    }
}
