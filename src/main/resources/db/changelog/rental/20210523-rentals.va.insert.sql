--liquibase formatted sql

--changeset akimov-ve:rentals_insert

INSERT INTO rentals ("name", address, img, owner_id)
VALUES ('Мотосалон STELS', 'г. Салехард', 'http://localhost:8080/images/upload/stels.jpeg', (SELECT id FROM users WHERE email = 'ivanov1@gmail.com')),
       ('Магазин "Идрис"', 'г. Салехард', 'http://localhost:8080/images/upload/idris.jpeg', (SELECT id FROM users WHERE email = 'ivanov2@gmail.com')),
       ('Мир спорта 24', 'г. Салехард', 'http://localhost:8080/images/upload/sport-world.jpeg', (SELECT id FROM users WHERE email = 'ivanov3@gmail.com')),
       ('Сервис Олега', 'г. Ноябрьск', 'http://localhost:8080/images/upload/oleg.jpeg', (SELECT id FROM users WHERE email = 'ivanov4@gmail.com')),
       ('Прокат спортивного оборудования в НУ', 'г. Новый Уренгой', 'http://localhost:8080/images/upload/rental-sport.jpeg', (SELECT id FROM users WHERE email = 'ivanov5@gmail.com')),
       ('Катай везде', 'г. Новый Уренгой', 'http://localhost:8080/images/upload/katay.jpeg', (SELECT id FROM users WHERE email = 'ivanov6@gmail.com'))
;
