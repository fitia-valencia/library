package com.example.BiblioFarany.controller;

import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.service.ExemplaireService;
import com.example.BiblioFarany.service.LivreService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExemplaireController {

    @Autowired
    private LivreService livreService;
    @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping("/liste-exemplaire")
public String afficherExemplaires(@RequestParam(required = false) Long livreId,
                                  @RequestParam(required = false) Boolean disponibilite,
                                  Model model) {
    List<Exemplaire> exemplaires;

    if (livreId != null && disponibilite != null) {
        exemplaires = exemplaireService.filtrerParLivreEtDisponibilite(livreId, disponibilite);
    } else if (livreId != null) {
        exemplaires = exemplaireService.filtrerParLivre(livreId);
    } else if (disponibilite != null) {
        exemplaires = exemplaireService.filtrerParDisponibilite(disponibilite);
    } else {
        exemplaires = exemplaireService.getAll();
    }

    model.addAttribute("exemplaires", exemplaires);
    model.addAttribute("livres", livreService.getAllLivres()); // Pour le menu déroulant des livres
    return "liste-exemplaire";
}


    @GetMapping("/ajout-exemplaire")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("livres", livreService.getAllLivres());
        return "ajout-exemplaire";
    }

    @PostMapping("/ajout-exemplaire")
    public String ajouterExemplaire(@RequestParam("livreId") Integer livreId) {
        exemplaireService.ajouterExemplairePourLivre(livreId);
        return "redirect:/liste-exemplaire?ajoutExemplaire=true";
    }

    
}
