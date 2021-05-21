--liquibase formatted sql

--changeset akimov-ve:rentals_insert

INSERT INTO rentals ("name", address, img, owner_id)
VALUES ('Мотосалон STELS', 'г. Салехард', 'http://localhost:8080/images/uploads/stels.jpeg', (SELECT id FROM users WHERE email = 'ivanov1@gmail.com')),
       ('Магазин "Идрис"', 'г. Салехард', 'http://localhost:8080/images/uploads/idris.jpeg', (SELECT id FROM users WHERE email = 'ivanov2@gmail.com')),
       ('Мир спорта 24', 'г. Салехард', 'http://localhost:8080/images/uploads/sport-world.jpeg', (SELECT id FROM users WHERE email = 'ivanov3@gmail.com')),
       ('Сервис Олега', 'г. Ноябрьск', 'http://localhost:8080/images/uploads/oleg.jpeg', (SELECT id FROM users WHERE email = 'ivanov4@gmail.com')),
       ('Прокат спортивного оборудования в НУ', 'г. Новый Уренгой', 'http://localhost:8080/images/uploads/rental-sport.jpeg', (SELECT id FROM users WHERE email = 'ivanov5@gmail.com')),
       ('Катай везде', 'г. Новый Уренгой', 'http://localhost:8080/images/uploads/katay.jpeg', (SELECT id FROM users WHERE email = 'ivanov6@gmail.com'))
;
