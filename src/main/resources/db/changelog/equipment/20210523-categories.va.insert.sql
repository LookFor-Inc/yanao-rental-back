--liquibase formatted sql

--changeset akimov-ve:equipment_categories_insert

INSERT INTO equipment_categories("name", img)
VALUES ('Велосипеды', 'http://localhost:8080/images/bike/main.jpeg'),
       ('Самокаты', 'http://localhost:8080/images/scooter/main.jpeg'),
       ('Ролики', 'http://localhost:8080/images/roller-skates/main.jpeg')
;
