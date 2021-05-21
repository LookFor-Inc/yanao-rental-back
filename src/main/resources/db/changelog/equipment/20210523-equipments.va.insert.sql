--liquibase formatted sql

--changeset akimov-ve:equipments_insert

INSERT INTO equipments(type_id, rental_id, "name", img, total_count, free_count)
VALUES ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS'), 'Велосипед Trek', 'http://localhost:8080/images/bike/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS'), 'Велосипед Flover', 'http://localhost:8080/images/bike/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед шоссейный'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS'), 'Велосипед Pinarello', 'http://localhost:8080/images/bike/highway.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS'), 'Самокат Oxelo', 'http://localhost:8080/images/scooter/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Мотосалон STELS'), 'Самокат Pituso', 'http://localhost:8080/images/scooter/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Магазин "Идрис"'), 'Велосипед Trek', 'http://localhost:8080/images/bike/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Магазин "Идрис"'), 'Велосипед Flover', 'http://localhost:8080/images/bike/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Велосипед Trek', 'http://localhost:8080/images/bike/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Велосипед Flover', 'http://localhost:8080/images/bike/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед шоссейный'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Велосипед Pinarello', 'http://localhost:8080/images/bike/highway.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед горный'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Велосипед Boxxer', 'http://localhost:8080/images/bike/mountain.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат взрослый'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Самокат Oxelo', 'http://localhost:8080/images/scooter/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Самокат Pituso', 'http://localhost:8080/images/scooter/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат электрический'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Самокат Ninebot', 'http://localhost:8080/images/scooter/electric.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики взрослые'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Ролики Стандарт', 'http://localhost:8080/images/roller-skates/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики детские'), (SELECT id FROM rentals WHERE "name" = 'Мир спорта 24'), 'Ролики Стандарт', 'http://localhost:8080/images/roller-skates/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Велосипед Trek', 'http://localhost:8080/images/bike/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Велосипед Flover', 'http://localhost:8080/images/bike/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед шоссейный'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Велосипед Pinarello', 'http://localhost:8080/images/bike/highway.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед горный'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Велосипед Boxxer', 'http://localhost:8080/images/bike/mountain.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед электрический'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Велосипед Townie', 'http://localhost:8080/images/bike/electric.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат взрослый'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Самокат Oxelo', 'http://localhost:8080/images/scooter/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Самокат Pituso', 'http://localhost:8080/images/scooter/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат электрический'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Самокат Ninebot', 'http://localhost:8080/images/scooter/electric.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики взрослые'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Ролики Стандарт', 'http://localhost:8080/images/roller-skates/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики детские'), (SELECT id FROM rentals WHERE "name" = 'Сервис Олега'), 'Ролики Стандарт', 'http://localhost:8080/images/roller-skates/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ'), 'Велосипед Trek', 'http://localhost:8080/images/bike/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед горный'), (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ'), 'Велосипед Boxxer', 'http://localhost:8080/images/bike/mountain.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Ролики взрослые'), (SELECT id FROM rentals WHERE "name" = 'Прокат спортивного оборудования в НУ'), 'Ролики Стандарт', 'http://localhost:8080/images/roller-skates/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед взрослый'), (SELECT id FROM rentals WHERE "name" = 'Катай везде'), 'Велосипед Trek', 'http://localhost:8080/images/bike/adult.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Велосипед детский'), (SELECT id FROM rentals WHERE "name" = 'Катай везде'), 'Велосипед Flover', 'http://localhost:8080/images/bike/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат детский'), (SELECT id FROM rentals WHERE "name" = 'Катай везде'), 'Самокат Pituso', 'http://localhost:8080/images/scooter/child.jpeg', 3, 2),
       ((SELECT id FROM equipment_types WHERE "name" = 'Самокат электрический'), (SELECT id FROM rentals WHERE "name" = 'Катай везде'), 'Самокат Ninebot', 'http://localhost:8080/images/scooter/electric.jpeg', 3, 2)
;
