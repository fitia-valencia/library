package com.example.BiblioFarany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BiblioFarany.model.Livre;
import com.example.BiblioFarany.service.GenreService;
// import com.example.BiblioFarany.model.Livre;
import com.example.BiblioFarany.service.LivreService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;
    @Autowired
    private GenreService genreService;
    
    @GetMapping("/liste-livre")
    public String afficherListeLivres(
        @RequestParam(required = false) Integer genreId,
        @RequestParam(required = false) Integer ageMin,
        Model model
    ) {
        List<Livre> livresFiltres = livreService.getLivresFiltres(genreId, ageMin);
        model.addAttribute("livres", livresFiltres);
        model.addAttribute("genres", genreService.getAllGenres()); // Pour alimenter la liste déroulante
        return "liste-livre";
    }


    @GetMapping("/ajout-livre")
    public String afficherFormulaireAjoutLivre(Model model, HttpSession session) {
        if (session.getAttribute("bibliothecaire") == null) {
            return "redirect:/liste-livre"; // sécurité côté back
        }

        model.addAttribute("livre", new Livre());
        model.addAttribute("genres", genreService.getAllGenres());
        return "ajout-livre";
    }

    @PostMapping("/ajout-livre")
    public String enregistrerLivre(@ModelAttribute Livre livre, HttpSession session, Model model) {
        if (session.getAttribute("bibliothecaire") == null) {
            return "redirect:/liste-livre"; // empêche enregistrement sans bibliothécaire
        }

        livreService.ajouterLivre(livre);
        return "redirect:/liste-livre?ajoutSuccess=true";
    }

}
