package com.example.BiblioFarany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "type_demande")
public class TypeDemande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    // --- Getters & Setters ---

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
}
