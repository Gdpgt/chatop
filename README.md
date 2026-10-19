# Estate

Rental application: Angular 20 front-end, Spring Boot back-end (Java 25) and MySQL 8.4 database.

## Prerequisites

- Node 22 or 24
- JDK 25
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

### 5. Front-end

In another terminal, from the project root:

```bash
npm install
npm run start
```

Open `http://localhost:4200`. Calls to `/api` are proxied to the back-end (`src/proxy.config.json`).

## Development tools (optional)

### Mockoon

Mockoon mocks the API with static responses, to work on the front-end without the back-end.

> **CAUTION**: the mock listens on port 3001, like the back-end. Do not run both at the same time.

Download Mockoon here: https://mockoon.com/download/

After installing, load the environment `ressources/mockoon/rental-oc.json` inside Mockoon
(**File > Open environment**), then click the play button to start the mock server.

Mockoon documentation: https://mockoon.com/docs/latest/about/
