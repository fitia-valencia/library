package com.example.BiblioFarany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.BiblioFarany.model.Exemplaire;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Long> {
    List<Exemplaire> findByLivreId(Long livreId);
    List<Exemplaire> findByDisponibilite(boolean disponibilite);
    List<Exemplaire> findByLivreIdAndDisponibilite(Long livreId, boolean disponibilite);
    @Query("SELECT COUNT(e) FROM Exemplaire e WHERE e.livre.id = :livreId AND e.disponibilite = true")
    int countDisponibleByLivreId(Integer livreId);
    Exemplaire findFirstByLivreIdAndDisponibiliteTrue(Integer livreId);

}
