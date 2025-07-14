package com.example.BiblioFarany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Genre;
import com.example.BiblioFarany.repository.GenreRepository;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    public List<Genre> getAllGenres() {
        // Implémentation pour récupérer tous les genres
        // Par exemple, en utilisant un repository pour accéder à la base de données
        return genreRepository.findAll();
    }
}
