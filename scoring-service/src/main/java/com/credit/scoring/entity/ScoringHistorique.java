package com.credit.scoring.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "scoring_historique")
public class ScoringHistorique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_id")
    private Long clientId;

    private Double montant;

    private Integer duree;

    @Column(name = "revenu_mensuel")
    private Double revenuMensuel;

    private Integer age;

    private Integer score;

    private String details;

    @Column(name = "date_calcul")
    private LocalDateTime dateCalcul;

    public ScoringHistorique() {
        this.dateCalcul = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Double getRevenuMensuel() {
        return revenuMensuel;
    }

    public void setRevenuMensuel(Double revenuMensuel) {
        this.revenuMensuel = revenuMensuel;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public LocalDateTime getDateCalcul() {
        return dateCalcul;
    }

    public void setDateCalcul(LocalDateTime dateCalcul) {
        this.dateCalcul = dateCalcul;
    }
}
