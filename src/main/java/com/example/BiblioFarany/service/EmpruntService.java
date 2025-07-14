package com.example.BiblioFarany.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Emprunt;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.Quota;
import com.example.BiblioFarany.repository.EmpruntRepository;
import com.example.BiblioFarany.repository.ExemplaireRepository;
import com.example.BiblioFarany.repository.QuotaRepository;

@Service
public class EmpruntService {
    @Autowired
    private EmpruntRepository empruntRepository;
    @Autowired
    private ExemplaireRepository exemplaireRepository;
    @Autowired
    private QuotaRepository quotaRepository;

    public List<Emprunt> getEmpruntsParAdherent(int adherentId) {
        return empruntRepository.findByAdherentId(adherentId);
    }

    public void creerEmprunt(Adherent adherent, Exemplaire exemplaire) {
        Emprunt emprunt = new Emprunt();
        emprunt.setAdherent(adherent);
        emprunt.setExemplaire(exemplaire);
        emprunt.setDateEmprunt(LocalDate.now());
        emprunt.setDateRetourPrevue(LocalDate.now().plusDays(15)); // Par ex : 15 jours

        empruntRepository.save(emprunt);

        exemplaire.setDisponibilite(false);
        exemplaireRepository.save(exemplaire);
    }

    public int getNombreExemplairesRestantsPourEmprunt(Adherent adherent) {
        int empruntsEnCours = empruntRepository.countEmpruntsEnCoursByAdherent(adherent);
        Quota quota = quotaRepository.findByTypeAdherent(adherent.getTypeAdherent());

        if (quota == null) {
            throw new RuntimeException("Quota non défini pour le type d'adhérent : " + adherent.getTypeAdherent().getLibelle());
        }

        return Math.max(0, quota.getQuotaExemplaireEmpruntable() - empruntsEnCours);
    }
}
