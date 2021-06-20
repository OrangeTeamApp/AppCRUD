CREATE TABLE IF NOT EXISTS USERS
(
    `id`         int AUTO_INCREMENT NOT NULL,
    `email`      varchar(250)       NOT NULL,
    `first_name` varchar(50)        NOT NULL,
    `last_name`  varchar(50)        NOT NULL,
    `birth_day`  date               NOT NULL,
    PRIMARY KEY (`id`)
);



