package com.emsi.pfa.controller;

import com.emsi.pfa.service.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admins")
public class AdministrateurController {

    @Autowired
    AdministrateurService administrateurService;



    @GetMapping
    public String adminHome()
    {
        return "admin/home";
    }

}
