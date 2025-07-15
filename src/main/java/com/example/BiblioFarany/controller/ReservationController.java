package com.example.BiblioFarany.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.Livre;
import com.example.BiblioFarany.model.Reservation;
import com.example.BiblioFarany.service.ExemplaireService;
import com.example.BiblioFarany.service.LivreService;
import com.example.BiblioFarany.service.PenalisationService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {
    @Autowired
    private PenalisationService penalisationService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private LivreService livreService;

    @GetMapping("reserver")
    public String afficherFormulaireReservation(@RequestParam("livreId") Long idLivre, Model model) {
        Livre livre = livreService.getById(idLivre);
        model.addAttribute("livre", livre);
        return "reserver";
    }

    // @PostMapping("reserver")
    // public String demanderEmprunterLivre(@RequestParam("livre") Livre livre,
    //         @RequestParam("typeDemandeId") Integer idTypeDemande, @RequestParam("dateDemande") LocalDate dateDemande,
    //         Model model, HttpSession session) {
    //     Adherent adherent = (Adherent) session.getAttribute("adherent");
    //     boolean aDesPenoActives = penalisationService.aDesPenalisationsActives(adherent);
    //     Exemplaire exemplaireDipoPourLivre = exemplaireService.getExemplaireDisponibleByLivreId(livre.getId().longValue());
    //     int ageMin = livre.getRestrictionAge();

    //     return "redirect:/liste-livre";
    // }
}