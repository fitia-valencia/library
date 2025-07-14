package com.example.BiblioFarany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "prolongement")
public class Prolongement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "emprunt_id", nullable = false)
    private Emprunt emprunt;

    @Column(name = "nbr_jours_dmd", nullable = false)
    private Integer nbrJoursDmd;

    // --- Getters & Setters ---

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Emprunt getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

    public Integer getNbrJoursDmd() {
        return nbrJoursDmd;
    }

    public void setNbrJoursDmd(Integer nbrJoursDmd) {
        this.nbrJoursDmd = nbrJoursDmd;
    }
}
