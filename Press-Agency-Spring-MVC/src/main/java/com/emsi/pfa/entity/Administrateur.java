package com.emsi.pfa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "administrateurs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false,unique = false)
    private String email ;
    @Column(nullable = false)
    private String password;


}
