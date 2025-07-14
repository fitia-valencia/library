package com.example.BiblioFarany.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.BiblioFarany.model.Livre;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    // Tu peux ajouter des méthodes personnalisées ici plus tard si besoin
    Optional<Livre> findById(Long id);
    List<Livre> findByGenreId(Integer genreId);
    List<Livre> findByRestrictionAgeLessThanEqual(Integer ageMin);
    List<Livre> findByGenreIdAndRestrictionAgeLessThanEqual(Integer genreId, Integer ageMin);

}
