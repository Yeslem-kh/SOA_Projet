package com.credit.application.client;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.credit.application.dto.DecisionDTO;

@Component
public class DecisionClient {

    @Value("${service.decision.url}")
    private String decisionUrl;

    @Value("${service.decision.username}")
    private String username;

    @Value("${service.decision.password}")
    private String password;

    private final RestTemplate restTemplate;

    public DecisionClient() {
        this.restTemplate = new RestTemplate();
    }

    public DecisionDTO obtenirDecision(Long demandeId, Integer score) {
        DecisionDTO request = new DecisionDTO();
        request.setDemandeId(demandeId);
        request.setScore(score);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<DecisionDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<DecisionDTO> response = restTemplate.exchange(
            decisionUrl + "/evaluer",
            HttpMethod.POST,
            entity,
            DecisionDTO.class
        );

        return response.getBody();
    }
}
