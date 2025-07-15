package com.example.BiblioFarany.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.example.BiblioFarany.model.TypeDemande;
import com.example.BiblioFarany.service.PenalisationService;
import com.example.BiblioFarany.service.AdherentService;
import com.example.BiblioFarany.service.DemandeService;
import com.example.BiblioFarany.service.TypeDemandeService;

import jakarta.servlet.http.HttpSession;

import com.example.BiblioFarany.service.EmpruntService;
import com.example.BiblioFarany.service.ExemplaireService;
import com.example.BiblioFarany.service.LivreService;

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
    @Autowired
    private LivreService livreService;
    @PostMapping("/emprunter")
    public String demanderEmprunterLivre(@RequestParam("livreId") Long idLivre,
            @RequestParam("typeDemandeId") Integer idTypeDemande, Model model, HttpSession session) {
        Adherent adherent = (Adherent) session.getAttribute("adherent");
        boolean aDesPenoActives = penalisationService.aDesPenalisationsActives(adherent);
        int nombreExemplairesRestantsPourEmprunt = empruntService.getNombreExemplairesRestantsPourEmprunt(adherent);
        int nombreExemplaireDispoPourLeLivre = exemplaireService.nombreExemplairesDisponibles(idLivre);
        Livre livre = livreService.getById(idLivre);
        int ageMin = livre.getRestrictionAge();
        if (adherent.isActive() && !aDesPenoActives && nombreExemplairesRestantsPourEmprunt > 0
                && nombreExemplaireDispoPourLeLivre > 0 && adherent.getAge() >= ageMin) {
            // empruntService.creerEmprunt(adherent,
            // exemplaireService.getExemplaireById(idLivre));
            TypeDemande typeDemande = typeDemandeService.findById(idTypeDemande);
            Exemplaire exemplaire = exemplaireService.getExemplaireDisponibleByLivreId(idLivre);
            Demande saved = demandeService.saveDemande(typeDemande, adherent, exemplaire, LocalDate.now(),
                    LocalDate.now(),false);
            model.addAttribute("demande", saved);
            model.addAttribute("adherent", adherent);
            model.addAttribute("exemplaire", exemplaire);
            model.addAttribute("typeDemande", typeDemande);
            return "redirect:/demande-emprunt";
        }
        return "redirect:/liste-livre";
    }

    @GetMapping("/demande-emprunt")
    public String afficherDemandesEmprunt(Model model, HttpSession session) {
        Adherent adherent = (Adherent) session.getAttribute("adherent");

        // Récupérer les demandes ou emprunts liés à l'adhérent
        List<Demande> demandes = demandeService.findDemandesNonValideesByAdherent(adherent);

        // Envoyer à la vue
        model.addAttribute("demandes", demandes);
        System.out.println("Nombre de demandes trouvées : " + demandes.size());
        for (Demande d : demandes) {
            System.out.println("Demande ID: " + d.getId() + ", Livre: " + d.getExemplaire().getLivre().getTitre());
        }

        return "demande-emprunt";
    }

    @GetMapping("liste-emprunt")
    public String afficherListeEmprunt(Model model, HttpSession session) {
        List<Emprunt> emprunts = empruntService.getAllEmprunt();
        model.addAttribute("emprunts", emprunts);
        return "liste-emprunt";
    }

    @GetMapping("ajout-emprunt")
    public String ajoutEmprunt(Model model, HttpSession session) {
        model.addAttribute("adherents", adherentService.getAllAdherents());
        model.addAttribute("exemplaires", exemplaireService.getAll());
        return "ajout-emprunt";
    }

    @PostMapping("ajout-emprunt")
    public String ajoutEmprunt(@RequestParam("adherentId") Integer idAdherent, @RequestParam("exemplaireId") Integer idExemplaire, 
    @RequestParam("dateEmprunt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmprunt,
    @RequestParam("dateRetourPrevue") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetourPrevue){
        Adherent adherent = adherentService.findById(idAdherent);
        Exemplaire exemplaire = exemplaireService.findById(idExemplaire.longValue());
        boolean aDesPenoActives = penalisationService.aDesPenalisationsActives(adherent);
        int nombreExemplairesRestantsPourEmprunt = empruntService.getNombreExemplairesRestantsPourEmprunt(adherent);
        int nombreExemplaireDispoPourLeLivre = exemplaireService.nombreExemplairesDisponibles(Long.valueOf(exemplaire.getLivre().getId()));
        int ageMin = exemplaire.getLivre().getRestrictionAge();
        if(adherent.isActive() && !aDesPenoActives && nombreExemplairesRestantsPourEmprunt>0 && nombreExemplaireDispoPourLeLivre>0 && adherent.getAge()>=ageMin) {
            Emprunt emprunt = new Emprunt(adherent,exemplaire,
            dateEmprunt,dateRetourPrevue);
            empruntService.save(emprunt);
            return "redirect:/liste-emprunt?message=succès";
        }
        return "redirect:/liste-emprunt?message=erreur";
    }

    @GetMapping("retourner")
public String afficherFormulaireRetour(@RequestParam("empruntId") Integer empruntId, Model model){
    Emprunt emprunt = empruntService.getByIdEmprunt(empruntId);
    model.addAttribute("emprunt", emprunt);
    return "retourner";
}


    @PostMapping("retourner")
    public String retournerExemplaire(@RequestParam("empruntId") Long empruntId, @RequestParam("dateRetourReelle") LocalDate dateRetourReelle){
        empruntService.updateDateRetourReelle(empruntId, dateRetourReelle);
        return "redirect:/liste-emprunt";
    }

}

