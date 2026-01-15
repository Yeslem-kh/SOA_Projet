# Plateforme de Demande de Crédit

Plateforme de demande de crédit basée sur 3 applications Spring Boot distinctes, utilisant exclusivement des API REST pour la communication entre services.

## Architecture

```
┌─────────────────────┐
│   Application       │
│     Service         │
│   (Port 8085)       │
│   Orchestrateur     │
└─────────┬───────────┘
          │
    ┌─────┴─────┐
    │           │
    ▼           ▼
┌─────────┐  ┌─────────┐
│Scoring  │  │Decision │
│Service  │  │Service  │
│(8081)   │  │(8086)   │
│  REST   │  │  REST   │
└─────────┘  └─────────┘
```

## Services

### 1. Scoring Service (Port 8081) - REST API
- **Rôle** : Calculer le score de crédit d'un client
- **Technologie** : REST API avec Spring Web
- **Endpoint** : `POST /api/scoring/calculer`
- **Base de données** : MySQL (scoring_db)

### 2. Decision Service (Port 8086) - REST API
- **Rôle** : Prendre une décision d'approbation/rejet basée sur le score
- **Technologie** : REST API avec Spring Web
- **Endpoint** : `POST /api/decision/evaluer`
- **Base de données** : MySQL (decision_db)

### 3. Application Service (Port 8085) - Orchestrateur REST
- **Rôle** : Orchestrer les demandes de crédit
- **Communication** : REST vers scoring et decision services
- **Endpoint** : `POST /api/demandes`
- **Base de données** : MySQL (credit_db)

## Prérequis

- Java 17+
- Maven 3.8+
- MySQL 8.0+

## Configuration MySQL

Créez les bases de données :

```sql
CREATE DATABASE credit_db;
CREATE DATABASE scoring_db;
CREATE DATABASE decision_db;
```

Créez les utilisateurs dans chaque base :

```sql
-- Pour credit_db
USE credit_db;
INSERT INTO utilisateur (username, password, role) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'ADMIN');

-- Pour scoring_db
USE scoring_db;
INSERT INTO utilisateur (username, password, role) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'ADMIN');

-- Pour decision_db
USE decision_db;
INSERT INTO utilisateur (username, password, role) VALUES 
('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMy.MqrqDz/VJOlVSqTWw8c4tEeQYGMrX7K', 'ADMIN');
```

**Mot de passe** : `admin123` (hashé en BCrypt)

## Compilation

```bash
# Scoring Service
cd scoring-service && mvn clean package -DskipTests && cd ..

# Decision Service
cd decision-service && mvn clean package -DskipTests && cd ..

# Application Service
cd application-service && mvn clean package -DskipTests && cd ..
```

## Démarrage

Démarrez les services dans cet ordre :

```bash
# Terminal 1 - Scoring Service
cd scoring-service && mvn spring-boot:run

# Terminal 2 - Decision Service
cd decision-service && mvn spring-boot:run

# Terminal 3 - Application Service
cd application-service && mvn spring-boot:run
```

## Tests avec cURL

### 1. Health Check
```bash
curl http://localhost:8081/api/scoring/health
curl http://localhost:8085/api/demandes/health
curl http://localhost:8086/api/decision/health
```

### 2. Tester le Scoring Service directement (REST)
```bash
curl -X POST http://localhost:8081/api/scoring/calculer \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "clientId": 1,
    "montant": 15000,
    "duree": 36,
    "revenuMensuel": 4000,
    "age": 35
  }'
```

### 3. Créer une demande de crédit
```bash
curl -X POST http://localhost:8085/api/demandes \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "nomClient": "Dupont",
    "prenomClient": "Jean",
    "email": "jean.dupont@email.com",
    "age": 35,
    "revenuMensuel": 4000,
    "montantDemande": 15000,
    "dureeMois": 36
  }'
```

### 4. Traiter une demande (appelle scoring et decision)
```bash
curl -X POST http://localhost:8085/api/demandes/1/traiter \
  -u admin:admin123
```

### 5. Consulter une demande
```bash
curl http://localhost:8085/api/demandes/1 \
  -u admin:admin123
```

## Algorithme de Scoring

Le score est calculé sur 100 points :
- **Ratio dette/revenu** : jusqu'à 40 points
- **Âge** : jusqu'à 30 points
- **Durée du prêt** : jusqu'à 30 points

Seuil d'approbation : **50 points**

## Authentification

- **Type** : HTTP Basic Authentication
- **Identifiants** : `admin` / `admin123`
- **Stockage** : MySQL avec hash BCrypt

## Technologies

- Spring Boot 3.2.0
- Spring Web (REST API)
- Spring Data JPA
- Spring Security
- MySQL
- Maven
