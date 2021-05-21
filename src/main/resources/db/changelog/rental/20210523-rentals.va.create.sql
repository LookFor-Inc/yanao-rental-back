--liquibase formatted sql

--changeset akimov-ve:rentals_create

CREATE TABLE rentals (
    id        BIGSERIAL    NOT NULL,
    "name"    VARCHAR(128) NOT NULL,
    address   VARCHAR(512) NOT NULL,
    img       VARCHAR(128) NULL,
    owner_id  BIGSERIAL    NOT NULL,
    latitude  FLOAT8 NULL,
    longitude FLOAT8 NULL,
    CONSTRAINT rentals_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (owner_id) REFERENCES users (id)
);

--rollback DROP TABLE rentals;
