--liquibase formatted sql

--changeset akimov-ve:rentals_create

INSERT INTO rentals ("name", address, img)
VALUES ('Мотосалон STELS', 'г. Салехард', 'http://localhost:8080/images/upload/stels.jpeg'),
       ('Магазин "Идрис"', 'г. Салехард', 'http://localhost:8080/images/upload/idris.jpeg'),
       ('Мир спорта 24', 'г. Салехард', 'http://localhost:8080/images/upload/sport-world.jpeg'),
       ('Сервис Олега', 'г. Ноябрьск', 'http://localhost:8080/images/upload/oleg.jpeg'),
       ('Прокат спортивного оборудования в НУ', 'г. Новый Уренгой', 'http://localhost:8080/images/upload/rental-sport.jpeg'),
       ('Катай везде', 'г. Новый Уренгой', 'http://localhost:8080/images/upload/katay.jpeg')
;

--rollback DROP TABLE rentals;
