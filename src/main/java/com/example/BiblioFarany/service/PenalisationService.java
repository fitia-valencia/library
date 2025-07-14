package com.example.BiblioFarany.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Penalisation;
import com.example.BiblioFarany.repository.PenalisationRepository;

@Service
public class PenalisationService {
    @Autowired
    private PenalisationRepository penalisationRepository;
    
    public List<Penalisation> getPenalitesParAdherent(Adherent adherent) {
        return penalisationRepository.findByAdherent(adherent);
    }
    public boolean aDesPenalisationsActives(Adherent adherent) {
    List<Penalisation> penalites = penalisationRepository.findByAdherent(adherent);
    LocalDate now = LocalDate.now();

    for (Penalisation p : penalites) {
        if (p.getDatePenalisation().plusDays(p.getDureePenalisation()).isAfter(now)) {
            return true;
        }
    }
    return false;
}

}
