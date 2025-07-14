package com.example.BiblioFarany.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20)
    private String isbn;

    @Column(nullable = false, length = 100)
    private String titre;

    @Column(nullable = false, length = 100)
    private String auteur;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @Column(name = "date_publication")
    private LocalDate datePublication;

    @Column(length = 100)
    private String editeur;

    @Column(name = "restriction_age", nullable = false)
    private Integer restrictionAge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Integer getRestrictionAge() {
        return restrictionAge;
    }

    public void setRestrictionAge(Integer restrictionAge) {
        this.restrictionAge = restrictionAge;
    }
    
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private List<Exemplaire> exemplaires;

    public List<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(List<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    // Getters & Setters
}
