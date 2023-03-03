package com.example.tpDevOps.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "offre")
@Data @AllArgsConstructor
@NoArgsConstructor
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOffre;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Categorie catOffre;
}
