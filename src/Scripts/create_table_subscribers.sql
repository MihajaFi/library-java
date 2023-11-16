-- scripts/create_table_visitor.sql
CREATE TABLE IF NOT EXISTS subscribers (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255),
    reference VARCHAR(255)
);

-- Insérer des données dans la table "subscribers"
INSERT INTO subscribers (id, name, reference)
VALUES 
  ('1', 'Visitor 1', 'Reference 1'),
  ('2', 'Visitor 2', 'Reference 2');
