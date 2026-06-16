CREATE DATABASE IF NOT EXISTS obligatorio;

USE obligatorio;

-- =========================================  
-- TABLAS PRINCIPALES  
-- =========================================  
  
CREATE TABLE usuario (  
    mail VARCHAR(100) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,  
    documento_tipo VARCHAR(20),  
    documento_numero_doc VARCHAR(30),  
    direccion_calle VARCHAR(100),  
    direccion_numero VARCHAR(10),  
    direccion_codigo_postal VARCHAR(20),  
    direccion_pais VARCHAR(50),  
    direccion_localidad VARCHAR(50)  
);  
  
CREATE TABLE perfil (  
    id INT AUTO_INCREMENT PRIMARY KEY,  
    mail_usuario VARCHAR(100),
    FOREIGN KEY (mail_usuario) REFERENCES usuario(mail)  
);  
  
CREATE TABLE administrador (  
    id_administrador INT PRIMARY KEY,  
    fecha_asignado DATE,  
    pais_sede ENUM ('México', 'Estados Unidos', 'Canadá') NOT NULL,  
    FOREIGN KEY (id_administrador) REFERENCES perfil(id)  
);  
  
CREATE TABLE funcionario (  
    id_funcionario INT PRIMARY KEY,  
    nro_legajo VARCHAR(20) UNIQUE,  
    FOREIGN KEY (id_funcionario) REFERENCES perfil(id)  
);  
  
CREATE TABLE general (  
    id_general INT PRIMARY KEY,  
    estado_verificacion_id VARCHAR(50),  
    fecha_registro DATE,  
    FOREIGN KEY (id_general) REFERENCES perfil(id)  
);  
  
CREATE TABLE dispositivo_escaneo (  
    id INT AUTO_INCREMENT PRIMARY KEY,  
    nro_legajo VARCHAR(20)  
);  
  
CREATE TABLE equipo (  
    nombre_pais VARCHAR(50) PRIMARY KEY  
);  
  
CREATE TABLE partido (  
    fecha_hora TIMESTAMP PRIMARY KEY  
);  
  
CREATE TABLE estadio (  
    nombre VARCHAR(100),  
    direccion_pais VARCHAR(50),  
    direccion_ciudad VARCHAR(50),  
    PRIMARY KEY (nombre, direccion_pais, direccion_ciudad)  
);  
  
CREATE TABLE evento (  
    estadio_nombre VARCHAR(100),  
    estadio_direccion_pais VARCHAR(50),  
    estadio_direccion_ciudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
    nombre_pais_equipo_local VARCHAR(50),  
    nombre_pais_equipo_visitante VARCHAR(50),  
  
    PRIMARY KEY (  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad,  
        fecha_hora_partido,  
        nombre_pais_equipo_local,  
        nombre_pais_equipo_visitante  
    ),  
  
    FOREIGN KEY (estadio_nombre, estadio_direccion_pais, estadio_direccion_ciudad)  
        REFERENCES estadio(nombre, direccion_pais, direccion_ciudad),  
  
    FOREIGN KEY (fecha_hora_partido)  
        REFERENCES partido(fecha_hora),  
  
    FOREIGN KEY (nombre_pais_equipo_local)  
        REFERENCES equipo(nombre_pais),  
  
    FOREIGN KEY (nombre_pais_equipo_visitante)  
        REFERENCES equipo(nombre_pais)  
);  
  
CREATE TABLE sector (  
    nombre VARCHAR(50),  
    estadio_nombre VARCHAR(100),  
    estadio_direccion_pais VARCHAR(50),  
    estadio_direccion_ciudad VARCHAR(50),  
    capacidad_max INT,  
  
    PRIMARY KEY (  
        nombre,  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad  
    ),  
  
    FOREIGN KEY (estadio_nombre, estadio_direccion_pais, estadio_direccion_ciudad)  
        REFERENCES estadio(nombre, direccion_pais, direccion_ciudad)  
);  
  
CREATE TABLE sector_evento (  
    nombre_sector VARCHAR(50),  
    estadio_nombre VARCHAR(100),  
    estadio_direccion_pais VARCHAR(50),  
    estadio_direccion_ciudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
    costo DECIMAL(10,2),  
  
    PRIMARY KEY (  
        nombre_sector,  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad,  
        fecha_hora_partido  
    ),  
  
    FOREIGN KEY (  
        nombre_sector,  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad  
    )  
        REFERENCES sector(  
            nombre,  
            estadio_nombre,  
            estadio_direccion_pais,  
            estadio_direccion_ciudad  
        ),  
  
    FOREIGN KEY (fecha_hora_partido)  
        REFERENCES partido(fecha_hora)  
);  
  
CREATE TABLE venta (  
    id_venta INT AUTO_INCREMENT PRIMARY KEY,  
    fecha_hora TIMESTAMP,  
    comision DECIMAL(10,2),  
    costo_final DECIMAL(10,2),  
    estado VARCHAR(30) NOT NULL DEFAULT 'confirmada',
    id_general INT,  
  
    FOREIGN KEY (id_general)  
        REFERENCES general(id_general)  
);  
  
CREATE TABLE entrada (  
    id INT AUTO_INCREMENT PRIMARY KEY,  
    estado ENUM('activa', 
                'en_transferencia', '
                transferida', 
                'consumida', 
                'no_consumida') 
        NOT NULL DEFAULT 'activa'
    cant_transferida INT DEFAULT 0,
  
    nombre_sector VARCHAR(50),  
    estadio_nombre VARCHAR(100),  
    estadio_direccion_pais VARCHAR(50),  
    estadio_direccion_ciudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
  
    nombre_pais_equipo_local VARCHAR(50),  
    nombre_pais_equipo_visitante VARCHAR(50),  
  
    id_general_propietario INT,  
    id_venta INT,  
  
    FOREIGN KEY (  
        nombre_sector,  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad,  
        fecha_hora_partido  
    )  
        REFERENCES sector_evento(  
            nombre_sector,  
            estadio_nombre,  
            estadio_direccion_pais,  
            estadio_direccion_ciudad,  
            fecha_hora_partido  
        ),  
  
    FOREIGN KEY (id_general_propietario)  
        REFERENCES general(id_general),  
  
    FOREIGN KEY (id_venta)  
        REFERENCES venta(id_venta)  
);  
  
CREATE TABLE token (  
    qr VARCHAR(255) PRIMARY KEY,  
    valido BOOLEAN  
);  
  
CREATE TABLE transf_entrada (  
    fecha_hora TIMESTAMP,  
    estado ENUM 
        ('pendiente', 
        'aceptada', 
        'rechazada',
        'cancelada')
        NOT NULL DEFAULT 'pendiente',
    id_entrada INT,  
    id_general_realiza INT,  
    id_general_recibe INT,  
  
    PRIMARY KEY (fecha_hora, id_entrada),  
  
    FOREIGN KEY (id_entrada)  
        REFERENCES entrada(id),  
  
    FOREIGN KEY (id_general_realiza)  
        REFERENCES general(id_general),  
  
    FOREIGN KEY (id_general_recibe)  
        REFERENCES general(id_general)  
);  
  
CREATE TABLE validacion (  
    nro_legajo_funcionario VARCHAR(20),  
    id_dispositivo_escaneo INT,  
  
    PRIMARY KEY (nro_legajo_funcionario, id_dispositivo_escaneo),  
  
    FOREIGN KEY (id_dispositivo_escaneo)  
        REFERENCES dispositivo_escaneo(id)  
);  
  
-- =========================================  
-- TABLAS INTERMEDIAS  
-- =========================================  
  
CREATE TABLE telefonos_usuario (  
    mail_usuario VARCHAR(100),  
    telefono VARCHAR(20),  
  
    PRIMARY KEY (mail_usuario, telefono),  
  
    FOREIGN KEY (mail_usuario)  
        REFERENCES usuario(mail)  
);  
  
CREATE TABLE administrador_gestiona_evento (  
    id_administrador INT,  
  
    estadio_nombre VARCHAR(100),  
    estadio_direccion_pais VARCHAR(50),  
    estadio_direccion_ciudad VARCHAR(50),  
    fecha_hora_partido TIMESTAMP,  
    nombre_pais_equipo_local VARCHAR(50),  
    nombre_pais_equipo_visitante VARCHAR(50),  
  
    PRIMARY KEY (  
        id_administrador,  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad,  
        fecha_hora_partido,  
        nombre_pais_equipo_local,  
        nombre_pais_equipo_visitante  
    ),  
  
    FOREIGN KEY (id_administrador)  
        REFERENCES administrador(id_administrador),  
  
    FOREIGN KEY (  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad,  
        fecha_hora_partido,  
        nombre_pais_equipo_local,  
        nombre_pais_equipo_visitante  
    )  
        REFERENCES evento(  
            estadio_nombre,  
            estadio_direccion_pais,  
            estadio_direccion_ciudad,  
            fecha_hora_partido,  
            nombre_pais_equipo_local,  
            nombre_pais_equipo_visitante  
        )  
);  
  
CREATE TABLE entrada_tiene_token (  
    qr VARCHAR(255),  
    id_entrada INT,  
    fecha_caducidad TIMESTAMP,  
  
    PRIMARY KEY (qr, id_entrada),  
  
    FOREIGN KEY (qr)  
        REFERENCES token(qr),  
  
    FOREIGN KEY (id_entrada)  
        REFERENCES entrada(id)  
);  
  
CREATE TABLE funcionario_asignado_a_sector (  
    nro_legajo VARCHAR(20),  
    nombre_sector VARCHAR(50),  
    estadio_nombre VARCHAR(100),  
    estadio_direccion_pais VARCHAR(50),  
    estadio_direccion_ciudad VARCHAR(50),  
  
    PRIMARY KEY (  
        nro_legajo,  
        nombre_sector,  
        estadio_nombre,  
        estadio_direccion_pais,  
        estadio_direccion_ciudad  
    )  
);  
  
CREATE TABLE token_escaneado_valido (  
    nro_legajo_funcionario VARCHAR(20),  
    id_dispositivo_escaneo INT,  
    fecha_validacion TIMESTAMP,  
    qr_token VARCHAR(255),  
  
    PRIMARY KEY (  
        nro_legajo_funcionario,  
        id_dispositivo_escaneo,  
        fecha_validacion,  
        qr_token  
    ),  
  
    FOREIGN KEY (qr_token)  
        REFERENCES token(qr),  
  
    FOREIGN KEY (  
        nro_legajo_funcionario,  
        id_dispositivo_escaneo  
    )  
        REFERENCES validacion(  
            nro_legajo_funcionario,  
            id_dispositivo_escaneo  
        )  
);