--liquibase formatted sql

--changeset akimov-ve:equipment_types_insert

INSERT INTO equipment_types ("name", category_id, img)
VALUES ('Велосипед взрослый', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды'), 'http://localhost:8080/images/bike/adult.jpeg'),
       ('Велосипед детский', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды'), 'http://localhost:8080/images/bike/child.jpeg'),
       ('Велосипед шоссейный', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды'), 'http://localhost:8080/images/bike/highway.jpeg'),
       ('Велосипед горный', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды'), 'http://localhost:8080/images/bike/mountain.jpeg'),
       ('Велосипед электрический', (SELECT id FROM equipment_categories WHERE "name" = 'Велосипеды'), 'http://localhost:8080/images/bike/electric.jpeg'),
       ('Самокат взрослый', (SELECT id FROM equipment_categories WHERE "name" = 'Самокаты'), 'http://localhost:8080/images/scooter/adult.jpeg'),
       ('Самокат детский', (SELECT id FROM equipment_categories WHERE "name" = 'Самокаты'), 'http://localhost:8080/images/scooter/child.jpeg'),
       ('Самокат электрический', (SELECT id FROM equipment_categories WHERE "name" = 'Самокаты'), 'http://localhost:8080/images/scooter/electric.jpeg'),
       ('Ролики взрослые', (SELECT id FROM equipment_categories WHERE "name" = 'Ролики'), 'http://localhost:8080/images/roller-skates/adult.jpeg'),
       ('Ролики детские', (SELECT id FROM equipment_categories WHERE "name" = 'Ролики'), 'http://localhost:8080/images/roller-skates/child.jpeg'),
       ('Ролики электрические', (SELECT id FROM equipment_categories WHERE "name" = 'Ролики'), 'http://localhost:8080/images/roller-skates/electric.jpeg')
;
