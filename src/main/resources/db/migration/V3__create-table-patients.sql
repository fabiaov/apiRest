CREATE TABLE patients (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(100) NOT NULL,
                           email VARCHAR(100) NOT NULL UNIQUE,
                           cpf VARCHAR(14) NOT NULL UNIQUE,
                           telephone VARCHAR(20) NOT NULL,
                           publicPlace VARCHAR(100) NOT NULL,
                           neighborhood VARCHAR(100) NOT NULL,
                           zipCode VARCHAR(9) NOT NULL,
                           complement VARCHAR(100),
                           number VARCHAR(20),
                           uf CHAR(2) NOT NULL,
                           city VARCHAR(100) NOT NULL
);
