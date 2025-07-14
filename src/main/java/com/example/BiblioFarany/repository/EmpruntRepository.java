package com.example.BiblioFarany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Emprunt;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
    List<Emprunt> findByAdherentId(Integer adherentId);

   @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.adherent = :adherent AND e.dateRetourReelle IS NULL")
    int countEmpruntsEnCoursByAdherent(Adherent adherent);

    List<Emprunt> findByAdherentAndDateRetourReelleIsNull(Adherent adherent);

}
