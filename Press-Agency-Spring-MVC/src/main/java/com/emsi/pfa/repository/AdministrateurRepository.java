package com.emsi.pfa.repository;

import com.emsi.pfa.entity.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur,Long> {
    
    Administrateur findByEmail(String email);
    
}
