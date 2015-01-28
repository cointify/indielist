ALTER TABLE ads DROP CONSTRAINT FKads200921;
ALTER TABLE ads DROP CONSTRAINT FKads373998;
ALTER TABLE users_roles DROP CONSTRAINT FKusers_role644573;
ALTER TABLE users_roles DROP CONSTRAINT FKusers_role564647;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS ads CASCADE;
DROP TABLE IF EXISTS application_settings CASCADE;
DROP TABLE IF EXISTS categories CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS users_roles CASCADE;
CREATE TABLE users (
  username         varchar(255) NOT NULL, 
  password         varchar(255) NOT NULL, 
  first_name       varchar(255) NOT NULL, 
  email            varchar(255) NOT NULL, 
  last_name        varchar(255) NOT NULL, 
  create_timestamp timestamp NOT NULL, 
  update_timestamp timestamp NOT NULL, 
  PRIMARY KEY (username));
CREATE TABLE ads (
  id                BIGSERIAL NOT NULL, 
  user_username    varchar(255) NOT NULL, 
  title            varchar(100) NOT NULL, 
  description      varchar(5000) NOT NULL, 
  phone_number     varchar(12) NOT NULL, 
  enabled          char(1) NOT NULL, 
  price            numeric(19, 2) NOT NULL, 
  categorie_name   varchar(255) NOT NULL, 
  street_name      varchar(255), 
  city             varchar(255) NOT NULL, 
  province         varchar(50) NOT NULL, 
  country          varchar(50) NOT NULL, 
  postal_code      varchar(255), 
  create_timestamp timestamp NOT NULL, 
  update_timestamp timestamp NOT NULL, 
  PRIMARY KEY (id));
CREATE TABLE application_settings (
  name  varchar(255) NOT NULL, 
  value varchar(255) NOT NULL, 
  PRIMARY KEY (name));
CREATE TABLE categories (
  name varchar(255) NOT NULL, 
  PRIMARY KEY (name));
CREATE TABLE roles (
  name varchar(255) NOT NULL, 
  PRIMARY KEY (name));
CREATE TABLE users_roles (
  user_username varchar(255) NOT NULL, 
  role_name     varchar(255) NOT NULL, 
  PRIMARY KEY (user_username, 
  role_name));
ALTER TABLE ads ADD CONSTRAINT FKads200921 FOREIGN KEY (user_username) REFERENCES users (username);
ALTER TABLE ads ADD CONSTRAINT FKads373998 FOREIGN KEY (categorie_name) REFERENCES categories (name);
ALTER TABLE users_roles ADD CONSTRAINT FKusers_role644573 FOREIGN KEY (user_username) REFERENCES users (username);
ALTER TABLE users_roles ADD CONSTRAINT FKusers_role564647 FOREIGN KEY (role_name) REFERENCES roles (name);
