--liquibase formatted sql

--changeset akimov-ve:users_create

CREATE TABLE users (
    id         BIGSERIAL    NOT NULL,
    email      VARCHAR(255) NOT NULL,
    "password" VARCHAR(255) NULL,
    "type"     VARCHAR(16) NOT NULL,
    enabled    BOOL         NOT NULL,
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

--rollback DROP TABLE users;
