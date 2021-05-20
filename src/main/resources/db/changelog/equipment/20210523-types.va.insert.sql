--liquibase formatted sql

--changeset akimov-ve:equipment_types_insert

INSERT INTO equipment_types ("name", category_id)
VALUES ('Велосипед взрослый', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды')),
       ('Велосипед детский', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды')),
       ('Велосипед шоссейный', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды')),
       ('Велосипед горный', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды')),
       ('Велосипед электрический', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды')),
       ('Самокат взрослый', (SELECT id FROM equipment_categories WHERE "name" = 'Самокаты')),
       ('Самокат детский', (SELECT id FROM equipment_categories WHERE "name" = 'Самокаты')),
       ('Самокат электрический', (SELECT id FROM equipment_categories WHERE "name" = 'Самокаты')),
       ('Ролики взрослые', (SELECT id FROM equipment_categories WHERE "name" = 'Ролики')),
       ('Ролики детские', (SELECT id FROM equipment_categories WHERE "name" = 'Ролики'))
;
