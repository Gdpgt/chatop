CREATE DATABASE IF NOT EXISTS `chatop_db`;
USE chatop_db;

-- REMPLACER ${MYSQL_PASSWORD} par le mot de passe souhaité entre guillemets simples 
-- (lui-même stocké dans le fichier .env non versionné, ou une variable d'environnement)
CREATE USER IF NOT EXISTS 'chatop'@'localhost' IDENTIFIED BY ${MYSQL_PASSWORD};

GRANT SELECT, INSERT, UPDATE, DELETE ON chatop_db.* TO 'chatop'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE IF NOT EXISTS `users` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255),
  `name` varchar(255),
  `password` varchar(255),
  `created_at` timestamp,
  `updated_at` timestamp,
  UNIQUE KEY `users_index` (`email`)
);

CREATE TABLE IF NOT EXISTS `rentals` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255),
  `surface` numeric,
  `price` numeric,
  `picture` varchar(255),
  `description` varchar(2000),
  `owner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

CREATE TABLE IF NOT EXISTS `messages` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `rental_id` integer,
  `user_id` integer,
  `message` varchar(2000),
  `created_at` timestamp,
  `updated_at` timestamp
);

ALTER TABLE `rentals` ADD FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`);

ALTER TABLE `messages` ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

ALTER TABLE `messages` ADD FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`);
