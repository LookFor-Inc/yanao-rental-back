--liquibase formatted sql

--changeset akimov-ve:rentals_create

CREATE TABLE rentals (
    id        BIGSERIAL    NOT NULL,
    "name"    VARCHAR(128) NOT NULL,
    address   VARCHAR(512) NOT NULL,
    img       VARCHAR(128) NULL,
    latitude  FLOAT8 NULL,
    longitude FLOAT8 NULL,
    CONSTRAINT rentals_pkey PRIMARY KEY (id)
);

--rollback DROP TABLE rentals;
