INSERT INTO users (USERNAME,PASSWORD) VALUES ('user','test');
INSERT INTO users (USERNAME,PASSWORD) VALUES ('admin','test');
INSERT INTO users (USERNAME,PASSWORD) VALUES ('both','test');
INSERT INTO users_roles (Users_USERNAME,ROLES) VALUES ('user','User');
INSERT INTO users_roles (Users_USERNAME,ROLES) VALUES ('admin','Admin');
INSERT INTO users_roles (Users_USERNAME,ROLES) VALUES ('both', 'User');
INSERT INTO users_roles (Users_USERNAME,ROLES) VALUES ('both', 'Admin');
