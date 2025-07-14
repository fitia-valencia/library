package com.example.BiblioFarany.controller;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Penalisation;
import com.example.BiblioFarany.service.PenalisationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PenalisationController {

    private final PenalisationService penalisationService;

    public PenalisationController(PenalisationService penalisationService) {
        this.penalisationService = penalisationService;
    }

    @GetMapping("/liste-penalisation")
    public String afficherPenalisations(HttpSession session, Model model) {
        Adherent adherent = (Adherent) session.getAttribute("adherent");

        if (adherent == null) {
            return "redirect:/adherent-login"; // ou page d'accueil
        }

        List<Penalisation> penalites = penalisationService.getPenalitesParAdherent(adherent);
        model.addAttribute("penalites", penalites);
        return "liste-penalisation";
    }
}
