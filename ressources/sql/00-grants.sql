-- Exécuté par l'image Docker MySQL au premier démarrage, après la création de MYSQL_USER.
-- L'image accorde ALL PRIVILEGES à cet utilisateur : on le restreint au CRUD, comme script.sql.
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'chatop'@'%';
GRANT SELECT, INSERT, UPDATE, DELETE ON chatop_db.* TO 'chatop'@'%';
