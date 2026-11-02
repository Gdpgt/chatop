# Estate

Rental application: Angular 20 front-end, Spring Boot back-end (Java 25) and MySQL 8.4 database.

## Prerequisites

- Node 22 or 24
- JDK 25 (with SDKMAN: `sdk env install` then `sdk env` at the project root, see `.sdkmanrc`)
- Docker with the Compose plugin, **or** a local MySQL Server

## Start the project

### 1. Clone

```bash
git clone https://github.com/Gdpgt/chatop
cd chatop
```

### 2. Configure the environment

```bash
cp env.properties.example env.properties
```

Edit `env.properties` (it is not versioned):

- `MYSQL_ROOT_PASSWORD`, `MYSQL_PASSWORD`: passwords of your choice
- `JWT_SECRET`: generate your own with `openssl rand -base64 32`

### 3. Database (MySQL)

#### Option A: Docker (recommended)

```bash
docker compose --env-file env.properties up -d
```

On first start, the container creates the `chatop_db` database, the `chatop` user
(restricted to SELECT, INSERT, UPDATE, DELETE) and the schema (`ressources/sql/schema.sql`).
MySQL listens on `127.0.0.1:3306` only.

These init scripts only run when the data volume is empty. To reset the database
(deletes all data):

```bash
docker compose down -v
docker compose --env-file env.properties up -d
```

#### Option B: Local MySQL Server

SQL script for creating the database, its user and its schema is available `ressources/sql/script.sql`.
Run it from the project root (it loads `ressources/sql/schema.sql` through a relative path):

```bash
mysql -u root -p < ressources/sql/script.sql
```

> **CAUTION**: Do not forget to replace the `${MYSQL_PASSWORD}` placeholder in `script.sql` by the same password as `MYSQL_PASSWORD` in `env.properties` (between single quotes).

### 4. Back-end

```bash
cd backend
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

The API is served on `http://localhost:3001/api`. The back-end reads `../env.properties`
and checks at startup that the database schema matches its entities.

#### VS Code

Set the JDK in your user settings (the path is machine-specific, so `.vscode/` is not versioned):

```json
"java.configuration.runtimes": [
  { "name": "JavaSE-25", "path": "/path/to/jdk-25", "default": true }
]
```

Maven needs no setup: the Maven extension uses `backend/mvnw`.

### 5. Front-end

In another terminal, from the project root:

```bash
npm install
npm run start
```

Open `http://localhost:4200`. Calls to `/api` are proxied to the back-end (`src/proxy.config.json`).

## API documentation (Swagger)

With the back-end running, open **http://localhost:3001/swagger-ui.html**
(OpenAPI JSON: `http://localhost:3001/v3/api-docs`). The documentation is public.

Every route except `POST /api/auth/register` and `POST /api/auth/login` requires a JWT. To test them:

1. Call `POST /api/auth/register` (or `/login` with an existing account) and copy the `token` from the response.
2. Click **Authorize**, paste the token (without the `Bearer ` prefix) and confirm.
3. The secured routes are now sent with `Authorization: Bearer <token>`.

| Method | Route | Auth |
|---|---|---|
| `POST` | `/api/auth/register` | public |
| `POST` | `/api/auth/login` | public |
| `GET` | `/api/auth/me` | JWT |
| `GET` | `/api/user/{id}` | JWT |
| `GET` | `/api/rentals` | JWT |
| `GET` | `/api/rentals/{id}` | JWT |
| `POST` | `/api/rentals` (multipart) | JWT |
| `PUT` | `/api/rentals/{id}` (multipart) | JWT, owner |
| `DELETE` | `/api/rentals/{id}` | JWT, owner |
| `POST` | `/api/messages` | JWT |

Full specification: `docs/definition_API.md`.

## Uploaded images

Rental pictures are stored in `backend/uploads/` (override with `UPLOAD_DIR` in `env.properties`) and served
publicly at `http://localhost:3001/images/<file>` (override the base URL with `PUBLIC_URL`).

## Development tools (optional)

### Mockoon

Mockoon mocks the API with static responses, to work on the front-end without the back-end.

> **CAUTION**: the mock listens on port 3001, like the back-end. Do not run both at the same time.

Download Mockoon here: https://mockoon.com/download/

After installing, load the environment `ressources/mockoon/rental-oc.json` inside Mockoon
(**File > Open environment**), then click the play button to start the mock server.

Mockoon documentation: https://mockoon.com/docs/latest/about/

## Differences from the Mockoon mock

The Angular front-end cannot be modified, so the back-end follows what the front-end actually sends and reads.
It deliberately differs from the mock on these points (details in `docs/definition_API.md`):

- **Status codes**: `201 Created` for register, rental creation and message sending (the mock returns `200`).
- **Extra errors**: `400` on invalid body, `404` on unknown id, `403` when a user who is not the owner updates or deletes a rental.
- **`DELETE /api/rentals/:id`**: added (`204`, owner only), although the front-end does not call it.
- **Rental owner and message sender**: taken from the JWT; the `user_id` sent by the front-end in `POST /api/messages` is ignored.
- **`picture`**: always a string (image URL), including in `GET /api/rentals/:id` where the mock returns an array.
- **Dates**: ISO 8601 (`2022-02-02T10:15:30`) instead of `YYYY/MM/DD`.
