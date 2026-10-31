# Avancement — ChâTop (OC P3)

> L'agent ne coche une case **que sur déclaration de l'apprenant** (ou sur
> proposition confirmée). Ce fichier est distinct de la fiche d'auto-évaluation
> (`docs/oc/Fiches_auto-evaluation.md`), que l'agent ne remplit jamais.

**Étape en cours** : Exercice 2, Étape 3 – Documentation Swagger, nettoyage & finalisation.

## Exercice 1 — Définir l'interface front-back avec une API

- [x] Environnement installé (Java, Node, Angular CLI 20) et front lancé [BRIEF]
- [x] Mockoon installé, environnement du projet démarré, front fonctionnel dessus [BRIEF]
- [x] Spécification des endpoints rédigée selon le modèle (URL, verbe, params, JSON, codes) [BRIEF]
- [x] Entités métier et dépendances Spring Boot identifiées [BRIEF]
- [x] Base MySQL `chatop_db` créée avec `ressources/sql/script.sql`, utilisateur dédié non-root [BRIEF]
- [x] Credentials BDD hors du code [SPECS]

## Exercice 2 — Implémenter l'architecture et le code back-end

- [x] `/register` et `/login` avec JWT, Spring Security (publiques : register, login, Swagger) [BRIEF]
- [x] Mots de passe chiffrés en base (BCrypt) [SPECS]
- [x] Couches Entity / Repository / Service / Controller, DTO, handler d'exceptions global [BRIEF]
- [x] Toutes les routes Mockoon implémentées et validées avec le front [SPECS]
- [x] Image de location : upload, stockage serveur, URL en base [SPECS]
- [ ] Swagger : chaque route documentée, testable avec un Bearer token [BRIEF]
- [ ] Nettoyage : code mort, `System.out`, méthodes publiques des controllers commentées [fiche]
- [ ] README Spring Boot (installation, BDD, URL Swagger) [BRIEF]
- [ ] Branche de release [BRIEF]
