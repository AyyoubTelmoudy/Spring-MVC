package com.emsi.pfa.service;

import com.emsi.pfa.entity.Article;
import com.emsi.pfa.entity.Commentaire;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ArticleService {

    Article saveArticle(Article article, String publicIdSujet, MultipartFile image) throws IOException;

    List<Article> getAllJournalisteArticles();

    Article getArticle(String articlePublicId);

    Article updateArticle(Article article, String publicIdSujet);

    List<Article> getAllArticles();

    void deleteArticle(String articlePublicId);

    void validateArticle(String articlePublicId);

    List<Commentaire> getArticleCommentaires(String articlePublicId);

    void addCommentToAricleAricle(Commentaire commentaire, String articlePublicId);

    List<Article> getValidatedArticles();

    List<Article> getArticleBySujet(String sujet);

}
