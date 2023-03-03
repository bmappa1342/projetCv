package com.example.tpDevOps.service;

import com.example.tpDevOps.entities.Demandeur;
import com.example.tpDevOps.entities.Offre;
import com.example.tpDevOps.repository.DemandeurRepo;
import com.example.tpDevOps.repository.OffreRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class OffreServiceImpl implements OffreService{
    private OffreRepo offreRepo;

    @Override
    public Offre addOffre(Offre offre) {
        return offreRepo.save(offre);
    }
}
