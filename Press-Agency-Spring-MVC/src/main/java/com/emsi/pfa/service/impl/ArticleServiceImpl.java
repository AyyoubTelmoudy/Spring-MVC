package com.emsi.pfa.service.impl;


import com.emsi.pfa.entity.Article;
import com.emsi.pfa.entity.Commentaire;
import com.emsi.pfa.entity.SuivreJournaliste;
import com.emsi.pfa.entity.UserDetailsImpl;
import com.emsi.pfa.repository.*;
import com.emsi.pfa.service.ArticleService;
import com.emsi.pfa.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    Utils utils;
    @Autowired
    SujetRepository sujetRepository;
    @Autowired
    JournalisteRepository journalisteRepository;
    @Autowired
    RedacteurEnChefRepository redacteurEnChefRepository;
    @Autowired
    LecteurRepository lecteurRepository;
    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    SuivreJournalisteRepository suivreJournalisteRepository;
    @Autowired
    private EmailSenderService senderService;

    @Override
    public Article saveArticle(Article article, String publicIdSujet, MultipartFile image) throws IOException {
        UserDetailsImpl journaliste=(UserDetailsImpl)Utils.getAuthenticatedUser();
        String nomImage=utils.genereteRandomString(30);

        String nomOrigine=image.getOriginalFilename().toString();
        String[] tokens=nomOrigine.split("[.]");
        String extensionImage=tokens[tokens.length-1];

        article.setSujet(sujetRepository.findByPublicId(publicIdSujet));
        article.setJournaliste(journalisteRepository.findByEmail(journaliste.getEmail()));
        article.setEstValide(false);
        article.setPublicId(utils.genereteRandomString(30));
        article.setImage("/upload/"+nomImage+"."+extensionImage);
        image.transferTo(new File(FileSystems.getDefault().getPath("").toAbsolutePath().toString()+"\\src\\main\\resources\\upload\\"+nomImage+"."+extensionImage));
        return articleRepository.save(article);
    }

    @Override
    public List<Article> getAllJournalisteArticles() {
        UserDetailsImpl journaliste=(UserDetailsImpl)Utils.getAuthenticatedUser();
        return articleRepository.findByJournaliste(journalisteRepository.findByEmail(journaliste.getEmail()));
    }

    @Override
    public Article getArticle(String articlePublicId) {
        return articleRepository.findByPublicId(articlePublicId);
    }

    @Override
    public Article updateArticle(Article article, String publicIdSujet) {
        Article art=articleRepository.findByPublicId(article.getPublicId());
        art.setTitre(article.getTitre());
        art.setSujet(sujetRepository.findByPublicId(publicIdSujet));
        art.setContenu(article.getContenu());
        return articleRepository.save(art);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public void deleteArticle(String articlePublicId) {
        articleRepository.deleteById(articleRepository.findByPublicId(articlePublicId).getId());
    }

    @Override
    public void validateArticle(String articlePublicId) {
        UserDetailsImpl chef=(UserDetailsImpl)Utils.getAuthenticatedUser();
        Article article=articleRepository.findByPublicId(articlePublicId);
        article.setEstValide(true);
        article.setDate(LocalDate.now());
        article.setRedacteurEnChef(redacteurEnChefRepository.findByEmail(chef.getEmail()));
        articleRepository.save(article);
        for (SuivreJournaliste suivreJournaliste:article.getJournaliste().getSuivreJournalistes()) {
            senderService.sendMail(suivreJournaliste.getLecteur().getEmail(),
                    "Nouvel Article de Votre Crédible Journaliste Preferé !",
                    "http://localhost:8080/article-details/"+articlePublicId);
        }
    }

    @Override
    public List<Commentaire> getArticleCommentaires(String articlePublicId) {
        return articleRepository.findByPublicId(articlePublicId).getCommentaires();
    }

    @Override
    public void addCommentToAricleAricle(Commentaire commentaire, String articlePublicId) {
        UserDetailsImpl lecteur=(UserDetailsImpl)Utils.getAuthenticatedUser();
        commentaire.setLecteur(lecteurRepository.findByEmail(lecteur.getEmail()));
        commentaire.setArticle(articleRepository.findByPublicId(articlePublicId));
        commentaire.setPublicId(utils.genereteRandomString(30));
        commentaireRepository.save(commentaire);
    }

    @Override
    public List<Article> getValidatedArticles() {
        List<Article> validatedArticles=new ArrayList<>();
        for (Article article: this.getAllArticles()) {
            if (article.isEstValide())
                validatedArticles.add(article);
        }
        return validatedArticles;
    }

    @Override
    public List<Article> getArticleBySujet(String sujet) {
        List<Article> articles=new ArrayList<>();
        for (Article article: this.getAllArticles()) {
            if (article.getSujet().getSujet().equals(sujet)&&article.isEstValide())
                articles.add(article);
        }
        return articles;
    }


}
