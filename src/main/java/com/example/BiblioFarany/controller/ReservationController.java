package com.example.BiblioFarany.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Demande;
import com.example.BiblioFarany.model.Emprunt;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.Livre;
import com.example.BiblioFarany.model.Reservation;
import com.example.BiblioFarany.model.TypeDemande;
import com.example.BiblioFarany.service.DemandeService;
import com.example.BiblioFarany.service.EmpruntService;
import com.example.BiblioFarany.service.ExemplaireService;
import com.example.BiblioFarany.service.LivreService;
import com.example.BiblioFarany.service.PenalisationService;
import com.example.BiblioFarany.service.ReservationService;
import com.example.BiblioFarany.service.TypeDemandeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {
    @Autowired
    private PenalisationService penalisationService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private LivreService livreService;
    @Autowired
    private EmpruntService empruntService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private TypeDemandeService typeDemandeService;

    @GetMapping("reserver")
    public String afficherFormulaireReservation(@RequestParam("livreId") Long idLivre, Model model) {
        Livre livre = livreService.getById(idLivre);
        model.addAttribute("livre", livre);
        return "reserver";
    }

    @PostMapping("reserver")
    public String demanderEmprunterLivre(
            @RequestParam("idLivre") Long idLivre,
            @RequestParam("dateDemande") LocalDate dateDemande,
            Model model,
            HttpSession session) {

        Livre livre = livreService.getById(idLivre);
        Integer idTypeDemande = 2;
        // Récupération de l'adhérent connecté depuis la session
        Adherent adherent = (Adherent) session.getAttribute("adherent");

        // Vérifie si l'adhérent a des pénalisations actives
        boolean aDesPenoActives = penalisationService.aDesPenalisationsActives(adherent);

        // Récupère un exemplaire disponible du livre demandé
        Exemplaire exemplaireDipoPourLivre = exemplaireService
                .getExemplaireDisponibleByLivreId(livre.getId().longValue());

        // Âge minimum requis pour emprunter ce livre
        int ageMin = livre.getRestrictionAge();

        // Récupère tous les emprunts existants
        List<Emprunt> allEmprunts = empruntService.getAllEmprunt();

        // Liste pour stocker les IDs d'exemplaires indisponibles à la date demandée
        // (empruntés)
        Set<Integer> exemplairesIndisponibles = new HashSet<>();

        for (Emprunt emprunt : allEmprunts) {
            LocalDate dateRetourReelle = emprunt.getDateRetourReelle();
            LocalDate dateEmprunt = emprunt.getDateEmprunt();

            // Si la dateRetourReelle est null, on considère que le livre est toujours
            // emprunté
            if (dateRetourReelle == null ||
                    (!dateDemande.isBefore(dateEmprunt) && !dateDemande.isAfter(dateRetourReelle))) {
                exemplairesIndisponibles.add(emprunt.getExemplaire().getId());
            }
        }

        // Récupère toutes les réservations existantes
        List<Reservation> allReservations = reservationService.getAll();

        for (Reservation reservation : allReservations) {
            // Si la date de réservation est égale à la date de la demande, l’exemplaire est
            // déjà réservé
            if (dateDemande.equals(reservation.getDateReservation())) {
                exemplairesIndisponibles.add(reservation.getExemplaire().getId());
            }
        }

        // Vérifie si l'exemplaire disponible ne fait pas partie des exemplaires déjà
        // réservés ou empruntés
        if (!aDesPenoActives && adherent.getAge()>=ageMin && exemplaireDipoPourLivre != null && !exemplairesIndisponibles.contains(exemplaireDipoPourLivre.getId())) {
            // Logique pour enregistrer la demande (non encore implémentée ici)
            TypeDemande typeDemande = typeDemandeService.findById(idTypeDemande);
            Demande saved = demandeService.saveDemande(typeDemande, adherent, exemplaireDipoPourLivre, dateDemande,
                    LocalDate.now(), false);
            model.addAttribute("demande", saved);
            model.addAttribute("adherent", adherent);
            model.addAttribute("exemplaire", exemplaireDipoPourLivre);
            model.addAttribute("typeDemande", typeDemande);
            // Redirection avec message de succès
            return "redirect:/demande-reservation?success=true";
        } else {
            // Redirection avec message d'erreur : demande non envoyée
            return "redirect:/liste-livre?error=demande_non_envoyee";
        }
    }

    @GetMapping("demande-reservation")
    public String afficherDemandesReservation() {
        return "demande-reservation";
    }

}