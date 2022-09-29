package com.emsi.pfa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "commentaires")
@Data
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false)
    private String contenu;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_article")
    private Article article;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_lecteur")
    private Lecteur lecteur;
}
