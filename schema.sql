-- DROP TABLE TAQUERIAS;

CREATE TABLE TAQUERIAS (
  ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  "NAME" VARCHAR(128) NOT NULL, 
  RATING INTEGER NOT NULL, 
  UNIQUE ("NAME"),
  PRIMARY KEY (ID)
);

INSERT INTO TAQUERIAS ("NAME", RATING) VALUES 
    ('Changos Taqueria', 5),
    ('Torchy''s Tacos', 4),
    ('Taco Deli', 5),
    ('Taco Bell', 2),
    ('Fuzzy''s Taco Shop', 4),
    ('Taco Cabana', 2),
    ('Cabo Bob''s', 3);
