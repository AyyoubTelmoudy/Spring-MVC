package com.emsi.pfa.controller;


import com.emsi.pfa.entity.Commentaire;
import com.emsi.pfa.entity.Lecteur;
import com.emsi.pfa.entity.SuivreJournaliste;
import com.emsi.pfa.entity.UserDetailsImpl;
import com.emsi.pfa.service.ArticleService;
import com.emsi.pfa.service.JournalisteService;
import com.emsi.pfa.service.LecteurService;
import com.emsi.pfa.service.SharedService;
import com.emsi.pfa.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping
public class SharedController {

    @Autowired
    SharedService sharedService;

    @Autowired
    LecteurService lecteurService;
    @Autowired
    ArticleService articleService;
    @Autowired
    JournalisteService journalisteService;

    @GetMapping("/")
    public String home(Model model)
    {
        Object principal =Utils.getAuthenticatedUser();
        if(principal instanceof UserDetailsImpl&&((UserDetailsImpl)principal).getRole().equals("ROLE_LECTEUR"))
        {
            model.addAttribute("isAuthenticated",true);
            model.addAttribute("activeUser",((UserDetailsImpl)principal).getEmail());
        }
        else
        {
            model.addAttribute("isAuthenticated",false);
        }

        model.addAttribute("culture",this.articleService.getArticleBySujet("Culture"));
        model.addAttribute("sport",this.articleService.getArticleBySujet("Sport"));
        model.addAttribute("politique",this.articleService.getArticleBySujet("Politique"));
        model.addAttribute("economie",this.articleService.getArticleBySujet("Économie"));
        model.addAttribute("journalistes",this.journalisteService.getAllJournalistes());

        return "index";
    }

    @GetMapping("/sign-up")
    public String signUp(HttpServletResponse httpServletResponse, Model model) throws IOException {
        Utils.redirctUser(httpServletResponse);
        model.addAttribute("lecteur",new Lecteur());
        return "signUp";
    }

    @PostMapping("/sign-up")
    public String saveLecteur(@ModelAttribute  Lecteur lecteur)
    {
        lecteurService.saveLecteur(lecteur);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(HttpServletResponse httpServletResponse) throws IOException {
       Utils.redirctUser(httpServletResponse);
        return "login";
    }


    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/";
    }

    @GetMapping("/article-details/{articlePublicId}")
    public String articleDetails(Model model,@PathVariable String articlePublicId) throws IOException {

        Object principal =Utils.getAuthenticatedUser();
        if(principal instanceof UserDetailsImpl&&((UserDetailsImpl)principal).getRole().equals("ROLE_LECTEUR"))
        {
            model.addAttribute("isAuthenticated",true);
            if(articleService.getArticle(articlePublicId)==null)
                return "notFoundResource";
            model.addAttribute("commentaire",new Commentaire());
            List<SuivreJournaliste> suivreJournalistes=articleService.getArticle(articlePublicId).getJournaliste().getSuivreJournalistes();
            model.addAttribute("journalistIsFollowed",false);
            for (SuivreJournaliste suivreJournaliste:suivreJournalistes) {
               if(lecteurService.getLecteur(suivreJournaliste.getLecteur().getEmail()).getEmail().equals(((UserDetailsImpl)principal).getEmail()))
                   model.addAttribute("journalistIsFollowed",true);
            }
        }
        else
        {
            model.addAttribute("isAuthenticated",false);
            if(articleService.getArticle(articlePublicId)==null)
                return "notFoundResource";
        }
        model.addAttribute("commentaires",articleService.getArticleCommentaires(articlePublicId));
        model.addAttribute("article",articleService.getArticle(articlePublicId));
        return "articleDetails";
    }

}