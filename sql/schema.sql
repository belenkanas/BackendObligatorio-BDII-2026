CREATE DATABASE IF NOT EXISTS obligatorio

USE obligatorio;

-- ============================================================
-- 1. PERFIL (superentidad de la especialización t,e)
--    Subtipos: Administrador, Funcionario, General
-- ============================================================
CREATE TABLE Perfil (
    id_perfil   INT            NOT NULL AUTO_INCREMENT,
    tipo        ENUM('Administrador','Funcionario','General') NOT NULL,
    PRIMARY KEY (id_perfil)
);

-- ============================================================
-- 2. USUARIO
--    Atributos propios + relación 1-N con Perfil (tiene)
-- ============================================================
CREATE TABLE Usuario (
    mail                    VARCHAR(255)  NOT NULL,  
    documento_tipo          VARCHAR(50)   NOT NULL,
    documento_numero        VARCHAR(50)   NOT NULL,
    direccion_pais          VARCHAR(100),
    direccion_localidad     VARCHAR(100),
    direccion_calle         VARCHAR(150),
    direccion_numero        VARCHAR(20),
    direccion_codigo_postal VARCHAR(20),
    telefono                VARCHAR(30),              
    id_perfil               INT           NOT NULL,
    PRIMARY KEY (mail),
    CONSTRAINT fk_usuario_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil (id_perfil)
);

-- ============================================================
-- 3. ADMINISTRADOR  (subtipo de Perfil - especialización t,e)
-- ============================================================
CREATE TABLE Administrador (
    id_perfil   INT         NOT NULL,
    mail_usuario VARCHAR(255) NOT NULL,             
    PRIMARY KEY (id_perfil),
    CONSTRAINT fk_admin_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil (id_perfil)
);

-- ============================================================
-- 4. FUNCIONARIO  (subtipo de Perfil)
-- ============================================================
CREATE TABLE Funcionario (
    id_perfil       INT          NOT NULL,
    nro_legajo      VARCHAR(50)  NOT NULL,           
    pais_sede       VARCHAR(100),
    fecha_asignado  DATE,
    PRIMARY KEY (id_perfil),
    CONSTRAINT fk_func_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil (id_perfil)
);

-- ============================================================
-- 5. GENERAL  (subtipo de Perfil)
--    No posee atributos propios en el MER
-- ============================================================
CREATE TABLE General (
    id_perfil   INT  NOT NULL,
    PRIMARY KEY (id_perfil),
    CONSTRAINT fk_general_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil (id_perfil)
);

-- ============================================================
-- 6. PAIS
-- ============================================================
CREATE TABLE Pais (
    nombre_pais VARCHAR(100) NOT NULL,   
    PRIMARY KEY (nombre_pais)
);

-- ============================================================
-- 7. ESTADIO
-- ============================================================
CREATE TABLE Estadio (
    nombre_estadio      VARCHAR(150) NOT NULL,   
    direccion   VARCHAR(255),
    pais        VARCHAR(100),
    ciudad      VARCHAR(100),
    PRIMARY KEY (nombre_estadio),
    CONSTRAINT fk_estadio_pais FOREIGN KEY (pais)
        REFERENCES Pais (nombre_pais)
);

-- ============================================================
-- 8. SECTOR  (tiene → 1 Estadio : N Sectores)
-- ============================================================
CREATE TABLE Sector (
    nombre_sector       VARCHAR(100) NOT NULL,    
    estadio_nombre      VARCHAR(150) NOT NULL,
    capacidad_max       INT,
    PRIMARY KEY (nombre_sector, estadio_nombre),
    CONSTRAINT fk_sector_estadio FOREIGN KEY (estadio_nombre)
        REFERENCES Estadio (nombre_estadio)
);

-- ============================================================
-- 9. EQUIPO
-- ============================================================
CREATE TABLE Equipo (
    nombre_pais VARCHAR(100) NOT NULL,   
    PRIMARY KEY (nombre_pais),
    CONSTRAINT fk_equipo_pais FOREIGN KEY (nombre_pais)
        REFERENCES Pais (nombre_pais)
);

-- ============================================================
-- 10. PARTIDO
--     Relaciones:
--       juega_local     → Equipo (1:N)
--       juega_visitante → Equipo (1:N)
--       ocurre          → Estadio (1:N)
-- ============================================================
CREATE TABLE Partido (
    fecha_hora              DATETIME     NOT NULL,   
    equipo_local            VARCHAR(100) NOT NULL,
    equipo_visitante        VARCHAR(100) NOT NULL,
    estadio_nombre          VARCHAR(150) NOT NULL,
    estadio_direccion       VARCHAR(255),
    PRIMARY KEY (fecha_hora),
    CONSTRAINT fk_partido_local      FOREIGN KEY (equipo_local)
        REFERENCES Equipo (nombre_pais),
    CONSTRAINT fk_partido_visitante  FOREIGN KEY (equipo_visitante)
        REFERENCES Equipo (nombre_pais),
    CONSTRAINT fk_partido_estadio    FOREIGN KEY (estadio_nombre)
        REFERENCES Estadio (nombre_estadio)
);

-- ============================================================
-- 11. EVENTO  (ocurre → Partido, tiene → Estadio)
--     nombre + direccion = PK compuesta (subrayados)
-- ============================================================
CREATE TABLE Evento (
    nombre              VARCHAR(150) NOT NULL,
    direccion           VARCHAR(255) NOT NULL,
    fecha_hora_evento   DATETIME,
    partido_fecha_hora  DATETIME     NOT NULL,       
    estadio_nombre      VARCHAR(150) NOT NULL,       
    estadio_direccion   VARCHAR(255),
    PRIMARY KEY (nombre, direccion),
    CONSTRAINT fk_evento_partido FOREIGN KEY (partido_fecha_hora)
        REFERENCES Partido (fecha_hora),
    CONSTRAINT fk_evento_estadio FOREIGN KEY (estadio_nombre)
        REFERENCES Estadio (nombre_estadio)
);

-- ============================================================
-- 12. SECTOR_EVENTO  (entidad débil: Sector + Evento)
--     Relaciones: tiene (Evento 1:N SectorEvento)
--                 corresponde (Sector 1:N SectorEvento)
--                 es (SectorEvento 1:1 SectorEvento — propia?)
--     Atributos: costo, fecha_hora_evento
-- ============================================================
CREATE TABLE SectorEvento (
    id_sector_evento    INT          NOT NULL AUTO_INCREMENT,
    evento_nombre       VARCHAR(150) NOT NULL,
    evento_direccion    VARCHAR(255) NOT NULL,
    sector_nombre       VARCHAR(100) NOT NULL,
    sector_estadio      VARCHAR(150) NOT NULL,
    costo               DECIMAL(10,2),
    fecha_hora_evento   DATETIME,
    PRIMARY KEY (id_sector_evento),
    CONSTRAINT fk_se_evento  FOREIGN KEY (evento_nombre, evento_direccion)
        REFERENCES Evento (nombre, direccion),
    CONSTRAINT fk_se_sector  FOREIGN KEY (sector_nombre, sector_estadio)
        REFERENCES Sector (nombre_sector, estadio_nombre)
);

-- ============================================================
-- 13. ENTRADA
--     Relaciones:
--       corresponde → SectorEvento (N:1)
--       es_propietario → Usuario   (N:1)
--       tiene → Token              (1:N)
-- ============================================================
CREATE TABLE Entrada (
    id                  INT          NOT NULL AUTO_INCREMENT,
    estado              VARCHAR(50),
    fecha_caducidad     DATE,
    id_sector_evento    INT          NOT NULL,
    usuario_mail        VARCHAR(255) NOT NULL,       -- es_propietario
    PRIMARY KEY (id),
    CONSTRAINT fk_entrada_se      FOREIGN KEY (id_sector_evento)
        REFERENCES SectorEvento (id_sector_evento),
    CONSTRAINT fk_entrada_usuario FOREIGN KEY (usuario_mail)
        REFERENCES Usuario (mail)
);

-- ============================================================
-- 14. TOKEN
--     Relaciones: tiene → Entrada (N:1)
-- ============================================================
CREATE TABLE Token (
    qr          VARCHAR(255) NOT NULL,    
    valido      TINYINT(1),               -- booleano "válido"
    id_entrada  INT          NOT NULL,
    PRIMARY KEY (qr),
    CONSTRAINT fk_token_entrada FOREIGN KEY (id_entrada)
        REFERENCES Entrada (id)
);

-- ============================================================
-- 15. DISPOSITIVO_ESCANEO
--     Relaciones:
--       tiene    → Funcionario (1:N) — a través de Perfil/Usuario
--       escanea  → Entrada     (1:N)
-- ============================================================
CREATE TABLE DispositivoEscaneo (
    id_dispositivo  INT  NOT NULL AUTO_INCREMENT,
    id_perfil_func  INT  NOT NULL,                  
    PRIMARY KEY (id_dispositivo),
    CONSTRAINT fk_disp_func FOREIGN KEY (id_perfil_func)
        REFERENCES Funcionario (id_perfil)
);

-- ============================================================
-- 16. VALIDACION  (resultado de escanea: DispositivoEscaneo → Entrada)
--     Registra cada evento de escaneo/validación
-- ============================================================
CREATE TABLE Validacion (
    id_validacion   INT      NOT NULL AUTO_INCREMENT,
    id_dispositivo  INT      NOT NULL,
    id_entrada      INT      NOT NULL,
    fecha_hora      DATETIME,
    PRIMARY KEY (id_validacion),
    CONSTRAINT fk_val_disp   FOREIGN KEY (id_dispositivo)
        REFERENCES DispositivoEscaneo (id_dispositivo),
    CONSTRAINT fk_val_entrada FOREIGN KEY (id_entrada)
        REFERENCES Entrada (id)
);

-- ============================================================
-- 17. VENTA
--     Relaciones:
--       realiza    → Usuario  (N:1)
--       corresponde → Entrada (1:N)
--     Atributos: fecha_hora, costoFinal, estado
-- ============================================================
CREATE TABLE Venta (
    id_venta        INT            NOT NULL AUTO_INCREMENT,
    fecha_hora      DATETIME,
    costo_final     DECIMAL(10,2),
    estado          VARCHAR(50),
    usuario_mail    VARCHAR(255)   NOT NULL,
    id_entrada      INT            NOT NULL,
    PRIMARY KEY (id_venta),
    CONSTRAINT fk_venta_usuario FOREIGN KEY (usuario_mail)
        REFERENCES Usuario (mail),
    CONSTRAINT fk_venta_entrada FOREIGN KEY (id_entrada)
        REFERENCES Entrada (id)
);

-- ============================================================
-- 18. TRANSF_ENTRADA  (TransfEntrada)
--     Relaciones:
--       realiza     → Usuario origen  (N:1)
--       recibe      → Usuario destino (N:1)
--       corresponde → Entrada         (N:1)
--     Atributos: fecha_hora, comisión, estado
-- ============================================================
CREATE TABLE TransfEntrada (
    id_transf           INT            NOT NULL AUTO_INCREMENT,
    fecha_hora          DATETIME,
    comision            DECIMAL(10,2),
    estado              VARCHAR(50),
    usuario_origen      VARCHAR(255)   NOT NULL,
    usuario_destino     VARCHAR(255)   NOT NULL,
    id_entrada          INT            NOT NULL,
    PRIMARY KEY (id_transf),
    CONSTRAINT fk_transf_origen  FOREIGN KEY (usuario_origen)
        REFERENCES Usuario (mail),
    CONSTRAINT fk_transf_destino FOREIGN KEY (usuario_destino)
        REFERENCES Usuario (mail),
    CONSTRAINT fk_transf_entrada FOREIGN KEY (id_entrada)
        REFERENCES Entrada (id)
);

-- ============================================================
-- 19. GESTION (relación N:N entre Administrador y Estadio)
--     "gestiona": Administrador gestiona Estadio
-- ============================================================
CREATE TABLE Gestion (
    id_perfil_admin INT          NOT NULL,
    estadio_nombre  VARCHAR(150) NOT NULL,
    PRIMARY KEY (id_perfil_admin, estadio_nombre),
    CONSTRAINT fk_gest_admin   FOREIGN KEY (id_perfil_admin)
        REFERENCES Administrador (id_perfil),
    CONSTRAINT fk_gest_estadio FOREIGN KEY (estadio_nombre)
        REFERENCES Estadio (nombre_estadio)
);

-- ============================================================
-- 20. ASIGNADO (relación N:N entre Funcionario y Partido)
--     "asignado": Funcionario asignado a Partido
-- ============================================================
CREATE TABLE Asignado (
    id_perfil_func      INT      NOT NULL,
    partido_fecha_hora  DATETIME NOT NULL,
    PRIMARY KEY (id_perfil_func, partido_fecha_hora),
    CONSTRAINT fk_asig_func    FOREIGN KEY (id_perfil_func)
        REFERENCES Funcionario (id_perfil),
    CONSTRAINT fk_asig_partido FOREIGN KEY (partido_fecha_hora)
        REFERENCES Partido (fecha_hora)
);

