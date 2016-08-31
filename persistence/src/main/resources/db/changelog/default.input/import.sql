INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO role(name) VALUES('ROLE_GOLOVA');
INSERT INTO role(name) VALUES('ROLE_MANAGER');

INSERT INTO house(city, street, zip_code, description) VALUES('Lviv', 'Rjashivska 9', '79040', 'a nice house with a lot of parking spaces');
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(1, 44, 1, 1);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(2, 67, 1, 2);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(3, 98, 1, 3);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(4, 67, 1, 4);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(5, 100, 1, 5);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(6, 87, 1, 6);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(7, 89, 1, 7);
INSERT INTO apartment (number, square, house_id, owner_id) VALUES(8, 66, 1, 8);
INSERT INTO apartment (number, square, house_id) VALUES(9,  55, 1);
INSERT INTO apartment (number, square, house_id) VALUES(10, 69, 1);
INSERT INTO apartment (number, square, house_id) VALUES(11, 99, 1);


INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('2016-06-13','admin@admin','Admin','admin','Adminius','1111','380000000','2',1,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1993-10-31','vilumkris@gmail.com','kris','female','vilum','1111','380931286864','1',2,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1990-11-28', 'n.svidersky@gmail.com','Nazar','male','Sviderskyi','fatmom2016','0931544845','1',3,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1996-02-21','KostetskyRoma@gmail.com','Roman','male','Kostetsky','1234','380687773508','1',4,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1992-12-30','butaroman@gmail.com','Roman','male','Buta','1111','0632571119','1',5,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1997-06-02','cavayman@gmail.com','Oleg','male','Kotsik','1111','0679167305','1',6,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('2916-04-14','nazardovhyi@gmail.com','Nazar','male','Dovhyi','151515','0973055976','1',7,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1994-12-16','oleg111koval@gmail.com','Oleg','male','Koval','123456','095729666','1',8,TRUE );


INSERT INTO provider_type ( type_name) VALUES ('Internet');
INSERT INTO provider_type ( type_name) VALUES ('Trash');
INSERT INTO provider_type ( type_name) VALUES ('Electricity');
INSERT INTO provider_type ( type_name) VALUES ('Water');
INSERT INTO provider_type ( type_name) VALUES ('House cleaning');
INSERT INTO provider_type ( type_name) VALUES ('Some other provider');


INSERT INTO provider (name, description, logo_url, periodicity, provider_type_id, email, phone, address, schedule, active)
VALUES ('Volya', 'Інтернет провайдер', 'https://lh3.googleusercontent.com/ChIb4t3-f90R0s4qu3BGuTh2-t0HoA7q6L6fTkRdfMrMKDj3rgYBR3SiNxcjr_o9FMU=w300', 'PERMANENT_MONTHLY', 1,
        'volya@gmail.com', '(093)226-86-34', 'м. Львів, вул. Золотогірська 5а', 'пн-пт 09.00-20.00', TRUE );

INSERT INTO provider (name, description, logo_url, periodicity, provider_type_id, email, phone, address, schedule, active)
VALUES ('Trash Provider', 'Служба виносу сміття', 'http://www.ci.stillwater.mn.us/vertical/Sites/%7B5BFEF821-C140-4887-AEB5-99440411EEFD%7D/uploads/%7B99CA72DD-0AF9-4598-BC43-EAA2BB6625B4%7D.GIF', 'PERMANENT_MONTHLY', 2,
        'trash@gmail.com', '(093)226-86-34', 'м. Львів, вул. Широка 25', 'пн-пт 09.00-20.00', TRUE );

INSERT INTO provider (name, description, logo_url, periodicity, provider_type_id, email, phone, address, schedule, active)
VALUES ('Electro com', 'Електрослужба', 'https://stocklogos-pd.s3.amazonaws.com/styles/logo-medium-alt/logos/image/f576842989a8d05aa71c0f79ad1c48ae.png?itok=IK4NARiQ', 'PERMANENT_MONTHLY', 3,
        'volya@gmail.com', '(093)226-86-34','м. Львів, вул. Личаківська 55',  'пн-пт 09.00-20.00', TRUE);

INSERT INTO provider (name, description, logo_url, periodicity, provider_type_id, email, phone, address, schedule, active)
VALUES ('True Water', 'Сервіс доставки води', 'http://edc1.securesites.net/~edc/drinkhydrasonic.com/images/logo/water-logo.png', 'PERMANENT_YEARLY', 4,
        'true.water@gmail.com', '(093)226-86-34', 'м. Львів, вул. Городоцька 217', 'пн-пт 09.00-20.00', TRUE);

INSERT INTO provider (name, description, logo_url, periodicity, provider_type_id, email, phone, address, schedule, active)
VALUES ('Awesome', 'Прибирання під''їзду', 'http://www.sketchappsources.com/resources/source-image/pacman.jpg', 'PERMANENT_MONTHLY', 5,
        'aws@gmail.com', '(093)226-86-34', 'м. Львів, вул. Пасічна 62б', 'пн-пт 09.00-20.00', TRUE );

INSERT INTO provider (name, description, logo_url, periodicity, provider_type_id, email, phone, address, schedule, active)
VALUES ('Google inc', 'Інформаційна компанія', 'https://pbs.twimg.com/profile_images/762369348300251136/5Obhonwa.jpg', 'ONE_TIME', 6,
        'google@gmail.com', '(093)226-86-34', 'м. Львів, вул. Бенцаля 3', 'пн-пт 09.00-20.00', TRUE);


INSERT INTO contract ( date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2016-08-03', '2018-09-05', 100, 'UAH',  'Опис контракту', 1, TRUE);

INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2002-08-03', '2019-05-07', 630,  'UAH', 'Опис контракту', 2, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2007-08-03', '2018-09-05', 3200,  'UAH','Опис контракту', 3, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2010-03-03', '2018-10-02', 100,  'UAH', 'Опис контракту', 5, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2010-03-03', '2010-10-02', 150,  'UAH', 'Опис контракту', 6, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2010-03-03', '2021-10-02', 100,  'UAH', 'Опис контракту', 5, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2010-03-03', '2015-10-02', 98,  'UAH', 'Опис контракту', 4, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2010-03-03', '2012-10-02', 100,  'UAH', 'Опис контракту', 3, TRUE);


INSERT INTO contract (date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2010-03-03', '2015-10-02', 98,  'UAH', 'Опис контракту', 2, TRUE);

INSERT INTO contract ( date_start, date_finish, price, price_currency, text, provider_id, active)
VALUES ('2016-08-03', '2018-09-05', 100, 'UAH',  'Опис контракту', 1, TRUE);


/*Nazar's test data*/

insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-05-28', 350.5,0, 4.5, 2, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-05-28', 280.5,120, 4.5, 1, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-05-28', 320,320, 4.5, 3,  'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id)VALUES('2016-06-28', 240.5,0, 2.5, 1, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-06-28', 356,356, 4.5, 1, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-07-28', 240.5,0, 2, 3, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-07-28', 150, 0, 4.5, 2, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-07-28', 350.5,0, 4.5, 4, 'PAID', 1);
