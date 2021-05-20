--liquibase formatted sql

--changeset akimov-ve:equipments_insert

INSERT INTO equipments(type_id, rental_id)
VALUES ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед шоссейный'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Магазин "Идрис"')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Магазин "Идрис"')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед шоссейный'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед горный'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат электрический'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики взрослые'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики детские'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед шоссейный'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед горный'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед электрический'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат взрослый'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат электрический'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики взрослые'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики детские'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед горный'), (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики взрослые'), (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Катай везде')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Катай везде')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Катай везде')),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат электрический'), (SELECT id FROM rentals WHERE "name" = 'Катай везде'))
;
