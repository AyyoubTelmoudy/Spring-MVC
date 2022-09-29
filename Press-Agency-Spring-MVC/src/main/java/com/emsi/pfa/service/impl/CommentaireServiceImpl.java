package com.emsi.pfa.service.impl;

import com.emsi.pfa.repository.CommentaireRepository;
import com.emsi.pfa.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentaireServiceImpl implements CommentaireService {
    @Autowired
    CommentaireRepository commentaireRepository;
}
