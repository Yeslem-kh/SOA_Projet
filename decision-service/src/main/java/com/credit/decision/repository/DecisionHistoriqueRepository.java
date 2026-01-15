package com.credit.decision.repository;

import com.credit.decision.entity.DecisionHistorique;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DecisionHistoriqueRepository extends JpaRepository<DecisionHistorique, Long> {
}
