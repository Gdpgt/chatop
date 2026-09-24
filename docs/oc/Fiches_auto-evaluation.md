# Fiches d'Auto-évaluation - Projet ChâTop

---

## Exercice 1 - Définissez l'interface front-back avec une API

### Étape 1  Installez l'environnement et découvrez le projet
- [ ] J'ai lancé l'environnement Mockoon qui fonctionne avec le front-end.
- [ ] J'ai pris en main l'application Angular fournie dans l'exercice.
- [ ] J'ai lancé l'application sur mon propre ordinateur.
- [ ] J'ai découvert les fonctionnalités majeures.
- [ ] Je suis capable de décrire le fonctionnement de l'application et son interaction avec le serveur Mockoon.

### Étape 2  Analysez l'API existante dans l'environnement Mockoon
- [ ] J'ai rédigé une liste des endpoints à implémenter en format texte.
- [ ] J'ai suivi le modèle fourni.
- [ ] J'ai déduit les entités métiers à manipuler selon les informations existantes.
- [ ] J'ai inclus toutes les informations des endpoints 
  - [ ] Les URL et paramètres des routes dans l'environnement Mockoon.
  - [ ] Les méthodes, les verbes HTTP et les requêtesréponses JSON.
  - [ ] Les codes d'erreurs pour gérer chaque statut HTML.
- [ ] J'ai vérifié que toutes les routes et leurs informations dans la liste sont identiques à celles de l'environnement Mockoon.

### Étape 3  Initialisez la base de données
- [ ] J'ai créé la base de données localement sur mon propre ordinateur.
- [ ] J'ai suivi le schéma fourni pour les tables et les relations de la BDD.
- [ ] J'ai suivi les bonnes pratiques pour sécuriser les données et l'application 
  - [ ] J'ai crypté le mot de passe en base de données et pas dans le code.
  - [ ] J'ai stocké les credentials dans des variables d'environnement ou dans un fichier (non pushé sur git) et pas dans le code.
  - [ ] J'ai utilisé un utilisateur avec des permissions limitées, pas le compte root.

---

## Exercice 2 - Implémentez l'architecture et le code back-end

### Étape 1 - Implémentez les routes register et login
- [ ] J'ai suivi les bonnes pratiques de sécurisation 
  - [ ] J'ai mis en place Spring Security.
  - [ ] J'ai utilisé JWT pour l'authentification.
  - [ ] Toutes les routes en dehors de celle de création de compte ou de login nécessitent une authentification avec JWT.
  - [ ] La documentation Swagger est accessible sans authentification.
  - [ ] J'ai crypté le mot de passe en base de données.
  - [ ] Je me suis assuré que les credentials de la base de données n'apparaissent pas dans le code.

### Étape 2 - Implémentez toutes les routes du back-end
- [ ] J'ai implémenté toutes les routes présentes dans l'environnement Mockoon et dans ma documentation de l'API de l'exercice 1.
- [ ] J'ai sécurisé toutes les routes (en dehors de celle de login et register) avec un token JWT.
- [ ] J'ai vérifié que le back-end de l'application fonctionne lors de son exécution sans aucune erreur dans la console.
- [ ] J'ai découpé l'application en 3 parties  Controller  Service  JPA Repository.
- [ ] J'ai documenté chaque route avec Swagger.
- [ ] Swagger permet d'interagir avec toutes les routes de l'API (authentifiées ou non).

### Étape 3 - Nettoyez le code et finalisez le repository
- [ ] Je suis capable d'expliquer comment j'ai assuré la propreté du code 
  - [ ] Les méthodes sont bien découpées (elles ont une responsabilité limitée).
  - [ ] Il n'y a pas de code mort.
  - [ ] Les méthodes publiques des controllers sont commentées.
  - [ ] Le code est faiblement couplé.
- [ ] J'ai rédigé un README clair et exhaustif.
- [ ] En suivant exactement les procédures que j'ai rédigées, un autre développeur réussirait à installer le projet et la BDD.