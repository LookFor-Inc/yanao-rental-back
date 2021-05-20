--liquibase formatted sql

--changeset akimov-ve:equipment_categories_create

CREATE TABLE equipment_categories (
    id     BIGSERIAL    NOT NULL,
    "name" VARCHAR(128) NOT NULL,
    img    VARCHAR(128) NOT NULL,
    CONSTRAINT equipment_categories_pkey PRIMARY KEY (id)
);

--rollback DROP TABLE equipment_categories;
