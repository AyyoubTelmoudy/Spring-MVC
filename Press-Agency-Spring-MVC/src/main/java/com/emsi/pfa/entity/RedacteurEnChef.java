package com.emsi.pfa.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "redacteurs_en_chef")
@Data
public class RedacteurEnChef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false)
    private String nom ;
    @Column(nullable = false)
    private String prenom ;
    @Column(nullable = false)
    private String nationalite;
    @Column(nullable = false,unique = true)
    private String email ;
    @Column(nullable = false)
    private String password;
    @Column(nullable = true)
    private String image;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_adresse")
    private Adresse adresse ;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="redacteurEnChef")
    private List<Article> articles;
}
