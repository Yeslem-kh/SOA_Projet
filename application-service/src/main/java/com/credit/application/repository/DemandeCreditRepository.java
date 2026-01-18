package com.credit.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.application.entity.DemandeCredit;
import com.credit.application.entity.StatutDemande;

public interface DemandeCreditRepository extends JpaRepository<DemandeCredit, Long> {
    
    List<DemandeCredit> findByStatut(StatutDemande statut);
    
    List<DemandeCredit> findByEmail(String email);
}
