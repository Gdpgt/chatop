CREATE DATABASE IF NOT EXISTS `chatop_db`;
USE chatop_db;

-- REMPLACER ${MYSQL_PASSWORD} par le mot de passe souhaité entre guillemets simples 
-- (lui-même stocké dans le fichier .env non versionné, ou une variable d'environnement)
CREATE USER IF NOT EXISTS 'chatop'@'localhost' IDENTIFIED BY ${MYSQL_PASSWORD};

GRANT SELECT, INSERT, UPDATE, DELETE ON chatop_db.* TO 'chatop'@'localhost';
FLUSH PRIVILEGES;

-- Chemin relatif : lancer ce script depuis la racine du projet
SOURCE ressources/sql/schema.sql;
