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

@Component
public class ScoringClient {

    @Value("${service.scoring.url}")
    private String scoringUrl;

    @Value("${service.scoring.username}")
    private String username;

    @Value("${service.scoring.password}")
    private String password;

    private final RestTemplate restTemplate;

    public ScoringClient() {
        this.restTemplate = new RestTemplate();
    }

    public int calculerScore(long clientId, double montant, int duree, double revenuMensuel, int age) {
        ScoringDTO request = new ScoringDTO();
        request.setClientId(clientId);
        request.setMontant(montant);
        request.setDuree(duree);
        request.setRevenuMensuel(revenuMensuel);
        request.setAge(age);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String auth = username + ":" + password;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
        headers.set("Authorization", "Basic " + encodedAuth);

        HttpEntity<ScoringDTO> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ScoringDTO> response = restTemplate.exchange(
            scoringUrl + "/calculer",
            HttpMethod.POST,
            entity,
            ScoringDTO.class
        );

        return response.getBody().getScore();
    }

    public static class ScoringDTO {
        private Long clientId;
        private Double montant;
        private Integer duree;
        private Double revenuMensuel;
        private Integer age;
        private Integer score;
        private String details;

        public Long getClientId() { return clientId; }
        public void setClientId(Long clientId) { this.clientId = clientId; }
        public Double getMontant() { return montant; }
        public void setMontant(Double montant) { this.montant = montant; }
        public Integer getDuree() { return duree; }
        public void setDuree(Integer duree) { this.duree = duree; }
        public Double getRevenuMensuel() { return revenuMensuel; }
        public void setRevenuMensuel(Double revenuMensuel) { this.revenuMensuel = revenuMensuel; }
        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
        public Integer getScore() { return score; }
        public void setScore(Integer score) { this.score = score; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }
}
