package com.emsi.pfa.controller;


import com.emsi.pfa.entity.Article;
import com.emsi.pfa.service.ArticleService;
import com.emsi.pfa.service.SujetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/journalistes")
public class JournalisteController {

    @Autowired
    ArticleService articleService;
    @Autowired
    SujetService sujetService;

    @GetMapping
    public String journalisteHome(Model model)
    {
        model.addAttribute("articles",this.articleService.getAllJournalisteArticles());
        articleService.getAllJournalisteArticles();
        return  "journaliste/home";
    }


    @GetMapping("/type-article")
    public String typeArticle(Model model)
    {
        model.addAttribute("article",new Article());
        model.addAttribute("sujets",this.sujetService.getAllSujets());
        return "journaliste/typeAticle";
    }

    @PostMapping("/save-article")
    public String saveAricle(@ModelAttribute Article article, @RequestParam("publicIdSujet") String publicIdSujet,@RequestParam("img") MultipartFile image) throws IOException {
       articleService.saveArticle(article,publicIdSujet,image);
        return "redirect:/journalistes";
    }


    @GetMapping("/edit-article/{articlePublicId}")
    public String editArticle(Model model,@PathVariable String articlePublicId)
    {    if(articleService.getArticle(articlePublicId)==null)
        return "notFoundResource";
        model.addAttribute("article",articleService.getArticle(articlePublicId));
        model.addAttribute("sujets",this.sujetService.getAllSujets());
        return "journaliste/editArticle";
    }

    @PostMapping("/update-article")
    public String updateArticle(@ModelAttribute Article article, @RequestParam("publicIdSujet") String publicIdSujet)
    {
        articleService.updateArticle(article,publicIdSujet);
        return "redirect:/journalistes";
    }





}
