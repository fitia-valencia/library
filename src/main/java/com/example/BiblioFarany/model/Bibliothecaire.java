package com.example.BiblioFarany.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Bibliothecaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String matricule;

    @Column(nullable = false, unique = true, length = 100)
    private String nom;

    @Column(nullable = false, length = 255)
    private String adresse;

    @Column(nullable = false, unique = true, length = 15)
    private String telephone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "mot_de_passe", nullable = false, length = 100)
    private String motDePasse;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }
}

