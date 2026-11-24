CREATE TABLE IF NOT EXISTS patient_accounts (
    account_id VARCHAR(64)  NOT NULL UNIQUE,
    patient_id VARCHAR(64)  NOT NULL,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    address    VARCHAR(500) NOT NULL,
    PRIMARY KEY (account_id)
);
