CREATE TABLE IF NOT EXISTS request_events (
    id          BIGINT      PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    ip_address  VARCHAR(32) NOT NULL,
    request_uri TEXT        NOT NULL,
    created_at  TIMESTAMP   NOT NULL
);
