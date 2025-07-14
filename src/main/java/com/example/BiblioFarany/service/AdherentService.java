package com.example.BiblioFarany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.repository.AdherentRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdherentService {

    @Autowired
    private AdherentRepository adherentRepository;

    public Adherent authentifierAdherent(String matricule, String motDePasse) {
        Adherent adherent = adherentRepository.findByMatricule(matricule);
        if (adherent != null && adherent.getMotDePasse().equals(motDePasse)) {
            return adherent;
        }
        return null;
    }

    public Adherent enregistrerAdherent(Adherent adherent) {
        // Définir la date d'inscription s'il n'y en a pas
        if (adherent.getDateInscription() == null) {
            adherent.setDateInscription(LocalDate.now());
        }
    
        // Étape 1 : définir un matricule temporaire pour que l'enregistrement passe
        adherent.setMatricule("TEMP");
    
        // Étape 2 : sauvegarder pour obtenir l'ID
        Adherent saved = adherentRepository.save(adherent);
    
        // Étape 3 : générer le vrai matricule
        String matricule = genererMatricule(saved);
        saved.setMatricule(matricule);
    
        // Étape 4 : sauvegarder à nouveau avec le bon matricule
        return adherentRepository.save(saved);
    }
    
    public String genererMatricule(Adherent adherent) {
        String annee = String.valueOf(LocalDate.now().getYear());
        String initiales = "";

        if (adherent.getNom() != null && !adherent.getNom().isEmpty()) {
            initiales += adherent.getNom().substring(0, 1).toUpperCase();
        }

        if (adherent.getPrenom() != null && !adherent.getPrenom().isEmpty()) {
            initiales += adherent.getPrenom().substring(0, 1).toUpperCase();
        }

        return "ADH" + annee + initiales + adherent.getId();
    }

    public boolean telephoneExist(String telephone) {
        return adherentRepository.findByTelephone(telephone).isPresent();
    }

    public List<Adherent> getAllAdherents() {
        return adherentRepository.findAll();
    }

    public Adherent findById(Integer id) {
        return adherentRepository.findById(id).orElse(null);
    }

    public Adherent getAdherentById(Integer id) {
        return adherentRepository.findById(id).orElse(null);
    }

    
}