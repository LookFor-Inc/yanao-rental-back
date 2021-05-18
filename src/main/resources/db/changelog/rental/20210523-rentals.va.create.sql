--liquibase formatted sql

--changeset akimov-ve:rentals_create

CREATE TABLE rentals (
    id     BIGSERIAL    NOT NULL,
    "name" VARCHAR(128) NOT NULL,
    town VARCHAR(128) NOT NULL,
    CONSTRAINT rentals_pkey PRIMARY KEY (id)
);

--rollback DROP TABLE rentals;
