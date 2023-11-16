-- scripts/create_table_author.sql
CREATE TABLE IF NOT EXISTS author (
    id SERIAL PRIMARY KEY,
    authorName VARCHAR(255),
    sex CHAR(1) CHECK (sex IN ('M', 'F'))
);

-- Insérer des données dans la table "author"
INSERT INTO author (id, authorName, sex)
VALUES 
  (1, 'John Doe', 'M'),
  (2, 'Jane Doe', 'F');
