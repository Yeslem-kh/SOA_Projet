CREATE DATABASE IF NOT EXISTS credit_db;
CREATE DATABASE IF NOT EXISTS scoring_db;
CREATE DATABASE IF NOT EXISTS decision_db;
USE credit_db;
INSERT INTO utilisateur(username,password,role,actif)
VALUES ('admin', '$2a$10$4q2/7bGqM8W8XWoNJYbEEuPmZwibi4BHlIRHPHdK93FZL4X1WL5dO', 'ADMIN', 1);

USE scoring_db;
INSERT INTO utilisateur(username,password,role,actif)
VALUES ('admin', '$2a$10$4q2/7bGqM8W8XWoNJYbEEuPmZwibi4BHlIRHPHdK93FZL4X1WL5dO', 'ADMIN', 1);

USE decision_db;
INSERT INTO utilisateur(username,password,role,actif)
VALUES ('admin', '$2a$10$4q2/7bGqM8W8XWoNJYbEEuPmZwibi4BHlIRHPHdK93FZL4X1WL5dO', 'ADMIN', 1);