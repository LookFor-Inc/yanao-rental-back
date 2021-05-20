--liquibase formatted sql

--changeset akimov-ve:rentals_create

INSERT INTO rentals ("name", address)
VALUES ('Мотосалон STELS', 'г. Салехард'),
       ('Магазин "Идрис"', 'г. Салехард'),
       ('Мир спорта 24', 'г. Салехард'),
       ('Сервис Олега', 'г. Ноябрьск'),
       ('Прокат спортивного оборудования в НУ', 'г. Новый Уренгой'),
       ('Катай везде', 'г. Новый Уренгой')
;

--rollback DROP TABLE rentals;
