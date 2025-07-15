package com.example.BiblioFarany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Demande;
// import com.example.BiblioFarany.model.Emprunt;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.Prolongement;
import com.example.BiblioFarany.model.Reservation;
import com.example.BiblioFarany.model.TypeDemande;
// import com.example.BiblioFarany.model.Penalisation;
// import com.example.BiblioFarany.service.AdherentService;
import com.example.BiblioFarany.service.DemandeService;
import com.example.BiblioFarany.service.EmpruntService;
import com.example.BiblioFarany.service.ExemplaireService;
import com.example.BiblioFarany.service.PenalisationService;
import com.example.BiblioFarany.service.ProlongementService;
import com.example.BiblioFarany.service.ReservationService;

@Controller
public class DemandeController {
    @Autowired
    private DemandeService demandeService;
    @Autowired
    private PenalisationService penalisationService;
    @Autowired
    private EmpruntService empruntService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ProlongementService prolongementService;
     @Autowired
    private ExemplaireService exemplaireService;

    @GetMapping("liste-demande")
    public String listeDemande(Model model) {
        List<Demande> demandes = demandeService.getDemandesNonValidees();
        model.addAttribute("demandes",demandes);
        return "liste-demande";
    }

    @PostMapping("valider-demande")
    public String validerDemande(@RequestParam("demandeId") int idDemande, Model model) {
        Demande demande = demandeService.findById(idDemande);
        Adherent adherent = demande.getAdherent();
        Exemplaire exemplaire = demande.getExemplaire();
        boolean aDesPenoActives = penalisationService.aDesPenalisationsActives(adherent);
        int nombreExemplairesRestantsPourEmprunt = empruntService.getNombreExemplairesRestantsPourEmprunt(adherent);
        int nombreExemplaireDispoPourLeLivre = exemplaireService.nombreExemplairesDisponibles(Long.valueOf(exemplaire.getLivre().getId()));
        int ageMin = exemplaire.getLivre().getRestrictionAge();
        if(adherent.isActive() && !aDesPenoActives && nombreExemplairesRestantsPourEmprunt>0 && nombreExemplaireDispoPourLeLivre>0 && adherent.getAge()>=ageMin) {
            demande.setValidation(true);
            if(demande.getTypeDemande().getLibelle().equals("emprunt")){
                empruntService.creerEmprunt(adherent, exemplaire);
                return "redirect:/liste-emprunt";
            }
            if(demande.getTypeDemande().getLibelle().equals("reservation")){
                reservationService.creerReservation(adherent, exemplaire, demande);
                return "redirect:/liste-reservation";
            }
            if(demande.getTypeDemande().getLibelle().equals("prolongement")){
                prolongementService.creerProlongement(adherent, exemplaire);
                return "redirect:/liste-prolongement";
            }
        }
        return "redirect:/liste-demande";
    }
}
