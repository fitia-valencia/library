package com.example.BiblioFarany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_adherent")
public class TypeAdherent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String libelle;

    @Column(name = "duree_abonnement", nullable = false)
    private Integer dureeAbonnement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getDureeAbonnement() {
        return dureeAbonnement;
    }

    public void setDureeAbonnement(Integer dureeAbonnement) {
        this.dureeAbonnement = dureeAbonnement;
    }
    
    // Getters & Setters
}
