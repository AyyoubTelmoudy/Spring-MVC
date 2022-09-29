package com.emsi.pfa.repository;

import com.emsi.pfa.entity.RedacteurEnChef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedacteurEnChefRepository extends JpaRepository<RedacteurEnChef,Long> {
    RedacteurEnChef findByEmail(String email);
}
