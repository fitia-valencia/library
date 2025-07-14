package com.example.BiblioFarany.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BiblioFarany.model.Adherent;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Long>{
    Adherent findByMatricule(String matricule);
    Optional<Adherent> findByTelephone(String telephone);
    Optional<Adherent> findById(Integer id);
}