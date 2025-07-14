package com.example.BiblioFarany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.repository.ProlongementRepository;

@Service
public class ProlongementService {
    @Autowired
    private ProlongementRepository prolongementRepository;
    
    public void creerProlongement(Adherent adherent, Exemplaire exemplaire){
        
    }
}
