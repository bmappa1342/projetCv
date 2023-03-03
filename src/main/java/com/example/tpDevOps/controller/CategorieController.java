package com.example.tpDevOps.controller;

import com.example.tpDevOps.entities.Categorie;
import com.example.tpDevOps.entities.Demandeur;
import com.example.tpDevOps.entities.Offre;
import com.example.tpDevOps.repository.CategorieRepo;
import com.example.tpDevOps.repository.OffreRepo;
import com.example.tpDevOps.service.CategorieService;
import com.example.tpDevOps.service.OffreService;
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
public class CategorieController {

    private CategorieRepo categorieRepo;
    private CategorieService categorieService;


    @GetMapping("/listcategorie")
    public String showPageListCategorie(Model model) {
        model.addAttribute("ListCategorie");
        return "categorie/listCategorie";
        }
    @GetMapping("/admin/formcategorie")
    public String formDemande(Model model){
        model.addAttribute("categorie", new Categorie());
        return "categorie/formCategorie";
    }
    @PostMapping(path = "/addcategorie")
    public String add(Model model, @Valid Categorie categorie, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "categorie/formcategorie";
        categorieService.addCategorie(categorie);
        return "redirect:/admin/categorie/listcategorie";
    }
    @GetMapping("/admin/categorie/listcategorie")
    public String showlistcategorie(Model model){
        List<Categorie> ListCategorie = categorieRepo.findAll();
        model.addAttribute("ListCategorie",ListCategorie);
        return "categorie/listCategorie";
    }
    @GetMapping("/editcategorie")
    public String editCategorie(Model model,Integer id){
        Categorie categorie= categorieRepo.findById(id).orElse(null);
        if (categorie ==null) throw  new RuntimeException("offre introuvable");
        model.addAttribute("categorie", categorie);
        return "categorie/editCategorie";
    }
}
