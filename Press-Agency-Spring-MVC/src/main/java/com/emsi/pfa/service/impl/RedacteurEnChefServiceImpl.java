package com.emsi.pfa.service.impl;

import com.emsi.pfa.repository.RedacteurEnChefRepository;
import com.emsi.pfa.service.RedacteurEnChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedacteurEnChefServiceImpl implements RedacteurEnChefService {
    @Autowired
    RedacteurEnChefRepository redacteurEnChefRepository;
}
