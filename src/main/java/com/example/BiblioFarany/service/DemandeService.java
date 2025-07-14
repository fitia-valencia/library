package com.example.BiblioFarany.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Demande;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.TypeDemande;
import com.example.BiblioFarany.repository.DemandeRepository;
// import com.example.BiblioFarany.repository.ExemplaireRepository;
// import com.example.BiblioFarany.repository.LivreRepository;

@Service
public class DemandeService {
    @Autowired
    private DemandeRepository demandeRepository;
    
    public Demande saveDemande(TypeDemande typeDemande, Adherent adherent, Exemplaire exemplaire,LocalDate dateDemande, LocalDate dateEnvoiDemande) {
        Demande demande = new Demande();
        demande.setTypeDemande(typeDemande);
        demande.setAdherent(adherent);
        demande.setExemplaire(exemplaire);
        demande.setDateDemande(dateDemande);
        demande.setDateEnvoiDemande(dateEnvoiDemande);
        return demandeRepository.save(demande);
        
    }
    public List<Demande> findByAdherent(Adherent adherent) {
        return demandeRepository.findByAdherent(adherent);
    }
    public List<Demande> getAllDemande(){
        return demandeRepository.findAll();
    }
    
}
