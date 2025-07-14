package com.example.BiblioFarany.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiblioFarany.model.Bibliothecaire;

@Repository
public interface BibliothecaireRepository extends JpaRepository<Bibliothecaire, Long>{
    Bibliothecaire findByMatricule(String matricule);
}