CREATE TYPE topic_enum AS ENUM ('ROMANCE', 'COMEDY', 'OTHER');

CREATE TABLE IF NOT EXISTS "book" (
    id SERIAL PRIMARY KEY,
    type VARCHAR(255),
    bookName VARCHAR(255),
    pageNumbers INTEGER,
    topic topic_enum,
    releaseDate DATE,
    authorId INT REFERENCES author(id),
    status VARCHAR(255) CHECK (status IN ('AVAILABLE', 'BORROWED'))
);


-- Insérer des données dans la table "book"
INSERT INTO "book" (type, bookName, pageNumbers, topic, releaseDate, authorId , status)
VALUES 
  ('Novel', 'Pride and Prejudice', 279, 'ROMANCE', '1813-01-28', 1, 'AVAILABLE'),
  ('Science Fiction', 'Dune', 412, 'OTHER', '1965-06-01', 2,'AVAILABLE'),
  ('Comedy', 'The Hitchhiker''s Guide to the Galaxy', 224, 'COMEDY', '1979-10-12',3,  'BORROWED');
