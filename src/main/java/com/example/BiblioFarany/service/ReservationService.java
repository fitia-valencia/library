package com.example.BiblioFarany.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BiblioFarany.model.Adherent;
import com.example.BiblioFarany.model.Exemplaire;
import com.example.BiblioFarany.model.Reservation;
import com.example.BiblioFarany.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationsParAdherent(int adherentId) {
        return reservationRepository.findByAdherentId(adherentId);
    }

    public void creerReservation(Adherent adherent, Exemplaire exemplaire){
        Reservation reservation = new Reservation();
        reservation.setAdherent(adherent);
        reservation.setExemplaire(exemplaire);
        reservation.setDateReservation(LocalDate.now());
        reservationRepository.save(reservation);
    }
}
