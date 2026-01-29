-- Base pour le service Auth (TP2)
CREATE DATABASE IF NOT EXISTS auth_db;

-- Base pour le service Catalog (TP1)
CREATE DATABASE IF NOT EXISTS catalog_db;

-- Base pour le service Progress (Nouveau)
CREATE DATABASE IF NOT EXISTS progress_db;

-- Donner les droits à l'utilisateur (si vous n'utilisez pas root)
CREATE USER IF NOT EXISTS 'dev_user'@'%' IDENTIFIED BY 'dev_pass';
GRANT ALL PRIVILEGES ON *.* TO 'dev_user'@'%';
FLUSH PRIVILEGES;