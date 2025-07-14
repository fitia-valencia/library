package com.example.BiblioFarany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BiblioFarany.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    
} 