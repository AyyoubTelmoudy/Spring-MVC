package com.emsi.pfa.repository;

import com.emsi.pfa.entity.Journaliste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalisteRepository extends JpaRepository<Journaliste,Long> {
    Journaliste findByEmail(String email);

    Journaliste findByPublicId(String journalistePublicId);
}
