package com.example.BiblioFarany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.TypeDemande;
import com.example.BiblioFarany.repository.TypeDemandeRepository;

@Service
public class TypeDemandeService {
    @Autowired
    private TypeDemandeRepository typeDemandeRepository;
    public TypeDemande findById(Integer id) {
        return typeDemandeRepository.findById(id).orElse(null); 
    }
}
