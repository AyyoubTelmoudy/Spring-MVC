package com.emsi.pfa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lecteurs")
@Data
public class Lecteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false,unique = true)
    private String email ;
    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="lecteur")
    private List<SuivreJournaliste> suivreJournalistes;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="lecteur")
    private List<Commentaire> commentaires;

}
