package com.credit.decision.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credit.decision.dto.DecisionDTO;
import com.credit.decision.service.DecisionService;

@RestController
@RequestMapping("/api/decision")
public class DecisionController {

    private final DecisionService decisionService;

    public DecisionController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @PostMapping("/evaluer")
    public ResponseEntity<DecisionDTO> evaluer(@RequestBody DecisionDTO request) {
        DecisionDTO response = decisionService.evaluer(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Decision Service OK");
    }
}
