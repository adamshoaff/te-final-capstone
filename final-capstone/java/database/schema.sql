BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS family;
DROP TABLE IF EXISTS members;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_family_id;
DROP SEQUENCE IF EXISTS seq_member_id;


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
  START WITH 101
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;

CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_username PRIMARY KEY (username),
	CONSTRAINT UQ_username UNIQUE (username)
);

CREATE TABLE family (
    family_id int DEFAULT nextval('seq_family_id'::regclass) NOT NULL,
    username varchar(50) NOT NULL,
    CONSTRAINT PK_family PRIMARY KEY (family_id),
    CONSTRAINT FK_family_username FOREIGN KEY (username) REFERENCES users(username)
);

CREATE TABLE members (
    member_id int DEFAULT nextval('seq_member_id'::regclass) NOT NULL,
    family_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    member_type varchar(50) NOT NULL,
    CONSTRAINT PK_member PRIMARY KEY (member_id),
    CONSTRAINT FK_member_family FOREIGN KEY (family_id) REFERENCES family(family_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('gilmore','password','ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES('banksfamily', 'password', 'ROLE_USER');

INSERT INTO family (family_id, username)

VALUES (501, 'banksfamily'),(502,'gilmore'), (503,'admin');

--INSERT INTO members (member_id, family_id, first_name, last_name, member_type)
--VALUES (101, 501, 'Phil', 'Banks', 'Parent'),
--(102, 501, 'Vivanne', 'Banks', 'Parent'),
--(103, 501, 'Carlton', 'Banks', 'Child'),
--(104, 502, 'Lorelai', 'Gilmore', 'Parent'),
--(105, 502, 'Rory', 'Gilmore', 'Child');


COMMIT TRANSACTION;
