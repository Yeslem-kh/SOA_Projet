package com.credit.application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.application.dto.DemandeCreditDTO;
import com.credit.application.entity.StatutDemande;
import com.credit.application.service.DemandeCreditService;

@RestController
@RequestMapping("/api/demandes")
public class DemandeCreditController {

    private final DemandeCreditService service;

    public DemandeCreditController(DemandeCreditService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DemandeCreditDTO> creerDemande(@RequestBody DemandeCreditDTO request) {
        DemandeCreditDTO response = service.creerDemande(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/traiter")
    public ResponseEntity<DemandeCreditDTO> traiterDemande(@PathVariable Long id) {
        DemandeCreditDTO response = service.traiterDemande(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeCreditDTO> getDemande(@PathVariable Long id) {
        DemandeCreditDTO response = service.getDemande(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DemandeCreditDTO>> getAllDemandes() {
        List<DemandeCreditDTO> demandes = service.getAllDemandes();
        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<DemandeCreditDTO>> getDemandesParStatut(@PathVariable String statut) {
        StatutDemande statutDemande = StatutDemande.valueOf(statut.toUpperCase());
        List<DemandeCreditDTO> demandes = service.getDemandesParStatut(statutDemande);
        return ResponseEntity.ok(demandes);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Application Service OK");
    }
}
