BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS family;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_member_id;
DROP SEQUENCE IF EXISTS seq_family_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_member_id
  INCREMENT BY 1
  START WITH 101
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE SEQUENCE seq_family_id
  INCREMENT BY 1
  START WITH 501
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE members (
    member_id int DEFAULT nextval('seq_member_id'::regclass) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    member_type varchar(50) NOT NULL,
    CONSTRAINT PK_member PRIMARY KEY (member_id)
);

CREATE TABLE family (
    family_id int DEFAULT nextval('seq_family_id'::regclass) NOT NULL,
    family_name varchar(50) NOT NULL,
    member_id int NOT NULL,
    CONSTRAINT FK_family_member FOREIGN KEY (member_id) REFERENCES members(member_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('pbanks','password','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('gilmore','password','ROLE_USER');

INSERT INTO members (member_id, first_name, last_name, member_type)
VALUES (101, 'Phil', 'Banks', 'Parent'),
(102, 'Vivanne', 'Banks', 'Parent'),
(103, 'Carlton', 'Banks', 'Child'),
(104, 'Lorelai', 'Gilmore', 'Parent'),
(105, 'Rory', 'Gilmore', 'Child');

INSERT INTO family (family_id, family_name, member_id)
VALUES (501, 'banksfamily', 101),
(501, 'banksfamily', 102),
(501, 'banksfamily', 103),
(502, 'gilmoregirls', 104),
(502, 'gilmoregirls', 105);

COMMIT TRANSACTION;
