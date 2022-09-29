package com.emsi.pfa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "adresses")
@Data
@NoArgsConstructor
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false)
    private String pays;
    @Column(nullable = false)
    private String ville;
    @Column(nullable = false)
    private String quartier;
    @Column(nullable = false)
    private String code_postal;
    public Adresse(String publicId, String pays, String ville, String quartier, String code_postal) {
        this.publicId = publicId;
        this.pays = pays;
        this.ville = ville;
        this.quartier = quartier;
        this.code_postal = code_postal;
    }
}
