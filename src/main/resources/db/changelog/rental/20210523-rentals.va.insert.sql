--liquibase formatted sql

--changeset akimov-ve:rentals_insert

INSERT INTO rentals ("name", address, img, owner_id, latitude, longitude)
VALUES ('Мотосалон STELS', 'г. Салехард', 'http://localhost:8080/images/uploads/stels.jpeg', (SELECT id FROM users WHERE email = 'ivanov1@gmail.com'), 66.530400, 66.624929),
       ('Магазин "Идрис"', 'г. Салехард', 'http://localhost:8080/images/uploads/idris.jpeg', (SELECT id FROM users WHERE email = 'ivanov2@gmail.com'), 66.559375, 66.563437),
       ('Мир спорта 24', 'г. Салехард', 'http://localhost:8080/images/uploads/sport-world.jpeg', (SELECT id FROM users WHERE email = 'ivanov3@gmail.com'), 66.561919, 66.576593),
       ('Сервис Олега', 'г. Ноябрьск', 'http://localhost:8080/images/uploads/oleg.jpeg', (SELECT id FROM users WHERE email = 'ivanov4@gmail.com'), 63.209750, 75.429582),
       ('Прокат спортивного оборудования в НУ', 'г. Новый Уренгой', 'http://localhost:8080/images/uploads/rental-sport.jpeg', (SELECT id FROM users WHERE email = 'ivanov5@gmail.com'), 66.087737, 76.676978),
       ('Катай везде', 'г. Новый Уренгой', 'http://localhost:8080/images/uploads/katay.jpeg', (SELECT id FROM users WHERE email = 'ivanov6@gmail.com'), 66.109950, 76.685027)
;
