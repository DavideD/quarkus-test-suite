DROP TABLE IF EXISTS authors;
CREATE TABLE authors (id int NOT NULL, name VARCHAR(100) NOT NULL, PRIMARY KEY (id));
INSERT INTO authors(id,name) VALUES (1, 'Homer');
INSERT INTO authors(id,name) VALUES (2, 'Vern');
INSERT INTO authors(id,name) VALUES (3, 'Dlugi');
INSERT INTO authors(id,name) VALUES (4, 'Kahneman');
DROP TABLE IF EXISTS books;
CREATE TABLE books (id INT IDENTITY(1,1),author INT, title VARCHAR(100) NOT NULL, isbn VARCHAR(100) NULL, PRIMARY KEY(id),FOREIGN KEY (author)REFERENCES authors(id) ON DELETE CASCADE ON UPDATE CASCADE);
INSERT INTO books(author, title) VALUES (3, 'Slovník');
INSERT INTO books(author, title, isbn) VALUES (4, 'Thinking fast and slow', '978-0374275631');
INSERT INTO books(author, title) VALUES (4, 'Attention and Effort');
