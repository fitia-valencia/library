package com.example.BiblioFarany.controller;

import java.util.List;

// import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Emprunt;
import com.example.BiblioFarany.model.Penalisation;
import com.example.BiblioFarany.model.Reservation;
import com.example.BiblioFarany.service.AdherentService;
import com.example.BiblioFarany.service.EmpruntService;
import com.example.BiblioFarany.service.PenalisationService;
import com.example.BiblioFarany.service.ReservationService;
import com.example.BiblioFarany.service.TypeAdherentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdherentController {
    private final AdherentService adherentService;
    private final TypeAdherentService typeAdherentService;
    private final EmpruntService empruntService;
    private final ReservationService reservationService;
    private final PenalisationService penalisationService;

    @Autowired
    public AdherentController(AdherentService adherentService, TypeAdherentService typeAdherentService,
            EmpruntService empruntService, ReservationService reservationService,
            PenalisationService penalisationService) {
        this.adherentService = adherentService;
        this.typeAdherentService = typeAdherentService;
        this.empruntService = empruntService;
        this.reservationService = reservationService;
        this.penalisationService = penalisationService;
    }

    @GetMapping("/adherent-login")
    public String adherentLogin() {
        return "adherent-login";
    }

    @GetMapping("/adherent-register")
    public String adherentRegister(Model model) {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("typesAdherent", typeAdherentService.getAllTypes());
        return "adherent-register";
    }

    @PostMapping("adherent-login")
    public String traiterLogin(@RequestParam String matricule, @RequestParam String motDePasse, Model model,
            HttpSession HttpSession) {
        Adherent adherent = adherentService.authentifierAdherent(matricule, motDePasse);
        if (adherent != null) {
            HttpSession.setAttribute("adherent", adherent);
            return "redirect:/liste-livre"; // Redirige vers la liste des livres
        } else {
            model.addAttribute("error", "Identifiants incorrects");
            return "adherent-login";
        }
    }

    @PostMapping("/adherent-register")
    public String inscrireAdherent(@ModelAttribute Adherent adherent, Model model,
            @RequestParam(value = "depuisAjout", required = false) Boolean depuisAjout) {
        if (adherentService.telephoneExist(adherent.getTelephone())) {
            model.addAttribute("error", "Un adhérent avec ce numéro de téléphone existe déjà.");
            model.addAttribute("typesAdherent", typeAdherentService.getAllTypes());
            if (Boolean.TRUE.equals(depuisAjout)) {
                model.addAttribute("depuisAjout", true);
            }
            return "adherent-register";
        }

        Adherent saved = adherentService.enregistrerAdherent(adherent);

        if (Boolean.TRUE.equals(depuisAjout)) {
            return "redirect:/liste-adherent?notif=ajout";
        }

        model.addAttribute("matricule", saved.getMatricule());
        model.addAttribute("adherent", saved);
        return "liste-livre";
    }

    @GetMapping("/liste-adherent")
    public String listeAdherents(
            @RequestParam(name = "typeId", required = false) Integer typeId,
            @RequestParam(name = "statut", required = false) String statut,
            @RequestParam(name = "notif", required = false) String notif,
            Model model) {

        List<Adherent> adherents = adherentService.getAllAdherents();
        model.addAttribute("typeAdherent", typeAdherentService.getAllTypes());

        if (typeId != null) {
            adherents = adherents.stream()
                    .filter(a -> a.getTypeAdherent().getId().equals(typeId))
                    .toList();
        }

        if ("actif".equalsIgnoreCase(statut)) {
            adherents = adherents.stream().filter(Adherent::isActive).toList();
        } else if ("inactif".equalsIgnoreCase(statut)) {
            adherents = adherents.stream().filter(a -> !a.isActive()).toList();
        }

        model.addAttribute("adherents", adherents);
        model.addAttribute("typesAdherents", typeAdherentService.getAllTypes());

        if ("ajout".equals(notif)) {
            model.addAttribute("message", "Nouvel adhérent enregistré avec succès !");
        }

        return "liste-adherent";
    }

    @GetMapping("/details-adherent")
    public String afficherDetails(@RequestParam("adherentId") Integer id, Model model) {
        Adherent adherent = adherentService.getAdherentById(id);
        List<Emprunt> emprunts = empruntService.getEmpruntsParAdherent(adherent);
        List<Reservation> reservations = reservationService.getReservationsParAdherent(id);
        List<Penalisation> penalites = penalisationService.getPenalitesParAdherent(adherent); // <-- ajoute cette ligne

        model.addAttribute("adherent", adherent);
        model.addAttribute("emprunts", emprunts);
        model.addAttribute("reservations", reservations);
        model.addAttribute("penalites", penalites); // <-- ajoute cette ligne

        return "details-adherent";
    }

    @GetMapping("/ajout-adherent")
    public String afficherFormulaireAjoutAdherent(Model model) {
        model.addAttribute("adherent", new Adherent());
        model.addAttribute("typesAdherent", typeAdherentService.getAllTypes());
        model.addAttribute("depuisAjout", true);
        return "adherent-register";
    }

}
