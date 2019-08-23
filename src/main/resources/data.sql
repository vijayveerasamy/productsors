drop table if exists Product;
drop table if exists ProductOrder;

CREATE TABLE Product (
  id   INTEGER      NOT NULL PRIMARY KEY,
  name VARCHAR(128) NOT NULL,
  stock INTEGER NOT NULL
);

CREATE TABLE ProductOrder (
  id   INTEGER      NOT NULL PRIMARY KEY,
  productId   INTEGER  NOT NULL,
  volume INTEGER NOT NULL
);


INSERT INTO Product (id, name, stock) VALUES (1, 'a', 5);
INSERT INTO Product (id, name, stock) VALUES (2, 'b', 8);
INSERT INTO Product (id, name, stock) VALUES (3, 'c', 2);
INSERT INTO Product (id, name, stock) VALUES (4, 'd', 0);
INSERT INTO Product (id, name, stock) VALUES (5, 'e', 1);
