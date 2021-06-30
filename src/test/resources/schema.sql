CREATE TABLE IF NOT EXISTS APP_USERS
(
    `id`
    int
    AUTO_INCREMENT
    NOT
    NULL,
    `email` varchar
(
    256
) NOT NULL,
    `first_name` varchar
(
    256
) NOT NULL,
    `last_name` varchar
(
    256
) NOT NULL,
    `birth_day` date NOT NULL,
    PRIMARY KEY
(
    `id`
)

    );



