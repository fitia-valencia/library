package com.example.BiblioFarany.repository;

import org.springframework.stereotype.Repository;

import com.example.BiblioFarany.model.Prolongement;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProlongementRepository extends JpaRepository<Prolongement, Long>{
    
}
