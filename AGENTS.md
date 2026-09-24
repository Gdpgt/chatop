# ChâTop — projet de formation OpenClassrooms (P3)

## Démarrage de session

Avant de répondre sur le projet, lis `docs/avancement.md` et les 5 briefs de
`docs/oc/` : ce sont la source de vérité détaillée (cette page ne les remplace
pas). La grille d'évaluation est `docs/oc/Fiches_auto-evaluation.md`.

## Mission

Développer le back-end Java / Spring Boot de ChâTop (mise en relation locataires /
propriétaires) qui remplace le mock Mockoon consommé par le front Angular fourni,
**sans modifier ce front**. Étapes détaillées : briefs `docs/oc/`, suivi :
`docs/avancement.md`.

## Commandes

Front (existant) :
- `npm install`
- `npm run start` — `ng serve`, proxy `/api/**` → `http://localhost:3001` (`src/proxy.config.json`)
- `npm test` — Jest
- `npm run build`
- `npm run lint`

Back-end : aucune commande tant qu'aucun `pom.xml` n'existe (à compléter à sa création).

Contrat de référence : `ressources/mockoon/rental-oc.json` (port 3001, préfixe
`api`, 9 routes) ; schéma BDD : `ressources/sql/script.sql`.

## Zones

- **Immuable** : `docs/oc/` (briefs, jamais modifiés).
- **Gelé par le brief** (« interdiction de modifier le front-end ») : `src/app/`,
  `src/assets/`, `src/environments/`, fichiers racine de `src/`, `angular.json`,
  `package*.json`, `yarn.lock`, `tsconfig*.json`, `jest.config.ts`,
  `setup-jest.ts`, `.eslintrc.json`, `.editorconfig`. Le gel prime sur « config ».
- **Config — demander avant** : `application*.properties` / `*.yml`, `pom.xml`,
  `.env*`, `.gitignore`, `ressources/`, CI (aucune à ce jour).
- **Livrables de l'apprenant** : spécification des endpoints (modèle
  `docs/oc/Modèle_définition_API.md`), code back-end, BDD `chatop_db` + utilisateur
  dédié, `README.md` d'installation, branche de release. Non verrouillés. Si
  l'apprenant demande à l'agent d'en rédiger un, l'agent le fait sans remettre la
  demande en question. Seul le
  mode `/tutor` (Claude Code) fait guider au lieu de rédiger.

## Non exigé par les briefs

Tests automatisés, CI / Docker / déploiement, rôles / refresh token / OAuth,
pagination / cache, toute modification du front.

## Divergences entre sources (à arbitrer par l'apprenant, ne pas trancher)

1. Routes : P3_2 écrit `/register`, `/login`, `/rentals`, `/messages`, `/user` sans préfixe ↔ modèle d'API et mock : `/api/auth/register|login`, `/api/user/:id`… (+ `GET /api/auth/me`).
2. Statuts : P3_2 cite 201/404, P3_1 200/400/500 ↔ le mock ne renvoie que 200/400/401.
3. Verbes : P3_2 cite `DELETE` ↔ aucune route DELETE dans le mock.
4. Body : le modèle d'API prévoit « Body (JSON) » ↔ le front envoie `POST`/`PUT /api/rentals` en `multipart/form-data` (champ `picture`).
5. Credentials : P3_1 « `.env` ou `application.properties` » ↔ P3_2 « pas de valeurs en clair dans `application.properties` ».
6. README : P3_2 veut un README Spring Boot à la racine ↔ le README racine actuel est celui du front.
7. Java : specs « 11 ou 17 » ↔ Spring Boot 3.x exige Java 17 minimum.
8. Mock : le 401 de `auth/login` teste `body.login` (pas `email`) — cas d'erreur peu fiable pour la spec.

## Garde-fou de concentration (toujours actif, mode tuteur ou non)

L'apprenant a tendance à creuser chaque concept au lieu d'avancer, et rend ses
projets en retard. Protège son temps, sans jamais bloquer son avancement.

- **Ne s'applique jamais** à une erreur, un bug, une question qui bloque la tâche
  en cours, ni à une demande de faire quelque chose : réponds ou exécute
  normalement.
- Pour une question de compréhension, compare-la à ce qu'exigent l'étape en cours
  (`docs/avancement.md`) et les briefs, puis :
  - **exigé par l'étape en cours** → réponds normalement ;
  - **exigé par une étape ultérieure du projet** → réponse courte, et nomme cette
    étape (telle qu'écrite dans `docs/avancement.md`) ;
  - **non exigé par ce projet** → ne développe pas : dis en une phrase ce que
    l'étape exige concrètement à la place, puis dans quel type de situation le
    sujet deviendra utile. Pas de délai chiffré : tu ne connais pas le parcours
    de l'apprenant. Si tu ne sais pas situer le sujet, dis-le.
- Justifie le classement en une ligne, à partir des briefs ou de l'avancement,
  pas avec une formule toute faite.
- Si la conversation s'éloigne de l'étape à plusieurs reprises, rappelle la tâche
  en cours et ce qu'il reste à faire.
- Si l'apprenant insiste pour creuser, réponds.

## Règles

- Aucun secret en clair (credentials BDD, clé JWT) : variables d'environnement ou
  fichier non versionné.
- Commits : Conventional Commits.
