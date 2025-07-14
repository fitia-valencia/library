package com.example.BiblioFarany.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quota")
public class Quota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "type_adherent_id", nullable = false)
    private TypeAdherent typeAdherent;

    @Column(name = "quota_exemplaire_empruntable", nullable = false)
    private Integer quotaExemplaireEmpruntable;

    @Column(name = "quota_emprunt_total", nullable = false)
    private Integer quotaEmpruntTotal;

    @Column(name = "quota_reservation", nullable = false)
    private Integer quotaReservation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeAdherent getTypeAdherent() {
        return typeAdherent;
    }

    public void setTypeAdherent(TypeAdherent typeAdherent) {
        this.typeAdherent = typeAdherent;
    }

    public Integer getQuotaExemplaireEmpruntable() {
        return quotaExemplaireEmpruntable;
    }

    public void setQuotaExemplaireEmpruntable(Integer quotaExemplaireEmpruntable) {
        this.quotaExemplaireEmpruntable = quotaExemplaireEmpruntable;
    }

    public Integer getQuotaEmpruntTotal() {
        return quotaEmpruntTotal;
    }

    public void setQuotaEmpruntTotal(Integer quotaEmpruntTotal) {
        this.quotaEmpruntTotal = quotaEmpruntTotal;
    }

    public Integer getQuotaReservation() {
        return quotaReservation;
    }

    public void setQuotaReservation(Integer quotaReservation) {
        this.quotaReservation = quotaReservation;
    }
    
    // Getters & Setters
}

