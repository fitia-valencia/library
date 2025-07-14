package com.example.BiblioFarany.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BiblioFarany.model.TypeDemande;

@Repository
public interface TypeDemandeRepository extends JpaRepository<TypeDemande, Integer> {
    
} 