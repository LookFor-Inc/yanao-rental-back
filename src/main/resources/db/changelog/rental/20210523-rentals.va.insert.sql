--liquibase formatted sql

--changeset akimov-ve:rentals_create

INSERT INTO rentals ("name", address)
VALUES ('Мотосалон STELS', 'Салехард'),
       ('Магазин "Идрис"', 'Салехард'),
       ('Мир спорта 24', 'Салехард'),
       ('Сервис Олега', 'Ноябрьск'),
       ('Прокат спортивного оборудования в НУ', 'Новый Уренгой'),
       ('Катай везде', 'Новый Уренгой')
;

--rollback DROP TABLE rentals;
