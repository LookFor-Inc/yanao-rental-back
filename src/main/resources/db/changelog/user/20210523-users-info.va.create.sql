--liquibase formatted sql

--changeset akimov-ve:users_info_create

CREATE TABLE users_info (
    id                BIGSERIAL NOT NULL,
    avatar            VARCHAR(255) NULL,
    first_name        VARCHAR(20) NULL,
    last_name         VARCHAR(20) NULL,
    middle_name       VARCHAR(20) NULL,
    registration_date DATE NULL,
    user_id           BIGSERIAL NOT NULL,
    CONSTRAINT users_info_pkey PRIMARY KEY (id),
    CONSTRAINT users_fkey FOREIGN KEY (user_id) REFERENCES users (id)
);

--rollback DROP TABLE users_info;
