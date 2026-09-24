# ChâTop — Spécifications Techniques

## Stack Technique
Pour ce projet, utiliser les versions suivantes (ou des versions plus récentes)[cite: 5] :
* **Front-end :** Angular 20[cite: 5]
* **Back-end :** Java 11 ou 17 avec Spring Boot[cite: 5]
* **Sécurité :** Spring Security avec authentification via JWT[cite: 5]
* **Base de données :** MySQL[cite: 5]
* **Gestion de version :** Code versionné sur un dépôt Git[cite: 5]

> **Attention :** Il ne faut pas modifier le front-end de l'application[cite: 5].

---

## Architecture Java
L'application est développée avec une architecture en couches (Controller / Service / JPA Repository)[cite: 5].

---

## Gestion des Images
* Lors de la création d'une location, une image est requise[cite: 5].
* Elle sera envoyée à l'API pour être stockée sur le serveur[cite: 5].
* L'URL de cette image sera enregistrée en base de données dans la table location[cite: 5].

---

## Sécurisation
* En utilisant Spring Security et JWT, suivre les bonnes pratiques : toutes les routes nécessitent l'authentification (sauf celle de création de compte, du login et de la documentation Swagger)[cite: 5].
* S'assurer que le mot de passe est crypté en base de données[cite: 5].
* S'assurer que les identifiants de la base de données n'apparaissent pas dans le code[cite: 5].
* **Note sur Swagger :** Bien que la documentation OpenAPI (Swagger) soit accessible sans authentification, il faudra s'identifier pour pouvoir tester les appels d'API authentifiées[cite: 5].

---

## Outils
* **Mockoon :** 
  * Cette application permet de simuler les réponses d'un serveur[cite: 5].
  * Il faudra créer toutes les routes présentes dans Mockoon[cite: 5].
  * Pour chaque route, il existe plusieurs réponses à gérer (par exemple une réponse OK avec un statut 200, et une réponse unauthorized avec un statut 401)[cite: 5].
* **Swagger :** 
  * Cette application permet de documenter toutes les routes de l'API en suivant la spécification OpenAPI[cite: 5].