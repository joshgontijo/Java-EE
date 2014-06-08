USE MY_DB;
CREATE TABLE users (
username varchar(20) NOT NULL PRIMARY KEY,
password varchar(20) NOT NULL
);
CREATE TABLE roles (
rolename varchar(20) NOT NULL PRIMARY KEY
);
CREATE TABLE users_roles (
username varchar(20) NOT NULL,
rolename varchar(20) NOT NULL,
PRIMARY KEY (username, rolename),
CONSTRAINT users_roles_fk1 FOREIGN KEY (username) REFERENCES users (username),
CONSTRAINT users_roles_fk2 FOREIGN KEY (rolename) REFERENCES roles (rolename)
);

INSERT INTO `users` (`username`, `password`) VALUES ('josue', '1');
INSERT INTO `roles` (`rolename`) VALUES ('user');
INSERT INTO `users_roles` (`username`, `rolename`) VALUES ('josue', 'user');
COMMIT;
