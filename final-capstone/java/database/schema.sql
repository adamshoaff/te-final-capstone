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
    user_id int,
    family_id int,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    member_type varchar(50) NOT NULL,
    CONSTRAINT PK_member PRIMARY KEY (member_id),
    CONSTRAINT FK_member_family FOREIGN KEY (family_id) REFERENCES family(family_id),
    CONSTRAINT FK_member_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE family (
    family_id int DEFAULT nextval('seq_family_id'::regclass) NOT NULL,
    family_name varchar(50) NOT NULL,
    CONSTRAINT PK_family PRIMARY KEY (family_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('pbanks','password','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('gilmore','password','ROLE_USER');

INSERT INTO members (member_id,user_id, first_name, last_name, member_type)
VALUES (101,3, 'Phil', 'Banks', 'Parent'),
(102, null,'Vivanne', 'Banks', 'Parent'),
(103,null, 'Carlton', 'Banks', 'Child'),
(104, 4,'Lorelai', 'Gilmore', 'Parent'),
(105,null, 'Rory', 'Gilmore', 'Child');

INSERT INTO family (family_id, family_name)
VALUES (501, 'banksfamily'),
(501,'banksfamily'),
(501,'banksfamily'),
(502,'gilmoregirls'),
(502,'gilmoregirls');

COMMIT TRANSACTION;
