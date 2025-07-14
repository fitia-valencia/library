package com.example.BiblioFarany.repository;

import com.example.BiblioFarany.model.Penalisation;
import com.example.BiblioFarany.model.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PenalisationRepository extends JpaRepository<Penalisation, Long> {
    List<Penalisation> findByAdherent(Adherent adherent);
    List<Penalisation> findByAdherentId(Integer adherentId);
}
