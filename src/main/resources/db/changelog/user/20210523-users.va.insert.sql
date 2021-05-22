--liquibase formatted sql

--changeset akimov-ve:users_insert

INSERT INTO users (email, "password", "type", enabled)
VALUES ('lookfor@lookfor.ml', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'ADMIN', true),
       ('ivanov1@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'LANDLORD', true),
       ('ivanov2@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'LANDLORD', true),
       ('ivanov3@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'LANDLORD', true),
       ('ivanov4@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'LANDLORD', true),
       ('ivanov5@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'LANDLORD', true),
       ('ivanov6@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'LANDLORD', true),
       ('petrov@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'RENTER', true),
       ('sergeev@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'RENTER', true),
       ('makeev@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'RENTER', true),
       ('olegov@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'RENTER', true),
       ('semenov@gmail.com', '$2a$10$NmeLjIOVpbSfBusN9J/jLODcu71d3qHnMErVWzYK7SqM9nccTiUNm', 'RENTER', true)
;

--rollback DROP TABLE users;
