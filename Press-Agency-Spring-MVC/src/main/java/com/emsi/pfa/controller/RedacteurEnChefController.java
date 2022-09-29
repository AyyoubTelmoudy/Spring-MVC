package com.emsi.pfa.controller;


import com.emsi.pfa.entity.Subject;
import com.emsi.pfa.entity.Sujet;
import com.emsi.pfa.service.ArticleService;
import com.emsi.pfa.service.RedacteurEnChefService;
import com.emsi.pfa.service.SujetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/chefs")
public class RedacteurEnChefController {

    @Autowired
    RedacteurEnChefService redacteurEnChefService;

    @Autowired
    ArticleService articleService;

    @Autowired
    SujetService sujetService;


    @GetMapping
    public String redacteurEnChefHome(Model model)
    {
        model.addAttribute("articles",this.articleService.getAllArticles());
        return "chef/home";
    }

    @GetMapping("/delete-article/{articlePublicId}")
    public String deleteArticle(@PathVariable String articlePublicId)
    {
        articleService.deleteArticle(articlePublicId);
        return "redirect:/chefs";
    }

    @GetMapping("/validate-article/{articlePublicId}")
    public String validateArticle(@PathVariable String articlePublicId)
    {
        articleService.validateArticle(articlePublicId);
        return "redirect:/chefs";
    }

    @GetMapping("/sujets")
    public String getSujetsList(Model model)
    {
        model.addAttribute("sujets",this.sujetService.getAllSujets());
        return "chef/sujets";
    }

    @GetMapping("/add-subject")
    public String addSubjectForm(Model model)
    {
        model.addAttribute("subject",new Subject());
        return "chef/addSubject";
    }

    @PostMapping("/add-subject")
    public String addsubject(@ModelAttribute Subject subject)
    {
              Sujet sujet=new Sujet();
              sujet.setSujet(subject.getSujet());
        this.sujetService.saveSujet(sujet);
        return "redirect:/chefs/sujets";
    }

    @GetMapping("/edit-subject/{subjectPublicId}")
    public String editSubjectForm(Model model, @PathVariable String subjectPublicId)
    {
        Subject subject=new Subject();
        BeanUtils.copyProperties(this.sujetService.getSujet(subjectPublicId),subject);
        model.addAttribute("subject",subject);
        return "chef/editSubject";
    }

    @PostMapping("/update-subject")
    public String updateSubject(@ModelAttribute Subject subject,@RequestParam("publicId") String subjectPublicId)
    {
        Sujet sujet=new Sujet();
        BeanUtils.copyProperties(subject,sujet);
        this.sujetService.updateSujet(sujet,subjectPublicId);
        return "redirect:/chefs/sujets";
    }

    @GetMapping("/delete-subject/{subjectPublicId}")
    public String deleteSubject(@PathVariable String subjectPublicId)
    {
        this.sujetService.deleteSujet(subjectPublicId);
        return "redirect:/chefs/sujets";
    }










}
