CREATE TABLE IF NOT EXISTS patient_view(
    id varchar NOT NULL,
    name varchar NOT NULL,
    email varchar UNIQUE NOT NULL,
    address varchar NOT NULL,
    date_of_birth date NOT NULL,
    registered_date date NOT NULL,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);