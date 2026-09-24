# Exercice - Prenez en main le projet et définissez l'interface front-back avec une API

## Contexte
Développement du back-end Java / Spring Boot pour **ChâTop**, une plateforme de mise en relation de locataires et propriétaires. 
Le front-end Angular existe déjà et tourne actuellement avec des données fictives fournies par Mockoon.

## Ressources du projet
* **Repository GitHub (starter code Angular) :** [Repository GitHub](https://github.com/OpenClassrooms-Student-Center/Mod-lisez-et-impl-mentez-le-back-end-en-utilisant-du-code-Java-maintenable)
* **Spécifications techniques :** [Spécifications techniques ChaTop (PDF)](https://course.oc-static.com/projects/4079_Mod%C3%A9lisez+et+impl%C3%A9mentez+le+back-end+en+utilisant+du+code+Java+maintenable/Spe%CC%81cifications+techniques+-+ChaTop.pdf)
* **Modèle de définition d'API :** [Modèle Définition d'API (.odt)](https://course.oc-static.com/projects/4079_Mod%C3%A9lisez+et+impl%C3%A9mentez+le+back-end+en+utilisant+du+code+Java+maintenable/Mode%CC%80le+-+De%CC%81finition+d%E2%80%99API.odt)
* **Fiche d'auto-évaluation :** [Fiche d'auto-évaluation (PDF)](https://course.oc-static.com/projects/4079_Mod%C3%A9lisez+et+impl%C3%A9mentez+le+back-end+en+utilisant+du+code+Java+maintenable/P3+FSJA+-+Fiche+d'auto-e%CC%81valuation.pdf)

## Objectifs
1. Analyser les données et les fonctionnalités existantes sur le front.
2. Définir les routes de l’API REST à développer selon le modèle fourni.
3. Modéliser les données à échanger avec le front-end.
4. Mettre en place la base de données MySQL locale.

---

## Étapes de réalisation

### Étape 1 – Installation de l’environnement & Découverte

#### Prérequis
* Java, Node.js et Angular CLI (v20) installés.
* Prise de connaissance des spécifications techniques.

#### Instructions
* Cloner le repository GitHub et lancer l'application Angular selon le `README.md`.
* Installer Mockoon, importer l'environnement Mockoon du projet (`File > Open environment`) et démarrer le serveur mock.
* Interdire toute modification du code front-end Angular (seul le back-end Spring Boot sera développé).

---

### Étape 2 – Analyse de l’API Mockoon

#### Livrable attendu
Une spécification claire de tous les endpoints à implémenter :
* URLs
* Verbes HTTP (GET, POST, etc.)
* Paramètres de requête
* Formats JSON (requêtes et réponses)
* Codes de statut HTTP (200, 400, 500)

#### Consignes de conception
* Recenser toutes les routes depuis Mockoon.
* Déduire les entités métiers à manipuler (ex: `User`, `Rental`).
* Identifier les dépendances Spring Boot nécessaires.
* Prendre en compte les réponses JSON spécifiques selon chaque code d'erreur HTTP généré par Mockoon.

---

### Étape 3 – Initialisation de la base de données MySQL

#### Configuration BDD
* Créer une base de données MySQL nommée `chatop_db` (Bases NoSQL interdites).
* Appliquer le schéma de BDD fourni dans le repository GitHub.
* Créer un utilisateur MySQL dédié avec des permissions limitées (ne pas utiliser le compte `root`).

#### Sécurité
* Masquer les identifiants d'accès BDD via des variables d'environnement (fichier `.env` ou `application.properties`).
* **Interdiction stricte de commiter des mots de passe en clair dans le code.**