package com.emsi.pfa.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sujets")
@Data
@ToString
public class Sujet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String publicId;

    @Column(nullable = false,unique = true)
    public String sujet;

    @OneToMany(cascade = CascadeType.ALL,mappedBy ="sujet")
    private List<Article> articles;
}
