CREATE SCHEMA IF NOT EXISTS notification;

CREATE TABLE IF NOT EXISTS notification.notifications
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content    VARCHAR,
    created_at  TIMESTAMP NOT NULL
);