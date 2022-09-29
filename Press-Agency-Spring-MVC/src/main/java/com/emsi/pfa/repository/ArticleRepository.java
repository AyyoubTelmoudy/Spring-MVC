package com.emsi.pfa.repository;

import com.emsi.pfa.entity.Article;
import com.emsi.pfa.entity.Journaliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findByJournaliste(Journaliste journaliste);
    Article findByPublicId(String articlePublicId);

}
