package com.emsi.pfa.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false)
    private String titre;

    @Lob
    @Column(nullable = false)
    private String contenu;

    @Column(nullable = true)
    private LocalDate date;

    @Column(nullable = false)
    private boolean estValide;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_redacteur_en_chef")
    private RedacteurEnChef redacteurEnChef;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_journaliste")
    private Journaliste journaliste;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_sujet")
    private Sujet sujet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="article")
    private List<Commentaire> commentaires;

    @Lob
    @Column(nullable = true)
    private String image;

}
