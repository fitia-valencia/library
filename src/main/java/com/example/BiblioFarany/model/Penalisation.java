package com.example.BiblioFarany.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "penalisation")
public class Penalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "adherent_id", nullable = false)
    private Adherent adherent;

    @Column(name = "date_penalisation", nullable = false)
    private LocalDate datePenalisation;

    @Column(name = "duree_penalisation", nullable = false)
    private Integer dureePenalisation;

    @Column(nullable = false, length = 255)
    private String motif;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public void setAdherent(Adherent adherent) {
        this.adherent = adherent;
    }

    public LocalDate getDatePenalisation() {
        return datePenalisation;
    }

    public void setDatePenalisation(LocalDate datePenalisation) {
        this.datePenalisation = datePenalisation;
    }

    public Integer getDureePenalisation() {
        return dureePenalisation;
    }

    public void setDureePenalisation(Integer dureePenalisation) {
        this.dureePenalisation = dureePenalisation;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }
    
    // Getters & Setters
}

