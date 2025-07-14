package com.example.BiblioFarany.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Demande;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.TypeDemande;
import com.example.BiblioFarany.service.PenalisationService;
import com.example.BiblioFarany.service.AdherentService;
import com.example.BiblioFarany.service.DemandeService;
import com.example.BiblioFarany.service.TypeDemandeService;

import jakarta.servlet.http.HttpSession;

import com.example.BiblioFarany.service.EmpruntService;
import com.example.BiblioFarany.service.ExemplaireService;

@Controller
public class EmpruntController {
    @Autowired
    private AdherentService adherentService;
    @Autowired
    private PenalisationService penalisationService;
    @Autowired
    private EmpruntService empruntService;
    @Autowired
    private ExemplaireService exemplaireService;
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private TypeDemandeService typeDemandeService;

    @PostMapping("/emprunter")
    public String demanderEmprunterLivre(@RequestParam("livreId") Integer idLivre,
            @RequestParam("typeDemandeId") Integer idTypeDemande, Model model, HttpSession session) {
        Adherent adherent = (Adherent) session.getAttribute("adherent");
        boolean aDesPenoActives = penalisationService.aDesPenalisationsActives(adherent);
        int nombreExemplairesRestantsPourEmprunt = empruntService.getNombreExemplairesRestantsPourEmprunt(adherent);
        int nombreExemplaireDispoPourLeLivre = exemplaireService.nombreExemplairesDisponibles(idLivre);
        if (adherent.isActive() && !aDesPenoActives && nombreExemplairesRestantsPourEmprunt > 0
                && nombreExemplaireDispoPourLeLivre > 0) {
            // empruntService.creerEmprunt(adherent,
            // exemplaireService.getExemplaireById(idLivre));
            TypeDemande typeDemande = typeDemandeService.findById(idTypeDemande);
            Exemplaire exemplaire = exemplaireService.getExemplaireDisponibleByLivreId(idLivre);
            Demande saved = demandeService.saveDemande(typeDemande, adherent, exemplaire, LocalDate.now(),
                    LocalDate.now());
            model.addAttribute("demande", saved);
            model.addAttribute("adherent", adherent);
            model.addAttribute("exemplaire", exemplaire);
            model.addAttribute("typeDemande", typeDemande);
            return "redirect:/demande-emprunt";
        }
        return "liste-livre";
    }

    @GetMapping("/demande-emprunt")
    public String afficherDemandesEmprunt(Model model, HttpSession session) {
        Adherent adherent = (Adherent) session.getAttribute("adherent");

        // Récupérer les demandes ou emprunts liés à l'adhérent
        List<Demande> demandes = demandeService.findByAdherent(adherent);

        // Envoyer à la vue
        model.addAttribute("demandes", demandes);
        System.out.println("Nombre de demandes trouvées : " + demandes.size());
        for (Demande d : demandes) {
            System.out.println("Demande ID: " + d.getId() + ", Livre: " + d.getExemplaire().getLivre().getTitre());
        }

        return "demande-emprunt";
    }
}
