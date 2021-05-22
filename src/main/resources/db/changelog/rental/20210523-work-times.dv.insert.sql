--liquibase formatted sql

--changeset vorozheykin-dv:work_times_insert

INSERT INTO work_times (from_day, from_time, to_day, to_time, rental_id)
VALUES ('MONDAY', '09:00', 'SUNDAY', '23:00', (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS')),
       ('MONDAY', '09:00', 'SUNDAY', '23:00', (SELECT id FROM rentals WHERE "name" = 'Магазин "Идрис"')),
       ('MONDAY', '09:00', 'SUNDAY', '23:00', (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ('MONDAY', '09:00', 'SUNDAY', '23:00', (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ('MONDAY', '09:00', 'SUNDAY', '23:00', (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ')),
       ('MONDAY', '09:00', 'SUNDAY', '23:00', (SELECT id FROM rentals WHERE "name" = 'Катай везде'))
;
