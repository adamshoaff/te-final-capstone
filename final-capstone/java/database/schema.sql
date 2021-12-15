BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS family;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS reading_activity;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_family_id;
DROP SEQUENCE IF EXISTS seq_member_id;
DROP SEQUENCE IF EXISTS seq_book_id;
DROP SEQUENCE IF EXISTS seq_activity_id;


CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_family_id
  INCREMENT BY 1
  START WITH 550
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
  START WITH 1050
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_activity_id
  INCREMENT BY 1
  START WITH 25
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
    last_name varchar(50),
    member_type varchar(50) NOT NULL,
    CONSTRAINT PK_members PRIMARY KEY (member_id),
    CONSTRAINT FK_members_user FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_members_family FOREIGN KEY (family_id) REFERENCES family(family_id)
);

CREATE TABLE books (
    book_id int DEFAULT nextval ('seq_book_id'::regclass) NOT NULL,
    isbn varchar(13) NOT NULL,
    title varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    family_id int,
    CONSTRAINT PK_books PRIMARY KEY (book_id),
    CONSTRAINT FK_books_family FOREIGN KEY (family_id) REFERENCES family(family_id)
);
CREATE TABLE reading_activity (
    activity_id int DEFAULT nextval ('seq_activity_id'::regclass) NOT NULL,
    activity_date timestamp default NOW(),
    reading_format varchar(50) NOT NULL,
    reading_minutes int NOT NULL,
    is_completed boolean default false,
    member_id int,
    book_id int,
    family_id int,
    reader_notes varchar(500),
    CONSTRAINT PK_reading_activity PRIMARY KEY (activity_id),
    CONSTRAINT FK_activity_member FOREIGN KEY (member_id) REFERENCES members(member_id),
    CONSTRAINT FK_activity_book FOREIGN KEY (book_id) REFERENCES books(book_id),
    CONSTRAINT FK_activity_family FOREIGN KEY (family_id) REFERENCES family(family_id)
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

INSERT INTO books (book_id, isbn, title, author, family_id)
VALUES (1001, 12345, 'Will', 'Will Smith', 501),
       (1002, 23456, 'The Splendid and The Vile', 'Erik Larson', 501),
       (1003, 22222, 'The Amber Spyglass', 'Philip Pullman', 501),
       (1004, 33333, 'Nature Anatomy', 'Julia Rothman', 502),
       (1005, 44444, 'The Innovators', 'Walter Isaacson', 502);

INSERT INTO reading_activity (activity_id, activity_date, reading_format, reading_minutes, member_id, book_id,family_id, reader_notes)
VALUES (21, '12/12/2021', 'Audiobook', 60, 103, 1001,501, 'This was a great book!'),
        (22, '12/13/2021', 'Paperback', 30, 101, 1002, 501,'I hated this book!');


COMMIT TRANSACTION;
