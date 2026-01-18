package com.credit.application.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "demande_credit")
public class DemandeCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_client")
    private String nomClient;

    @Column(name = "prenom_client")
    private String prenomClient;

    private String email;

    private Integer age;

    @Column(name = "revenu_mensuel")
    private Double revenuMensuel;

    @Column(name = "montant_demande")
    private Double montantDemande;

    @Column(name = "duree_mois")
    private Integer dureeMois;

    private Integer score;

    private String decision;

    private String motif;

    @Enumerated(EnumType.STRING)
    private StatutDemande statut;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "date_traitement")
    private LocalDateTime dateTraitement;

    public DemandeCredit() {
        this.dateCreation = LocalDateTime.now();
        this.statut = StatutDemande.EN_ATTENTE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getRevenuMensuel() {
        return revenuMensuel;
    }

    public void setRevenuMensuel(Double revenuMensuel) {
        this.revenuMensuel = revenuMensuel;
    }

    public Double getMontantDemande() {
        return montantDemande;
    }

    public void setMontantDemande(Double montantDemande) {
        this.montantDemande = montantDemande;
    }

    public Integer getDureeMois() {
        return dureeMois;
    }

    public void setDureeMois(Integer dureeMois) {
        this.dureeMois = dureeMois;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public StatutDemande getStatut() {
        return statut;
    }

    public void setStatut(StatutDemande statut) {
        this.statut = statut;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDateTime getDateTraitement() {
        return dateTraitement;
    }

    public void setDateTraitement(LocalDateTime dateTraitement) {
        this.dateTraitement = dateTraitement;
    }
}
