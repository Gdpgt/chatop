# Avancement — ChâTop (OC P3)

> L'agent ne coche une case **que sur déclaration de l'apprenant** (ou sur
> proposition confirmée). Ce fichier est distinct de la fiche d'auto-évaluation
> (`docs/oc/Fiches_auto-evaluation.md`), que l'agent ne remplit jamais.

**Étape en cours** : Exercice 1 — installation de l'environnement.

## Exercice 1 — Définir l'interface front-back avec une API

- [ ] Environnement installé (Java, Node, Angular CLI 20) et front lancé [BRIEF]
- [ ] Mockoon installé, environnement du projet démarré, front fonctionnel dessus [BRIEF]
- [ ] Spécification des endpoints rédigée selon le modèle (URL, verbe, params, JSON, codes) [BRIEF]
- [ ] Entités métier et dépendances Spring Boot identifiées [BRIEF]
- [ ] Base MySQL `chatop_db` créée avec `ressources/sql/script.sql`, utilisateur dédié non-root [BRIEF]
- [ ] Credentials BDD hors du code [SPECS]

## Exercice 2 — Implémenter l'architecture et le code back-end

- [ ] `/register` et `/login` avec JWT, Spring Security (publiques : register, login, Swagger) [BRIEF]
- [ ] Mots de passe chiffrés en base (BCrypt) [SPECS]
- [ ] Couches Entity / Repository / Service / Controller, DTO, handler d'exceptions global [BRIEF]
- [ ] Toutes les routes Mockoon implémentées et validées avec le front [SPECS]
- [ ] Image de location : upload, stockage serveur, URL en base [SPECS]
- [ ] Swagger : chaque route documentée, testable avec un Bearer token [BRIEF]
- [ ] Nettoyage : code mort, `System.out`, méthodes publiques des controllers commentées [fiche]
- [ ] README Spring Boot (installation, BDD, URL Swagger) [BRIEF]
- [ ] Branche de release [BRIEF]
