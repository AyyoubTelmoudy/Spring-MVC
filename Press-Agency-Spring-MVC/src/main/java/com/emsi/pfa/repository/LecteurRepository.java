package com.emsi.pfa.repository;

import com.emsi.pfa.entity.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecteurRepository extends JpaRepository<Lecteur,Long> {
    Lecteur findByEmail(String email);
}
