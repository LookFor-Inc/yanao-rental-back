--liquibase formatted sql

--changeset akimov-ve:equipments_create

CREATE TABLE equipments (
    id            BIGSERIAL NOT NULL,
    type_id       BIGSERIAL NOT NULL,
    rental_id     BIGSERIAL NOT NULL,
    CONSTRAINT equipments_pkey PRIMARY KEY (id),
    CONSTRAINT equipments_equipment_types_fkey FOREIGN KEY (type_id) REFERENCES equipment_types (id),
    CONSTRAINT equipments_rentals_fkey FOREIGN KEY (rental_id) REFERENCES rentals (id)
);

--rollback DROP TABLE equipments;
