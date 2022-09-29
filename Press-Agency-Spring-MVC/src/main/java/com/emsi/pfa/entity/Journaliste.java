package com.emsi.pfa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "journalistes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Journaliste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="journaliste")
    private List<SuivreJournaliste> suivreJournalistes;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="journaliste")
    private List<Article> articles;

}
