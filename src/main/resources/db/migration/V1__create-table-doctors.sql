CREATE TABLE doctors (
                         id BIGSERIAL PRIMARY KEY, -- Substitui auto_increment do MySQL
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         crm VARCHAR(6) NOT NULL UNIQUE,
                         specialty VARCHAR(100) NOT NULL,
                         publicPlace VARCHAR(100) NOT NULL,
                         neighborhood VARCHAR(100) NOT NULL,
                         zipCode VARCHAR(9) NOT NULL,
                         complement VARCHAR(100),
                         number VARCHAR(20),
                         uf CHAR(2) NOT NULL,
                         city VARCHAR(100) NOT NULL
);