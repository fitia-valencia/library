package com.example.BiblioFarany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.BiblioFarany.model.Bibliothecaire;
import com.example.BiblioFarany.service.BibliothecaireService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BibliothecaireController {
    private final BibliothecaireService bibliothecaireService;
    @Autowired
    public BibliothecaireController(BibliothecaireService bibliothecaireService) {
        this.bibliothecaireService = bibliothecaireService;
    }

    @GetMapping("/bibliothecaire-login")
    public String bibliothecaireLogin(){
        return "bibliothecaire-login";
    }

    @PostMapping("bibliothecaire-login")
    public String traiterLogin(@RequestParam String matricule, @RequestParam String motDePasse, Model model, HttpSession HttpSession){
        Bibliothecaire bibliothecaire = bibliothecaireService.authentifierBibliothecaire(matricule, motDePasse);
        if(bibliothecaire != null) {
            HttpSession.setAttribute("bibliothecaire", bibliothecaire);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "bibliothecaire-login";
        }
    }

}
