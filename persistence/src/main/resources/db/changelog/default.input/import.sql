INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number) VALUES ('2016-06-13','admin@admin','Admin','admin','Adminius','1111','380000000');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number) VALUES ('1993-10-31','vilumkris@gmail.com','kris','female','vilum','1111','380931286864');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number) VALUES ('1990-11-28', 'n.svidersky@gmail.com','Nazar','male','Sviderskyi','fatmom2016','0931544845');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number) VALUES ('1996-02-21','KostetskyRoma@gmail.com','Roman','male','Kostetsky','1234','380687773508');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number) VALUES ('1992-12-30','butaroman@gmail.com','Roman','male','Buta','1111','0632571119');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number) VALUES ('1997-06-02','cavayman@gmail.com','Oleg','male','Kotsik','1111','0679167305');

INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO role(name) VALUES('ROLE_GOLOVA');
INSERT INTO role(name) VALUES('ROLE_MANAGER');

update users set role='2' where user_id='1';
