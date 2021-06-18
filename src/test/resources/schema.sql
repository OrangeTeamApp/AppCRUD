CREATE SCHEMA scm
    CREATE TABLE IF NOT EXISTS USERS (
    id         SERIAL              NOT NULL,
    email      varchar(250) UNIQUE NOT NULL,
    first_name varchar(50)         NOT NULL,
    last_name  varchar(50)         NOT NULL,
    birth_day  TIMESTAMP           NOT NULL,
    PRIMARY KEY (id),
);



