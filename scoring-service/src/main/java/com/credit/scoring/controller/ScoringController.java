package com.credit.scoring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.scoring.dto.ScoringDTO;
import com.credit.scoring.service.ScoringService;

@RestController
@RequestMapping("/api/scoring")
public class ScoringController {

    private final ScoringService scoringService;

    public ScoringController(ScoringService scoringService) {
        this.scoringService = scoringService;
    }

    @PostMapping("/calculer")
    public ResponseEntity<ScoringDTO> calculerScore(@RequestBody ScoringDTO request) {
        int score = scoringService.calculerScore(
            request.getClientId(),
            request.getMontant(),
            request.getDuree(),
            request.getRevenuMensuel(),
            request.getAge()
        );

        String details = scoringService.getDetails(
            request.getClientId(),
            request.getMontant(),
            request.getDuree(),
            request.getRevenuMensuel(),
            request.getAge()
        );

        request.setScore(score);
        request.setDetails(details);

        return ResponseEntity.ok(request);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Scoring Service OK");
    }
}
