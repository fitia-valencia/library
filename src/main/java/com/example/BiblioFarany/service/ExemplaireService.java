package com.example.BiblioFarany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.Livre;
import com.example.BiblioFarany.repository.ExemplaireRepository;
import com.example.BiblioFarany.repository.LivreRepository;

@Service
public class ExemplaireService {

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private LivreRepository livreRepository;

    public List<Exemplaire> getAll() {
        return exemplaireRepository.findAll();
    }

    public Exemplaire ajouterExemplaire(Exemplaire exemplaire, Integer livreId) {
        Livre livre = livreRepository.findById(Long.valueOf(livreId)).orElse(null);
    
        if (livre == null) {
            throw new RuntimeException("Livre introuvable");
        }
    
        exemplaire.setLivre(livre);
    
        // 🔽 Sauvegarder une première fois pour obtenir l'id
        exemplaire.setDisponibilite(true); // par défaut
        exemplaire.setReference("TEMP"); // mettre une valeur temporaire pour éviter l'erreur
        Exemplaire saved = exemplaireRepository.save(exemplaire);
    
        // 🔽 Générer la vraie référence et mettre à jour
        String ref = "EX" + saved.getId() + "-L" + livre.getId();
        saved.setReference(ref);
        return exemplaireRepository.save(saved);
    }
    

    public void ajouterExemplairePourLivre(Integer livreId) {
        Exemplaire exemplaire = new Exemplaire();
        ajouterExemplaire(exemplaire, livreId);
    }

    public List<Exemplaire> filtrerParLivre(Long livreId) {
        return exemplaireRepository.findByLivreId(livreId);
    }
    
    public List<Exemplaire> filtrerParDisponibilite(boolean dispo) {
        return exemplaireRepository.findByDisponibilite(dispo);
    }
    
    public List<Exemplaire> filtrerParLivreEtDisponibilite(Long livreId, boolean dispo) {
        return exemplaireRepository.findByLivreIdAndDisponibilite(livreId, dispo);
    }
    
    public int nombreExemplairesDisponibles(Long livreId) {
        return exemplaireRepository.countDisponibleByLivreId(livreId);
    }

    public Exemplaire getExemplaireDisponibleByLivreId(Long livreId) {
        return exemplaireRepository.findFirstByLivreIdAndDisponibiliteTrue(livreId);
    }
}
