--liquibase formatted sql

--changeset akimov-ve:equipment_types_create

CREATE TABLE equipment_types (
    id          BIGSERIAL    NOT NULL,
    "name"      VARCHAR(128) NOT NULL,
    category_id BIGSERIAL    NOT NULL,
    CONSTRAINT equipment_types_pkey PRIMARY KEY (id),
    CONSTRAINT equipment_types_equipment_categories_fkey FOREIGN KEY (category_id) REFERENCES equipment_categories (id)
);

--rollback DROP TABLE equipment_types;
