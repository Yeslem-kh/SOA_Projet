package com.credit.scoring.service;

import org.springframework.stereotype.Service;

import com.credit.scoring.entity.ScoringHistorique;
import com.credit.scoring.repository.ScoringHistoriqueRepository;

@Service
public class ScoringService {

    private final ScoringHistoriqueRepository repository;

    public ScoringService(ScoringHistoriqueRepository repository) {
        this.repository = repository;
    }

    public int calculerScore(long clientId, double montant, int duree, double revenuMensuel, int age) {
        int score = 100;
        StringBuilder details = new StringBuilder();

        // Règle 1: Ratio endettement (mensualité / revenu)
        double mensualite = montant / duree;
        double ratioEndettement = mensualite / revenuMensuel;
        
        if (ratioEndettement > 0.5) {
            score -= 40;
            details.append("Ratio endettement trop élevé (-40). ");
        } else if (ratioEndettement > 0.33) {
            score -= 20;
            details.append("Ratio endettement moyen (-20). ");
        } else {
            details.append("Bon ratio endettement. ");
        }

        // Règle 2: Age
        if (age < 25) {
            score -= 15;
            details.append("Age < 25 ans (-15). ");
        } else if (age > 60) {
            score -= 10;
            details.append("Age > 60 ans (-10). ");
        } else {
            score += 10;
            details.append("Age optimal (+10). ");
        }

        // Règle 3: Montant demandé
        if (montant > 50000) {
            score -= 20;
            details.append("Montant élevé (-20). ");
        } else if (montant > 20000) {
            score -= 10;
            details.append("Montant moyen (-10). ");
        }

        // Règle 4: Durée
        if (duree > 60) {
            score -= 15;
            details.append("Durée longue (-15). ");
        } else if (duree < 12) {
            score += 5;
            details.append("Durée courte (+5). ");
        }

        // Score minimum 0, maximum 100
        score = Math.max(0, Math.min(100, score));

        // Sauvegarder l'historique
        ScoringHistorique historique = new ScoringHistorique();
        historique.setClientId(clientId);
        historique.setMontant(montant);
        historique.setDuree(duree);
        historique.setRevenuMensuel(revenuMensuel);
        historique.setAge(age);
        historique.setScore(score);
        historique.setDetails(details.toString());
        repository.save(historique);

        return score;
    }

    public String getDetails(long clientId, double montant, int duree, double revenuMensuel, int age) {
        StringBuilder details = new StringBuilder();
        double mensualite = montant / duree;
        double ratioEndettement = mensualite / revenuMensuel;

        details.append("Analyse pour client ").append(clientId).append(": ");
        details.append("Mensualité=").append(String.format("%.2f", mensualite));
        details.append(", Ratio=").append(String.format("%.2f%%", ratioEndettement * 100));

        return details.toString();
    }
}
