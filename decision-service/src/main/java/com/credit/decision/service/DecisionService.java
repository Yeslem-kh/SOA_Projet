package com.credit.decision.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.credit.decision.dto.DecisionDTO;
import com.credit.decision.entity.DecisionHistorique;
import com.credit.decision.repository.DecisionHistoriqueRepository;

@Service
public class DecisionService {

    private final DecisionHistoriqueRepository repository;
    
    @Value("${credit.seuil.approbation}")
    private int seuilApprobation;

    public DecisionService(DecisionHistoriqueRepository repository) {
        this.repository = repository;
    }

    public DecisionDTO evaluer(DecisionDTO request) {
        String decision;
        String motif;

        if (request.getScore() >= seuilApprobation) {
            decision = "APPROUVE";
            if (request.getScore() >= 80) {
                motif = "Excellent profil - Crédit approuvé sans réserve";
            } else if (request.getScore() >= 65) {
                motif = "Bon profil - Crédit approuvé";
            } else {
                motif = "Profil acceptable - Crédit approuvé avec conditions";
            }
        } else {
            decision = "REJETE";
            if (request.getScore() >= 35) {
                motif = "Score insuffisant - Possibilité de réexamen dans 6 mois";
            } else {
                motif = "Score trop faible - Demande rejetée";
            }
        }

        request.setDecision(decision);
        request.setMotif(motif);

        DecisionHistorique historique = new DecisionHistorique();
        historique.setDemandeId(request.getDemandeId());
        historique.setScore(request.getScore());
        historique.setDecision(decision);
        historique.setMotif(motif);
        repository.save(historique);

        return request;
    }
}
