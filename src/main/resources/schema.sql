
CREATE TABLE IF NOT EXISTS users (
    id         BIGINT       PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    username   VARCHAR(64)  NOT NULL UNIQUE,
    email      VARCHAR(128) NOT NULL UNIQUE,
    first_name VARCHAR(64)  NOT NULL,
    last_name  VARCHAR(64)  NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    id   BIGINT      PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS transfers (
    id          BIGINT         PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_id     BIGINT         NOT NULL REFERENCES users (id),
    amount      NUMERIC(17, 2) NOT NULL,
    is_income   BOOLEAN        NOT NULL,
    description VARCHAR(128)   NOT NULL,
    category_id INTEGER        NOT NULL REFERENCES categories (id),
    created_at  TIMESTAMP      NOT NULL
);
