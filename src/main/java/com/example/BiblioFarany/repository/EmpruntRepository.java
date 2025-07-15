package com.example.BiblioFarany.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Emprunt;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    List<Emprunt> findByAdherentId(Integer adherentId);

    @Query("SELECT COUNT(e) FROM Emprunt e WHERE e.adherent = :adherent AND e.dateRetourReelle IS NULL")
    int countEmpruntsEnCoursByAdherent(Adherent adherent);

    List<Emprunt> findByAdherentAndDateRetourReelleIsNull(Adherent adherent);

    List<Emprunt> findByDateRetourReelleIsNull();

    Emprunt findById(Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Emprunt e SET e.dateRetourReelle = :date WHERE e.id = :id")
    void updateDateRetourReelle(Long id, LocalDate date);
}
