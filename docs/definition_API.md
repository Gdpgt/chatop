# Définition d’API

> Le front Angular est immuable : en cas d'écart avec le mock Mockoon (`ressources/mockoon/rental-oc.json`), c'est le comportement du front qui fait foi. Toutes les routes sauf `register` et `login` exigent un header `Authorization: Bearer <jwt>` (sinon `401`).

| URL | Méthode | Description | Params (path/query) | Body (JSON) | Status | Exemple de réponse |
| --- | --- | --- | --- | --- | --- | --- |
| `/api/auth/register` | `POST` | Créer un compte utilisateur | `-` | `{ "email": "", "name": "", "password": "" }` | `201`, `400` | `{ "token": "jwt" }` |
| `/api/auth/login` | `POST` | S'authentifier | `-` | `{ "email": "", "password": "" }` | `200`, `400`, `401` | `{ "token": "jwt" }` |
| `/api/auth/me` | `GET` | Récupérer l'utilisateur authentifié (déduit du JWT) | `-` | `-` | `200`, `401` | `{ "id": 1, "name": "Test TEST", "email": "test@test.com", "created_at": "2022-02-02T10:15:30", "updated_at": "2022-08-02T10:15:30" }` |
| `/api/user/:id` | `GET` | Récupérer les infos d'un utilisateur | `id` | `-` | `200`, `401`, `404` | `{ "id": 2, "name": "Owner Name", "email": "test@test.com", "created_at": "2022-02-02T10:15:30", "updated_at": "2022-08-02T10:15:30" }` |
| `/api/rentals` | `GET` | Récupérer toutes les locations | `-` | `-` | `200`, `401` | `{ "rentals": [ { "id": 1, "name": "", "surface": 0, "price": 0, "picture": "http://…/image.jpg", "description": "", "owner_id": 1, "created_at": "2022-02-02T10:15:30", "updated_at": "2022-08-02T10:15:30" } ] }` |
| `/api/rentals/:id` | `GET` | Récupérer une location | `id` | `-` | `200`, `401`, `404` | `{ "id": 1, "name": "", "surface": 0, "price": 0, "picture": "http://…/image.jpg", "description": "", "owner_id": 1, "created_at": "2022-02-02T10:15:30", "updated_at": "2022-08-02T10:15:30" }` |
| `/api/rentals` | `POST` | Créer une location (propriétaire = utilisateur du JWT) | `-` | `multipart/form-data` : `name`, `surface`, `price`, `description` + `picture` (fichier, obligatoire) | `201`, `400`, `401`, `413` | `{ "message": "Rental created !" }` |
| `/api/rentals/:id` | `PUT` | Mettre à jour une location (propriétaire uniquement, image conservée) | `id` | `multipart/form-data` : `name`, `surface`, `price`, `description` | `200`, `400`, `401`, `403`, `404` | `{ "message": "Rental updated !" }` |
| `/api/rentals/:id` | `DELETE` | Supprimer une location (propriétaire uniquement) | `id` | `-` | `204`, `401`, `403`, `404` | `-` |
| `/api/messages` | `POST` | Envoyer un message au propriétaire d'une location (expéditeur = utilisateur du JWT) | `-` | `{ "rental_id": 1, "user_id": 1, "message": "" }` | `201`, `400`, `401`, `404` | `{ "message": "Message sent with success" }` |

## Contraintes imposées par le front

- **Noms de champs JSON en snake_case** : `owner_id`, `created_at`, `updated_at`, `rental_id`, `user_id` (lus/envoyés tels quels par les interfaces Angular).
- **Dates** : ISO 8601 (`LocalDateTime` sérialisé par défaut par Jackson), affichées par le pipe `date` d'Angular.
- **`owner_id`** n'est jamais envoyé par le formulaire de location : le back le déduit du JWT.
- **`picture`** est une chaîne (URL de l'image stockée sur le serveur), y compris dans `GET /api/rentals/:id` (le mock renvoie un tableau, le front attend `string`).
- **`PUT /api/rentals/:id`** : le front n'envoie jamais `picture` ; l'image existante est conservée.
- **`POST /api/messages`** : `user_id` est envoyé par le front mais ignoré ; l'expéditeur est l'utilisateur authentifié.
- **`DELETE /api/rentals/:id`** : non appelé par le front (exigence du brief P3_2).

## Codes d'erreur

- `400` : body invalide (champ manquant ou invalide, image manquante en `POST /api/rentals`, email déjà utilisé au `register`).
- `401` : JWT absent/invalide, ou identifiants incorrects au `login`.
- `403` : `PUT`/`DELETE` d'une location dont l'utilisateur n'est pas propriétaire.
- `404` : id inconnu (utilisateur, location, `rental_id` d'un message).

# Dépendances Spring Boot à utiliser :

- Spring Boot Starter Web
- Spring Boot Starter Data Jpa
- MySQL Connector
- Spring Boot Starter Security
- JSONWebToken
- Spring Boot Starter Validation
- Spring Boot Devtools
- Springdoc OpenAPI Starter WebMVC UI
