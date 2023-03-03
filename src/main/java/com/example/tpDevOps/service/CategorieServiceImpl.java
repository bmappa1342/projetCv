package com.example.tpDevOps.service;

import com.example.tpDevOps.entities.Categorie;
import com.example.tpDevOps.repository.CategorieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private CategorieRepo categorieRepo;

    @Override
    public Categorie addCategorie(Categorie categorie) {
        return categorieRepo.save(categorie);
    }
}
