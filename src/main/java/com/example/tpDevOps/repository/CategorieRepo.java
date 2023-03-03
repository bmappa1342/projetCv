package com.example.tpDevOps.repository;

import com.example.tpDevOps.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepo extends JpaRepository<Categorie,Integer> {
}
