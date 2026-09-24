# Exercice - Implémentez l'architecture et le code back-end

## Contexte
Implémentation du back-end Java avec **Spring Boot** pour l'application **ChâTop**, en respectant l'architecture en couches, l'authentification JWT, la documentation Swagger/OpenAPI et les principes de code propre (SOLID).

## Ressources du projet
* **Spécifications techniques :** [Spécifications techniques ChaTop (PDF)](https://course.oc-static.com/projects/4079_Mod%C3%A9lisez+et+impl%C3%A9mentez+le+back-end+en+utilisant+du+code+Java+maintenable/Spe%CC%81cifications+techniques+-+ChaTop.pdf)
* **Fiche d'auto-évaluation :** [Fiche d'auto-évaluation (PDF)](https://course.oc-static.com/projects/4079_Mod%C3%A9lisez+et+impl%C3%A9mentez+le+back-end+en+utilisant+du+code+Java+maintenable/P3+FSJA+-+Fiche+d'auto-e%CC%81valuation.pdf)
* **Guides Baeldung utiles :**
  * [Conversion Entity <-> DTO dans Spring REST](https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application)
  * [Gestion globale des erreurs REST (`@ControllerAdvice`)](https://www.baeldung.com/exception-handling-for-rest-with-spring)
  * [Configuration JWT + Swagger UI](https://www.baeldung.com/spring-boot-swagger-jwt)

---

## Étapes de réalisation

### Étape 1 – Auth & Sécurité (`/register` et `/login`)

#### Prérequis
* API modélisée et BDD MySQL créée (Étape précédente).
* Liste des endpoints issue de l'analyse Mockoon.

#### Exigences techniques
* Implémenter les routes d'authentification : `/register` et `/login`.
* Générer et retourner un **token JWT** valide lors d'une connexion ou inscription réussie.
* Configurer **Spring Security** :
  * **Routes publiques :** `/register`, `/login`, et les URLs de documentation Swagger/OpenAPI.
  * **Routes sécurisées :** Toutes les autres routes nécessitent un token JWT valide.

#### Consignes d'implémentation
* Respecter l'architecture en couches : `Entity` -> `Repository` (Spring Data JPA) -> `Service` -> `Controller`.
* **Sécurité :** Chiffrer les mots de passe en base (BCrypt).
* **Variables d'environnement :** Masquer les secrets/credentials (variables d'environnement, pas de valeurs en clair dans `application.properties`).
* **Gestion des erreurs :** Mettre en place un handler d'exceptions global (`@ExceptionHandler`).
* **DTOs :** Ne pas exposer directement les entités JPA dans les controllers ; utiliser des DTOs.
* Valider le fonctionnement avec le front Angular.

---

### Étape 2 – Implémentation globale des routes REST

#### Objectif
Implémenter l'ensemble des endpoints métier identifiés sur Mockoon (ex: `/rentals`, `/messages`, `/user`).

#### Exigences techniques
* Sécuriser l'ensemble de ces routes via le filtre JWT.
* Appliquer le pattern : `Entity` / `DTO` / `Repository` / `Service` / `Controller`.
* Supporter les verbes HTTP appropriés (`GET`, `POST`, `PUT`, `DELETE`).
* Respecter les conventions REST et retourner les codes de statut HTTP adéquats (`200 OK`, `201 Created`, `400 Bad Request`, `401 Unauthorized`, `404 Not Found`).
* Vérifier le formatage précis des payloads JSON (params, body, query).
* **Interdiction stricte de modifier le code front-end Angular.**

---

### Étape 3 – Documentation Swagger, nettoyage & Finalisation

#### Documentation & Code Clean
* Documenter l'ensemble des endpoints avec des annotations OpenAPI / Swagger.
* Vérifier que l'interface Swagger UI est accessible et permet de tester les routes avec authentification Bearer Token.
* Nettoyer le code : formater l'indentation, supprimer le code mort, retirer les `System.out.println` ou logs inutiles.

#### Livrable & Git
* Rédiger un fichier `README.md` complet à la racine du projet :
  * Instructions d'installation et d'exécution du projet Spring Boot.
  * Configuration requise pour la base de données.
  * URL d'accès à la documentation Swagger UI.
* Créer une branche de release propre sur Git.