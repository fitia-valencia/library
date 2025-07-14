package com.example.BiblioFarany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BiblioFarany.model.Quota;
import com.example.BiblioFarany.model.TypeAdherent;

@Repository
public interface QuotaRepository extends JpaRepository<Quota, Long> {
    
    Quota findByTypeAdherent(TypeAdherent typeAdherent);
    // Autres méthodes spécifiques si nécessaire
}
