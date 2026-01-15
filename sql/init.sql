-- ============================================
-- Script SQL pour la Plateforme de Crédit
-- ============================================

-- Création des bases de données
CREATE DATABASE IF NOT EXISTS credit_db;
CREATE DATABASE IF NOT EXISTS scoring_db;
CREATE DATABASE IF NOT EXISTS decision_db;

-- ============================================
-- BASE DE DONNÉES: credit_db (application-service)
-- ============================================
USE credit_db;

-- Table des utilisateurs
CREATE TABLE IF NOT EXISTS utilisateur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    actif BOOLEAN DEFAULT TRUE
);

-- Table des demandes de crédit
CREATE TABLE IF NOT EXISTS demande_credit (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom_client VARCHAR(100),
    prenom_client VARCHAR(100),
    email VARCHAR(150),
    age INT,
    revenu_mensuel DOUBLE,
    montant_demande DOUBLE,
    duree_mois INT,
    score INT,
    decision VARCHAR(20),
    motif VARCHAR(255),
    statut VARCHAR(20),
    date_creation DATETIME,
    date_traitement DATETIME
);

-- Insertion des utilisateurs (mot de passe: admin123)
-- Le hash BCrypt pour "admin123" est: $2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K
INSERT INTO utilisateur (username, password, role, actif) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'ADMIN', true),
('user1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'USER', true);

-- ============================================
-- BASE DE DONNÉES: scoring_db (scoring-service)
-- ============================================
USE scoring_db;

-- Table des utilisateurs
CREATE TABLE IF NOT EXISTS utilisateur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    actif BOOLEAN DEFAULT TRUE
);

-- Table historique scoring
CREATE TABLE IF NOT EXISTS scoring_historique (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT,
    montant DOUBLE,
    duree INT,
    revenu_mensuel DOUBLE,
    age INT,
    score INT,
    details TEXT,
    date_calcul DATETIME
);

-- Insertion des utilisateurs (mot de passe: admin123)
INSERT INTO utilisateur (username, password, role, actif) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'ADMIN', true),
('user1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'USER', true);

-- ============================================
-- BASE DE DONNÉES: decision_db (decision-service)
-- ============================================
USE decision_db;

-- Table des utilisateurs
CREATE TABLE IF NOT EXISTS utilisateur (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) DEFAULT 'USER',
    actif BOOLEAN DEFAULT TRUE
);

-- Table historique décisions
CREATE TABLE IF NOT EXISTS decision_historique (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    demande_id BIGINT,
    score INT,
    decision VARCHAR(20),
    motif VARCHAR(255),
    date_decision DATETIME
);

-- Insertion des utilisateurs (mot de passe: admin123)
INSERT INTO utilisateur (username, password, role, actif) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'ADMIN', true),
('user1', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'USER', true);

-- ============================================
-- Vérification
-- ============================================
SELECT 'credit_db' as db, COUNT(*) as users FROM credit_db.utilisateur
UNION ALL
SELECT 'scoring_db', COUNT(*) FROM scoring_db.utilisateur
UNION ALL
SELECT 'decision_db', COUNT(*) FROM decision_db.utilisateur;
