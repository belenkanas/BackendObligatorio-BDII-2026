CREATE DATABASE IF NOT EXISTS obligatorio;

USE obligatorio;

-- =========================================  
-- TABLAS PRINCIPALES  
-- =========================================  
  
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
  
CREATE TABLE Perfil (  
    id INT PRIMARY KEY,  
    mailUsuario VARCHAR(100),
    contrasena VARCHAR(255) NOT NULL,  
    FOREIGN KEY (mailUsuario) REFERENCES Usuario(mail)  
);  
  
CREATE TABLE Administrador (  
    id_administrador INT PRIMARY KEY,  
    fecha_asignado DATE,  
    paisSede VARCHAR(50),  
    FOREIGN KEY (id_administrador) REFERENCES Perfil(id)  
);  
  
CREATE TABLE Funcionario (  
    id_funcionario INT PRIMARY KEY,  
    nroLegajo VARCHAR(20) UNIQUE,  
    FOREIGN KEY (id_funcionario) REFERENCES Perfil(id)  
);  
  
CREATE TABLE General (  
    id_general INT PRIMARY KEY,  
    estado_verificacion_id VARCHAR(50),  
    fecha_registro DATE,  
    FOREIGN KEY (id_general) REFERENCES Perfil(id)  
);  
  
CREATE TABLE DispositivoEscaneo (  
    id INT PRIMARY KEY,  
    nrolegajo VARCHAR(20)  
);  
  
CREATE TABLE Equipo (  
    nombrePais VARCHAR(50) PRIMARY KEY  
);  
  
CREATE TABLE Partido (  
    fecha_hora TIMESTAMP PRIMARY KEY  
);  
  
CREATE TABLE Estadio (  
    nombre VARCHAR(100),  
    direccion_pais VARCHAR(50),  
    direccion_ciudad VARCHAR(50),  
    PRIMARY KEY (nombre, direccion_pais, direccion_ciudad)  
);  
  
CREATE TABLE Evento (  
    estadioNombre VARCHAR(100),  
    estadioDireccionPais VARCHAR(50),  
    estadioDireccionCiudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
    nombrePais_equipoLocal VARCHAR(50),  
    nombrePais_equipoVisitante VARCHAR(50),  
  
    PRIMARY KEY (  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad,  
        fecha_hora_partido,  
        nombrePais_equipoLocal,  
        nombrePais_equipoVisitante  
    ),  
  
    FOREIGN KEY (estadioNombre, estadioDireccionPais, estadioDireccionCiudad)  
        REFERENCES Estadio(nombre, direccion_pais, direccion_ciudad),  
  
    FOREIGN KEY (fecha_hora_partido)  
        REFERENCES Partido(fecha_hora),  
  
    FOREIGN KEY (nombrePais_equipoLocal)  
        REFERENCES Equipo(nombrePais),  
  
    FOREIGN KEY (nombrePais_equipoVisitante)  
        REFERENCES Equipo(nombrePais)  
);  
  
CREATE TABLE Sector (  
    nombre VARCHAR(50),  
    estadioNombre VARCHAR(100),  
    estadioDireccionPais VARCHAR(50),  
    estadioDireccionCiudad VARCHAR(50),  
    capacidadMax INT,  
  
    PRIMARY KEY (  
        nombre,  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad  
    ),  
  
    FOREIGN KEY (estadioNombre, estadioDireccionPais, estadioDireccionCiudad)  
        REFERENCES Estadio(nombre, direccion_pais, direccion_ciudad)  
);  
  
CREATE TABLE SectorEvento (  
    nombre_sector VARCHAR(50),  
    estadioNombre VARCHAR(100),  
    estadioDireccionPais VARCHAR(50),  
    estadioDireccionCiudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
    costo DECIMAL(10,2),  
  
    PRIMARY KEY (  
        nombre_sector,  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad,  
        fecha_hora_partido  
    ),  
  
    FOREIGN KEY (  
        nombre_sector,  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad  
    )  
        REFERENCES Sector(  
            nombre,  
            estadioNombre,  
            estadioDireccionPais,  
            estadioDireccionCiudad  
        ),  
  
    FOREIGN KEY (fecha_hora_partido)  
        REFERENCES Partido(fecha_hora)  
);  
  
CREATE TABLE Venta (  
    id_venta INT PRIMARY KEY,  
    fecha_hora TIMESTAMP,  
    comision DECIMAL(10,2),  
    costoFinal DECIMAL(10,2),  
    estado VARCHAR(30),  
    id_general INT,  
  
    FOREIGN KEY (id_general)  
        REFERENCES General(id_general)  
);  
  
CREATE TABLE Entrada (  
    id INT PRIMARY KEY,  
    estado VARCHAR(30),  
  
    nombre_sector VARCHAR(50),  
    estadioNombre VARCHAR(100),  
    estadioDireccionPais VARCHAR(50),  
    estadioDireccionCiudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
  
    nombrePais_equipoLocal VARCHAR(50),  
    nombrePais_equipoVisitante VARCHAR(50),  
  
    id_general_propietario INT,  
    id_venta INT,  
  
    FOREIGN KEY (  
        nombre_sector,  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad,  
        fecha_hora_partido  
    )  
        REFERENCES SectorEvento(  
            nombre_sector,  
            estadioNombre,  
            estadioDireccionPais,  
            estadioDireccionCiudad,  
            fecha_hora_partido  
        ),  
  
    FOREIGN KEY (id_general_propietario)  
        REFERENCES General(id_general),  
  
    FOREIGN KEY (id_venta)  
        REFERENCES Venta(id_venta)  
);  
  
CREATE TABLE Token (  
    qr VARCHAR(255) PRIMARY KEY,  
    valido BOOLEAN  
);  
  
CREATE TABLE TransfEntrada (  
    fecha_hora TIMESTAMP,  
    estado VARCHAR(30),  
    id_entrada INT,  
    id_general_realiza INT,  
    id_general_recibe INT,  
  
    PRIMARY KEY (fecha_hora, id_entrada),  
  
    FOREIGN KEY (id_entrada)  
        REFERENCES Entrada(id),  
  
    FOREIGN KEY (id_general_realiza)  
        REFERENCES General(id_general),  
  
    FOREIGN KEY (id_general_recibe)  
        REFERENCES General(id_general)  
);  
  
CREATE TABLE Validacion (  
    nroLegajo_funcionario VARCHAR(20),  
    id_dispositivoEscaneo INT,  
  
    PRIMARY KEY (nroLegajo_funcionario, id_dispositivoEscaneo),  
  
    FOREIGN KEY (id_dispositivoEscaneo)  
        REFERENCES DispositivoEscaneo(id)  
);  
  
-- =========================================  
-- TABLAS INTERMEDIAS  
-- =========================================  
  
CREATE TABLE Telefonos_Usuario (  
    mail_usuario VARCHAR(100),  
    telefono VARCHAR(20),  
  
    PRIMARY KEY (mail_usuario, telefono),  
  
    FOREIGN KEY (mail_usuario)  
        REFERENCES Usuario(mail)  
);  
  
CREATE TABLE Administrador_gestiona_Evento (  
    id_administrador INT,  
  
    estadioNombre VARCHAR(100),  
    estadioDireccionPais VARCHAR(50),  
    estadioDireccionCiudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
    nombrePais_equipoLocal VARCHAR(50),  
    nombrePais_equipoVisitante VARCHAR(50),  
  
    PRIMARY KEY (  
        id_administrador,  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad,  
        fecha_hora_partido,  
        nombrePais_equipoLocal,  
        nombrePais_equipoVisitante  
    ),  
  
    FOREIGN KEY (id_administrador)  
        REFERENCES Administrador(id_administrador),  
  
    FOREIGN KEY (  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad,  
        fecha_hora_partido,  
        nombrePais_equipoLocal,  
        nombrePais_equipoVisitante  
    )  
        REFERENCES Evento(  
            estadioNombre,  
            estadioDireccionPais,  
            estadioDireccionCiudad,  
            fecha_hora_partido,  
            nombrePais_equipoLocal,  
            nombrePais_equipoVisitante  
        )  
);  
  
CREATE TABLE Entrada_tiene_Token (  
    qr VARCHAR(255),  
    id_entrada INT,  
    fecha_caducidad TIMESTAMP,  
  
    PRIMARY KEY (qr, id_entrada),  
  
    FOREIGN KEY (qr)  
        REFERENCES Token(qr),  
  
    FOREIGN KEY (id_entrada)  
        REFERENCES Entrada(id)  
);  
  
CREATE TABLE Funcionario_asignadoA_Sector (  
    nroLegajo VARCHAR(20),  
    nombre_sector VARCHAR(50),  
    estadioNombre VARCHAR(100),  
    estadioDireccionPais VARCHAR(50),  
    estadioDireccionCiudad VARCHAR(50),  
  
    PRIMARY KEY (  
        nroLegajo,  
        nombre_sector,  
        estadioNombre,  
        estadioDireccionPais,  
        estadioDireccionCiudad  
    )  
);  
  
CREATE TABLE Token_escaneado_Valido (  
    nroLegajo_funcionario VARCHAR(20),  
    id_dispositivoEscaneo INT,  
    fecha_validacion TIMESTAMP,  
    qr_token VARCHAR(255),  
  
    PRIMARY KEY (  
        nroLegajo_funcionario,  
        id_dispositivoEscaneo,  
        fecha_validacion,  
        qr_token  
    ),  
  
    FOREIGN KEY (qr_token)  
        REFERENCES Token(qr),  
  
    FOREIGN KEY (  
        nroLegajo_funcionario,  
        id_dispositivoEscaneo  
    )  
        REFERENCES Validacion(  
            nroLegajo_funcionario,  
            id_dispositivoEscaneo  
        )  
);  
