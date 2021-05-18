--liquibase formatted sql

--changeset akimov-ve:equipment_categories_insert

INSERT INTO equipment_categories("name")
VALUES ('Велосипеды'),
       ('Самокаты'),
       ('Ролики')
;
