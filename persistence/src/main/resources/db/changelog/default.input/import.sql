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


INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('2016-06-13','admin@admin','Admin','admin','Adminius','$2a$06$IeFQ1f0OMzPvfrwMvUrvqO6ghg.Qayu57t6nRAhgY9Ic18muPHGAC','380000000','2',1,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1993-10-31','vilumkris@gmail.com','kris','female','vilum','$2a$06$IeFQ1f0OMzPvfrwMvUrvqO6ghg.Qayu57t6nRAhgY9Ic18muPHGAC','380931286864','1',2,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1990-11-28', 'n.svidersky@gmail.com','Nazar','male','Sviderskyi','$2a$06$fGiJsjD0U/ZoHbmjab2ytOoedYAB1zQ0XANiGUQXAwv9TcyxV.Qfe','0931544845','1',3,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1996-02-21','KostetskyRoma@gmail.com','Roman','male','Kostetsky','$2a$06$R33Bzp5v3k5l5xs1n3dwQuQ/fM1DjCMldqthEXhvnI7Cu3gOQ4ms6','380687773508','1',4,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1992-12-30','butaroman@gmail.com','Roman','male','Buta','$2a$06$IeFQ1f0OMzPvfrwMvUrvqO6ghg.Qayu57t6nRAhgY9Ic18muPHGAC','0632571119','1',5,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1997-06-02','cavayman@gmail.com','Oleg','male','Kotsik','$2a$06$IeFQ1f0OMzPvfrwMvUrvqO6ghg.Qayu57t6nRAhgY9Ic18muPHGAC','0679167305','1',6,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('2916-04-14','nazardovhyi@gmail.com','Nazar','male','Dovhyi','$2a$06$FHyRBL.Yc54J8K1XoKPF9.wWzyThXZd/6kNBLY8BxyoaGUb.YtFjG','0973055976','1',7,TRUE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1994-12-16','oleg111koval@gmail.com','Oleg','male','Koval','$2a$06$QfIksIErYqbeoD3Pnxai7Ott22NGF8G38GsC/pQVpGG/rt55IYhUy','095729666','1',8,TRUE );


INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1994-12-16','svitlanaKoval@gmail.com','Svitlana','male','Koval','123456','095729666','1',8,FALSE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1994-12-16','oleg2koval@gmail.com','Ruslan','male','Koval','123456','095729666','1',8,FALSE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1994-12-16','oleg111koval@gmail.com','Sergiy','male','Koval','123456','095729666','1',8,FALSE );
INSERT INTO users(  birth_date,email, first_name, gender, last_name, password,phone_number,role,apartment_id,is_owner) VALUES ('1994-12-16','oleg111koval@gmail.com','Andriy','male','Koval','123456','095729666','1',8,FALSE );


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

insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-05-28', 350.5,0, 4.5, 7, 1, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-05-28', 280.5,120, 4.5, 1, 1, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-05-28', 320,320, 4.5, 3,  'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status)VALUES('2016-06-28', 240.5,0, 2.5, 2, 1, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-06-28', 356,356, 4.5, 7, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status)VALUES('2016-06-28', 60,0, 2, 8, 5, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-06-28', 120,120, 0.5, 6, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status)VALUES('2016-06-28', 88,88, 1.5, 6, 6, 'PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-06-28', 356,356, 4.5, 7, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-07-28', 240.5,0, 2, 3, 2, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-07-28', 150, 0, 4.5, 7, 3, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-07-28', 188,50, 3, 5, 4, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-07-28', 120, 120, 4.5, 7, 4, 'PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-07-28', 350.5,350.5, 4.5, 4, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-08-28', 90,0, 1, 1, 4, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-08-28', 144.5, 144.5, 2, 2, 6, 'PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-08-28', 199, 0, 2, 3, 3, 'NOT_PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id, status) VALUES('2016-08-28', 120, 120, 4.5, 6, 6, 'PAID');
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-08-28', 150.5,150.5, 4, 7, 'PAID', 1);



INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Мій Дім','осбб для людей','м.Львів вул.Городоцька 147а','Залізничний','2016-09-01 10:46:43.221000','http://itukraine.org.ua/sites/default/files/news/sserve.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Червона Калина','найкраще осбб','м.Львів вул.В.Великого 99','Франківський','2014-09-05 10:46:43.221220','http://ua-ekonomist.com/uploads/posts/2013-08/1376494055_932378712.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Двір','рівні права','м.Львів вул.Б.Хмельницького 77','Франківський','2012-05-27 08:20:43.221000','http://news.dks.ua/images/0915/i_08091517_1.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Район','осбб','м.Львів вул.Сихівськаа 33','Сихів','2015-10-15 10:46:43.221000','http://www.abmk.com.ua/content/images/projects/138047835156_orig.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Надія','файне осбб','м.Львів вул.Гната Хоткевича 47а','Сихів','2010-08-01 10:46:43.221000','http://nomerodyn.com/public/img/big/main.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Весна',' ','м.Львів вул.Проспект Червоної Калини 11','Сихів','2016-05-10 12:46:43.221000','http://archfest.com/web/images/139479223222_1.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Мрія',' ','м.Львів вул.Городоцька 147а','Залізничний','2016-09-01 10:46:43.221000','https://www.askideas.com/media/39/Awesome-Empire-State-Building-Picture.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Моя Оселя','','м.Львів вул.Повітряна 17б','Залізничний','2013-10-12 08:46:43.221340','http://img1.globalinfo.ua/im/2014/02/12/TZA04E.jpg');
INSERT INTO osbb(  name, description, address, district, creation_date, logo_url) VALUES ('Сонечко','','м.Львів вул.Наукова 77','Франківський','2015-04-18 10:46:43.221270','http://kyiv.ridna.ua/wp-content/uploads/2015/06/Project2_0014.jpg');

UPDATE house SET osbb_id=1 WHERE house_id=1;

insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id)VALUES('2016-06-28', 240.5,0, 2.5, 1, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-06-28', 356,356, 4.5, 1, 'PAID', 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-07-28', 240.5,0, 2, 3, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, provider_id) VALUES('2016-07-28', 150, 0, 4.5, 2, 1);
insert into bill(date, to_pay, paid, tariff, apartment_id, status, provider_id) VALUES('2016-07-28', 350.5,0, 4.5, 4, 'PAID', 1);


INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,3,'Назва тікету1','Опис тікету1','NEW','2016-09-02','2016-12-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,5,'Назва тікету2','Опис тікету2','DONE','2016-10-02','2016-12-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,2,'Назва тікету3','Опис тікету3','NEW','2016-11-02','2016-11-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,2,'Назва тікету4','Опис тікету4','DONE','2016-04-02','2016-05-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,3,'Назва тікету5','Опис тікету5','NEW','2016-03-02','2016-05-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,3,'Назва тікету6','Опис тікету6','DONE','2016-01-02','2016-02-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,4,'Назва тікету7','Опис тікету7','NEW','2016-11-02','2016-12-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,5,'Назва тікету8','Опис тікету8','IN_PROGRESS','2016-08-02','2016-09-12');
INSERT into ticket(assigned,user_id,name,description,state,"time",state_time)
           VALUES(1,2,'Назва тікету9','Опис тікету9','NEW','2016-10-02','2016-12-12');

