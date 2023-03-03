package com.example.tpDevOps.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorie")
@Data @AllArgsConstructor @NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorie;
    @Column(length = 150,nullable = false)
    private String libelle;
    @OneToMany(mappedBy = "catOffre",cascade = CascadeType.ALL)
    private List<Offre> offres;
}
