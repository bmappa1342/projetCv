package com.example.tpDevOps.controller;

import com.example.tpDevOps.entities.Categorie;
import com.example.tpDevOps.entities.Offre;
import com.example.tpDevOps.repository.CategorieRepo;
import com.example.tpDevOps.repository.OffreRepo;
import com.example.tpDevOps.service.OffreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class OffreController {

    private OffreRepo offreRepo;
    private CategorieRepo categorieRepo;
    private OffreService offreService;



    @GetMapping("/listoffre")
    public String showPageListOffre(Model model) {
        model.addAttribute("ListOffre");
        return "offre/listOffre";
        }
    @GetMapping("/user/offre/listOffre")
    public String showlistoffre(Model model){
        List<Offre> ListOffre = offreRepo.findAll();
        model.addAttribute("ListOffre",ListOffre);
        return "offre/listOffre";
    }
    @GetMapping("/admin/formoffre")
    public String formDemande(Model model){
        model.addAttribute("offre", new Offre());
        List<Categorie> categories = categorieRepo.findAll();
        model.addAttribute("categories", categories);
        return "offre/formOffre";
    }
    @PostMapping(path = "/addoffre")
    public String add(Model model, @Valid Offre offre,@RequestParam Integer idCategorie, BindingResult bindingResult){
        if(bindingResult.hasErrors()) return "offre/formOffre";
        model.addAttribute("categories", categorieRepo.findAll());
        Categorie categorie = categorieRepo.findById(idCategorie).get();
        offre.setCatOffre(categorie);
        //offreRepo.save(offre);
        offreService.addOffre(offre);
        return "redirect:/user/offre/listOffre";
    }
    @GetMapping("/editoffre")
    public String editOffre(Model model,Integer id){
        Offre offre = offreRepo.findById(id).orElse(null);
        if (offre ==null) throw  new RuntimeException("offre introuvable");
        model.addAttribute("offre", offre);
        return "offre/editOffre";
    }
}
