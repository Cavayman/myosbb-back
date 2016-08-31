INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO role(name) VALUES('ROLE_GOLOVA');
INSERT INTO role(name) VALUES('ROLE_MANAGER');

INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('2016-06-13','admin@admin','Admin','admin','Adminius','1111','380000000','2');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('1993-10-31','vilumkris@gmail.com','kris','female','vilum','1111','380931286864','1');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('1990-11-28', 'n.svidersky@gmail.com','Nazar','male','Sviderskyi','fatmom2016','0931544845','1');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('1996-02-21','KostetskyRoma@gmail.com','Roman','male','Kostetsky','1234','380687773508','1');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('1992-12-30','butaroman@gmail.com','Roman','male','Buta','1111','0632571119','1');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('1997-06-02','cavayman@gmail.com','Oleg','male','Kotsik','1111','0679167305','1');
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role) VALUES ('2916-04-14','nazardovhyi@gmail.com','Nazar','male','Dovhyi','151515','0973055976','1');

/*Nazar's test data*/
INSERT INTO provider(name,description, email, phone, address) VALUES('YardCleaning LTD', 'the best ones in the biz', 'wethebestyardcleaning@ymail.com', 0667854645, 'not available' );
INSERT INTO house(city, street, zip_code, description) VALUES('Lviv', 'Rjashivska 9', '79040', 'a nice house with a lot of parking spaces');
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(3, 45, 1, 7);
UPDATE users set apartment_id = 1 where user_id=7;
INSERT INTO apartment (number, square, house_id) VALUES(10, 45, 1);
INSERT INTO apartment (number, square, house_id) VALUES(12, 12, 1);
INSERT INTO apartment (number, square, house_id) VALUES(23, 45, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-05-28', 350.5,0, 4.5, 2, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-05-28', 280.5,120, 4.5, 1, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-05-28', 320,320, 4.5, 3,  'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id)VALUES('2016-06-28', 240.5,0, 2.5, 1, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-06-28', 356,356, 4.5, 1, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-07-28', 240.5,0, 2, 3, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-07-28', 150, 0, 4.5, 2, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-07-28', 350.5,0, 4.5, 4, 'PAID', 1);

