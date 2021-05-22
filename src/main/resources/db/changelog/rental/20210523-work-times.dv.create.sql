--liquibase formatted sql

--changeset vorozheykin-dv:work_times_create

CREATE TABLE work_times (
    id        BIGSERIAL    NOT NULL,
    from_day  VARCHAR(255) NOT NULL,
    from_time TIME         NOT NULL,
    to_day    VARCHAR(255) NOT NULL,
    to_time   TIME         NOT NULL,
    rental_id INT8         NOT NULL,
    CONSTRAINT work_times_pkey PRIMARY KEY (id),
    CONSTRAINT rentals_fkey FOREIGN KEY (rental_id) REFERENCES rentals (id)
);


--rollback DROP TABLE work_times;
