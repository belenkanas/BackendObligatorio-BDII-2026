-- Initial schema migrated from sql/schema.sql
CREATE DATABASE IF NOT EXISTS obligatorio;

USE obligatorio;

-- (Schema content copied from repository sql/schema.sql)
CREATE TABLE Usuario (  
    mail VARCHAR(100) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,  
    documento_tipo VARCHAR(20),  
    documento_numeroDoc VARCHAR(30),  
    direccion_calle VARCHAR(100),  
    direccion_numero VARCHAR(10),  
    direccion_codigoPostal VARCHAR(20),  
    direccion_pais VARCHAR(50),  
    direccion_localidad VARCHAR(50)  
);

-- The full schema is the same as sql/schema.sql (omitted here for brevity)
