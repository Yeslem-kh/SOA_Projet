package com.credit.application.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.credit.application.client.DecisionClient;
import com.credit.application.client.ScoringClient;
import com.credit.application.dto.DecisionDTO;
import com.credit.application.dto.DemandeCreditDTO;
import com.credit.application.entity.DemandeCredit;
import com.credit.application.entity.StatutDemande;
import com.credit.application.repository.DemandeCreditRepository;

@Service
public class DemandeCreditService {

    private final DemandeCreditRepository repository;
    private final ScoringClient scoringClient;
    private final DecisionClient decisionClient;

    public DemandeCreditService(DemandeCreditRepository repository,
                                 ScoringClient scoringClient,
                                 DecisionClient decisionClient) {
        this.repository = repository;
        this.scoringClient = scoringClient;
        this.decisionClient = decisionClient;
    }

    @Transactional
    public DemandeCreditDTO creerDemande(DemandeCreditDTO request) {
        //  Creer et sauvegarder la demande
        DemandeCredit demande = new DemandeCredit();
        demande.setNomClient(request.getNomClient());
        demande.setPrenomClient(request.getPrenomClient());
        demande.setEmail(request.getEmail());
        demande.setAge(request.getAge());
        demande.setRevenuMensuel(request.getRevenuMensuel());
        demande.setMontantDemande(request.getMontantDemande());
        demande.setDureeMois(request.getDureeMois());
        demande.setStatut(StatutDemande.EN_COURS);
        
        demande = repository.save(demande);

        //  AUTOMATIQUE: Appeler le scoring-service
        int score = scoringClient.calculerScore(
            demande.getId(),
            demande.getMontantDemande(),
            demande.getDureeMois(),
            demande.getRevenuMensuel(),
            demande.getAge()
        );
        demande.setScore(score);

        // 3. AUTOMATIQUE: Appeler le decision-service
        DecisionDTO decision = decisionClient.obtenirDecision(demande.getId(), score);
        demande.setDecision(decision.getDecision());
        demande.setMotif(decision.getMotif());

        // 4. Finaliser la demande
        demande.setStatut(StatutDemande.TRAITEE);
        demande.setDateTraitement(LocalDateTime.now());
        repository.save(demande);

        return toDTO(demande);
    }

    @Transactional
    public DemandeCreditDTO traiterDemande(Long id) {
        DemandeCredit demande = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée: " + id));

        if (demande.getStatut() == StatutDemande.TRAITEE) {
            return toDTO(demande);
        }

        demande.setStatut(StatutDemande.EN_COURS);
        repository.save(demande);

        int score = scoringClient.calculerScore(
            demande.getId(),
            demande.getMontantDemande(),
            demande.getDureeMois(),
            demande.getRevenuMensuel(),
            demande.getAge()
        );
        demande.setScore(score);

        DecisionDTO decision = decisionClient.obtenirDecision(demande.getId(), score);
        demande.setDecision(decision.getDecision());
        demande.setMotif(decision.getMotif());

        demande.setStatut(StatutDemande.TRAITEE);
        demande.setDateTraitement(LocalDateTime.now());
        repository.save(demande);

        return toDTO(demande);
    }

    public DemandeCreditDTO getDemande(Long id) {
        DemandeCredit demande = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Demande non trouvée: " + id));
        return toDTO(demande);
    }

    public List<DemandeCreditDTO> getAllDemandes() {
        return repository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public List<DemandeCreditDTO> getDemandesParStatut(StatutDemande statut) {
        return repository.findByStatut(statut).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    private DemandeCreditDTO toDTO(DemandeCredit demande) {
        DemandeCreditDTO dto = new DemandeCreditDTO();
        dto.setId(demande.getId());
        dto.setNomClient(demande.getNomClient());
        dto.setPrenomClient(demande.getPrenomClient());
        dto.setEmail(demande.getEmail());
        dto.setAge(demande.getAge());
        dto.setRevenuMensuel(demande.getRevenuMensuel());
        dto.setMontantDemande(demande.getMontantDemande());
        dto.setDureeMois(demande.getDureeMois());
        dto.setScore(demande.getScore());
        dto.setDecision(demande.getDecision());
        dto.setMotif(demande.getMotif());
        dto.setStatut(demande.getStatut().name());
        return dto;
    }
}
