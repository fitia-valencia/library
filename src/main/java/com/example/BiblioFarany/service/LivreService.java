package com.example.BiblioFarany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Livre;
import com.example.BiblioFarany.repository.LivreRepository;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }
    public void ajouterLivre(Livre livre) {
        livreRepository.save(livre);
    }
    public List<Livre> getLivresFiltres(Integer genreId, Integer ageMin) {
        if (genreId != null && ageMin != null) {
            return livreRepository.findByGenreIdAndRestrictionAgeLessThanEqual(genreId, ageMin);
        } else if (genreId != null) {
            return livreRepository.findByGenreId(genreId);
        } else if (ageMin != null) {
            return livreRepository.findByRestrictionAgeLessThanEqual(ageMin);
        } else {
            return livreRepository.findAll();
        }
    }
    public Livre getById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }
        
}
