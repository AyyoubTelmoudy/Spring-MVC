package com.emsi.pfa.repository;

import com.emsi.pfa.entity.SuivreJournaliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface SuivreJournalisteRepository extends JpaRepository<SuivreJournaliste,Long> {

    @Modifying
    @Query(value = "DELETE FROM suivre_journalistes WHERE suivre_journalistes.public_id =?1",nativeQuery = true)
    void deleteByPublicId(String publicId);

}
