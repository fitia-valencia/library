package com.example.BiblioFarany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Bibliothecaire;
import com.example.BiblioFarany.repository.BibliothecaireRepository;

@Service
public class BibliothecaireService {
    
    @Autowired
    private BibliothecaireRepository bibliothecaireRepository;
    public Bibliothecaire authentifierBibliothecaire(String matricule, String motDePasse){
        Bibliothecaire bibliothecaire = bibliothecaireRepository.findByMatricule(matricule);
        if (bibliothecaire != null && (bibliothecaire.getMotDePasse().equals(motDePasse))) {
            return bibliothecaire;
        }
        return null;
    }
}
