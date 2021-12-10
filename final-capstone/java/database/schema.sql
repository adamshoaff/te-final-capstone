BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS family;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS books;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_family_id;
DROP SEQUENCE IF EXISTS seq_member_id;
DROP SEQUENCE IF EXISTS seq_book_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_family_id
  INCREMENT BY 1
  START WITH 501
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_member_id
  INCREMENT BY 1
  START WITH 150
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_book_id
  INCREMENT BY 1
  START WITH 1001
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_users PRIMARY KEY (user_id)
);

CREATE TABLE family (
    family_id int DEFAULT nextval('seq_family_id'::regclass) NOT NULL,
    family_name varchar(50) NOT NULL,
    CONSTRAINT PK_family PRIMARY KEY (family_id)
);

CREATE TABLE members (
    member_id int DEFAULT nextval('seq_member_id'::regclass) NOT NULL,
    user_id int,
    family_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    member_type varchar(50) NOT NULL,
    CONSTRAINT PK_members PRIMARY KEY (member_id),
    CONSTRAINT FK_members_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_members_family FOREIGN KEY (family_id) REFERENCES family(family_id)
);

CREATE TABLE books (
    book_id int DEFAULT nextval ('seq_book_id'::regclass) NOT NULL,
    book_name varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    family_id int,
    CONSTRAINT PK_books PRIMARY KEY (book_id),
    CONSTRAINT FK_books_family FOREIGN KEY (family_id) REFERENCES family(family_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('gilmore','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES('banksfamily', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'ROLE_USER');

INSERT INTO family (family_id, family_name)
VALUES (501, 'banksfamily'),(502,'gilmore'), (503,'admin');

INSERT INTO members (member_id, user_id, family_id, first_name, last_name, member_type)
VALUES (101, 4, 501, 'Phil', 'Banks', 'Parent'),
       (102, null, 501, 'Vivanne', 'Banks', 'Parent'),
       (103, null, 501, 'Carlton', 'Banks', 'Child'),
       (104, 3, 502, 'Lorelai', 'Gilmore', 'Parent'),
       (105, null, 502, 'Rory', 'Gilmore', 'Child');

INSERT INTO books (book_id, book_name, author, family_id)
VALUES (1001, 'Will', 'Will Smith', 501),
       (1002, 'The Splendid and The Vile', 'Erik Larson', 501),
       (1003, 'The Amber Spyglass', 'Philip Pullman', 501),
       (1004, 'Nature Anatomy', 'Julia Rothman', 502),
       (1005, 'The Innovators', 'Walter Isaacson', 502);


COMMIT TRANSACTION;
