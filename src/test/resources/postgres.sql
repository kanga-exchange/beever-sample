DROP TABLE IF EXISTS accounts;

CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    info VARCHAR
);

CREATE UNIQUE INDEX IF NOT EXISTS idx_accounts_1 ON accounts (username);

CREATE TABLE IF NOT EXISTS auth (
    account_id BIGINT,
    login VARCHAR,
    password VARCHAR
);

