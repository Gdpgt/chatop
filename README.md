# Estate

This project is based on Angular 20, and requires node 22.

## Start the project

Git clone:

```bash
git clone https://github.com/Gdpgt/chatop
```

Go inside folder:

```bash
cd chatop
```

Install dependencies:

```bash
npm install
```

Launch Front-end:

```bash
npm run start
```

## Ressources

### Mockoon env

Download Mockoon here: https://mockoon.com/download/

After installing you could load the environement

> ressources/mockoon/rental-oc.json

directly inside Mockoon

> File > Open environmement

For launching the Mockoon server click on play bouton

Mockoon documentation: https://mockoon.com/docs/latest/about/

### MySQL

#### Prerequisites

MySQL Server installed.

SQL script for creating the database, its user and its schema is available `ressources/sql/script.sql`.
You can launch it with:

```bash
mysql -u root -p < ressources/sql/script.sql
```

> **CAUTION** : Do not forget to replace the ${password} placeholder by the user password you choose (between single quotes). You can then save this password inside your .env file or your environment variables.
