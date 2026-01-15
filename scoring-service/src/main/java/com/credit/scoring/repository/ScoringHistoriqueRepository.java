package com.credit.scoring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.scoring.entity.ScoringHistorique;

public interface ScoringHistoriqueRepository extends JpaRepository<ScoringHistorique, Long> {
}
