--liquibase formatted sql

--changeset akimov-ve:users_info_insert

INSERT INTO users_info (avatar, first_name, last_name, middle_name, user_id)
VALUES ('http://localhost:8080/images/user/lookfor.png', 'LookFor', NULL, NULL, (SELECT id FROM users WHERE email = 'lookfor@lookfor.ml')),
       ('https://secure.gravatar.com/avatar/a50616b06c9f94b2c1aa4ccfc8f4c27b?s=800&d=identicon', 'Иван', 'Иванов', 'Иванович', (SELECT id FROM users WHERE email = 'ivanov@gmail.com')),
       ('https://secure.gravatar.com/avatar/a50616b06cqf94b2c1aa4ccfc8f4c27b?s=800&d=identicon', 'Пётр', 'Петров', 'Петрович', (SELECT id FROM users WHERE email = 'petrov@gmail.com')),
       ('https://secure.gravatar.com/avatar/b50616b06c9f94b2c17a4ccfc8f4c27b?s=800&d=identicon', 'Макей', 'Макеев', 'Макеевич', (SELECT id FROM users WHERE email = 'makeev@gmail.com')),
       ('https://secure.gravatar.com/avatar/a50616b06c9f94b2c1aa1ccfc8f4b27b?s=800&d=identicon', 'Олег', 'Олегов', 'Олегович', (SELECT id FROM users WHERE email = 'olegov@gmail.com'))
;

--rollback DROP TABLE users_info;
