package com.example.BiblioFarany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {
    List<Demande> findByAdherent(Adherent adherent);
    List<Demande> findByValidationFalse();
    List<Demande> findByAdherentAndValidationFalse(Adherent adherent);

}
