package com.example.BiblioFarany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.BiblioFarany.model.Demande;
import com.example.BiblioFarany.service.DemandeService;

@Controller
public class DemandeController {
    @Autowired
    private DemandeService demandeService;
    @GetMapping("liste-demande")
    public String listeDemande(Model model) {
        List<Demande> demandes = demandeService.getAllDemande();
        model.addAttribute("demandes",demandes);
        return "liste-demande";
    }
}
