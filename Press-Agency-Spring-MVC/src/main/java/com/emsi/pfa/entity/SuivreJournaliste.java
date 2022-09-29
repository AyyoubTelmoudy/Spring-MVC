package com.emsi.pfa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "suivre_journalistes")
@Data
public class SuivreJournaliste {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_lecteur")
    private Lecteur lecteur;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_journaliste")
    private Journaliste journaliste;
}
