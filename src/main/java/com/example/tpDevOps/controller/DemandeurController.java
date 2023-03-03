package com.example.tpDevOps.controller;

import com.example.tpDevOps.entities.Demandeur;
import com.example.tpDevOps.repository.DemandeurRepo;
import com.example.tpDevOps.service.DemandeurService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class DemandeurController {

    private DemandeurRepo demandeurRepo;
    private DemandeurService demandeurService;


    @GetMapping("/listCv")
    public String showPageListDemande(Model model) {
        model.addAttribute("ListCv");
        return "demandeur/listCv";
        }
    @GetMapping("/admin/demandeur/listCv")
    public String showlistCv(Model model){
        List<Demandeur> ListDemande = demandeurRepo.findAll();
        model.addAttribute("ListDemande",ListDemande);
        return "demandeur/listCv";
    }

    @PostMapping(path = "/addCv")
    public String add(Model model, @Valid Demandeur demandeur, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "demandeur/formCv";
        demandeurService.addDemandeur(demandeur);
        return "redirect:/demandeur/listCv";
    }
    @GetMapping("/user/formCv")
    public String formDemande(Model model){
        model.addAttribute("demandeur", new Demandeur());
        return "demandeur/formCv";
    }
    @GetMapping("/editDemande")
    public String editDemande(Model model,Integer id){
        Demandeur demandeur = demandeurRepo.findById(id).orElse(null);
        if (demandeur ==null) throw  new RuntimeException("demandeur introuvable");
        model.addAttribute("demandeur", demandeur);
        return "demandeur/editCv";
    }
}
