package com.example.tpDevOps.service;

import com.example.tpDevOps.entities.Demandeur;
import com.example.tpDevOps.repository.DemandeurRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DemandeurServiceImpl implements DemandeurService {
    private DemandeurRepo demandeurRepo;

    @Override
    public Demandeur addDemandeur(Demandeur demandeur) {
        return demandeurRepo.save(demandeur);
    }

}
