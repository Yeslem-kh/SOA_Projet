package com.credit.scoring.dto;

public class ScoringDTO {

    private Long clientId;
    private Double montant;
    private Integer duree;
    private Double revenuMensuel;
    private Integer age;
    private Integer score;
    private String details;

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
}
