package com.credit.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credit.application.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    
    Optional<Utilisateur> findByUsername(String username);
}
