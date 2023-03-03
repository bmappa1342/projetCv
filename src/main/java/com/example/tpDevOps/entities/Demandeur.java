package com.example.tpDevOps.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "demandeur")
@Data
public class Demandeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 150,nullable = false)
    private String nom;
    @Column(length = 150,nullable = false)
    private String prenom;
    private int age;
    @Column(length = 150,nullable = false)
    private String adresse;
    @Column(length = 150,nullable = false)
    private String email;
    @Column(length = 150,nullable = false)
    private String telephone;
    @Column(length = 150,nullable = false)
    private String specialite;
    @Column(length = 150,nullable = false)
    private String niveauEtude;
    @Column(nullable = false)
    private String experienceProfessionnelle;


}
