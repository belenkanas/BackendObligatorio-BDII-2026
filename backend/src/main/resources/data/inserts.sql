-- Se agregan los países y estadios que formarán parte del mundial 2026,
-- con la idea de que no se tengan que agregar a mano cada vez que se quiera probar la aplicación,
-- ni que se tenga que agregar un país o estadio que no formará parte del mundial.

-- Con la misma idea, se agregan los partidos reales, con sus fechas y estadios correspondientes.

-- De forma adicional, se agregan usuarios administradores por defecto, para facilitar el proceso de pruebas.
-- Esto se debe también a que para evitar la irresponsable creación de usuarios administradores, estos mismos son creados por el sistema, no por usuarios externos.

INSERT INTO equipo (nombre_pais) VALUES 
('Canadá'),('México'),('Estados Unidos'),('Australia'),
('Irán'),('Japón'),('Jordania'),('Corea del Sur'),
('Catar'),('Arabia Saudí'),('Uzbekistán'),('Irak'),
('Argelia'),('Cabo Verde'),('Costa de Marfil'),('Egipto'),
('Ghana'),('Marruecos'),('Senegal'),('Sudáfrica'),
('Túnez'),('Congo'),('Curazao'),('Haití'),('Panamá'),
('Argentina'),('Brasil'),('Colombia'),('Ecuador'),
('Paraguay'),('Uruguay'),('Nueva Zelanda'),('Austria'),
('Bélgica'),('Bosnia y Herzegovina'),('Croacia'),('Chequia'),
('Inglaterra'),('Francia'),('Alemania'),('Países Bajos'),
('Noruega'),('Portugal'),('Escocia'),('España'),
('Suecia'),('Suiza'),('Turquía')
ON DUPLICATE KEY UPDATE nombre_pais=nombre_pais;

INSERT INTO estadio (nombre, direccion_pais, direccion_ciudad) VALUES 
('Estadio Azteca','México','Ciudad de México'),
('Estadio Akron','México','Guadalajara'),
('Estadio BBVA','México','Monterrey'),
('BMO Field','Canadá','Toronto'),
('BC Place','Canadá','Vancouver'),
('Mercedes-Benz Stadium','Estados Unidos','Atlanta'),
('Gillette Stadium','Estados Unidos','Foxborough'),
('AT&T Stadium','Estados Unidos','Arlington'),
('NRG Stadium','Estados Unidos','Houston'),
('Arrowhead Stadium','Estados Unidos','Kansas City'),
('SoFi Stadium','Estados Unidos','Los Ángeles'),
('Hard Rock Stadium','Estados Unidos','Miami'),
('MetLife Stadium','Estados Unidos','East Rutherford'),
('Lincoln Financial Field','Estados Unidos','Filadelfia'),
('Levis Stadium','Estados Unidos','San Francisco'),
('Lumen Field','Estados Unidos','Seattle')
ON DUPLICATE KEY UPDATE nombre=nombre;

-- Sectores por estadio
INSERT INTO sector (nombre, estadio_nombre, estadio_direccion_pais, estadio_direccion_ciudad, capacidad_max) VALUES
-- México
('Palco',          'Estadio Azteca', 'México', 'Ciudad de México', 100),
('Tribuna Norte',  'Estadio Azteca', 'México', 'Ciudad de México', 500),
('Tribuna Sur',    'Estadio Azteca', 'México', 'Ciudad de México', 500),
('General',        'Estadio Azteca', 'México', 'Ciudad de México', 1000),
 
('Palco',          'Estadio Akron',  'México', 'Guadalajara', 80),
('Tribuna Norte',  'Estadio Akron',  'México', 'Guadalajara', 400),
('Tribuna Sur',    'Estadio Akron',  'México', 'Guadalajara', 400),
('General',        'Estadio Akron',  'México', 'Guadalajara', 800),
 
('Palco',          'Estadio BBVA',   'México', 'Monterrey', 80),
('Tribuna Norte',  'Estadio BBVA',   'México', 'Monterrey', 400),
('Tribuna Sur',    'Estadio BBVA',   'México', 'Monterrey', 400),
('General',        'Estadio BBVA',   'México', 'Monterrey', 800),
 
-- Canadá
('Palco',          'BMO Field',      'Canadá', 'Toronto', 80),
('Tribuna Norte',  'BMO Field',      'Canadá', 'Toronto', 350),
('Tribuna Sur',    'BMO Field',      'Canadá', 'Toronto', 350),
('General',        'BMO Field',      'Canadá', 'Toronto', 700),
 
('Palco',          'BC Place',       'Canadá', 'Vancouver', 80),
('Tribuna Norte',  'BC Place',       'Canadá', 'Vancouver', 350),
('Tribuna Sur',    'BC Place',       'Canadá', 'Vancouver', 350),
('General',        'BC Place',       'Canadá', 'Vancouver', 700),
 
-- Estados Unidos
('Palco',          'Mercedes-Benz Stadium',    'Estados Unidos', 'Atlanta',         100),
('Tribuna Norte',  'Mercedes-Benz Stadium',    'Estados Unidos', 'Atlanta',         500),
('Tribuna Sur',    'Mercedes-Benz Stadium',    'Estados Unidos', 'Atlanta',         500),
('General',        'Mercedes-Benz Stadium',    'Estados Unidos', 'Atlanta',        1000),
 
('Palco',          'Gillette Stadium',         'Estados Unidos', 'Foxborough',       80),
('Tribuna Norte',  'Gillette Stadium',         'Estados Unidos', 'Foxborough',      400),
('Tribuna Sur',    'Gillette Stadium',         'Estados Unidos', 'Foxborough',      400),
('General',        'Gillette Stadium',         'Estados Unidos', 'Foxborough',      800),
 
('Palco',          'AT&T Stadium',             'Estados Unidos', 'Arlington',       100),
('Tribuna Norte',  'AT&T Stadium',             'Estados Unidos', 'Arlington',       500),
('Tribuna Sur',    'AT&T Stadium',             'Estados Unidos', 'Arlington',       500),
('General',        'AT&T Stadium',             'Estados Unidos', 'Arlington',      1000),
 
('Palco',          'NRG Stadium',              'Estados Unidos', 'Houston',         100),
('Tribuna Norte',  'NRG Stadium',              'Estados Unidos', 'Houston',         500),
('Tribuna Sur',    'NRG Stadium',              'Estados Unidos', 'Houston',         500),
('General',        'NRG Stadium',              'Estados Unidos', 'Houston',        1000),
 
('Palco',          'Arrowhead Stadium',        'Estados Unidos', 'Kansas City',      80),
('Tribuna Norte',  'Arrowhead Stadium',        'Estados Unidos', 'Kansas City',     400),
('Tribuna Sur',    'Arrowhead Stadium',        'Estados Unidos', 'Kansas City',     400),
('General',        'Arrowhead Stadium',        'Estados Unidos', 'Kansas City',     800),
 
('Palco',          'SoFi Stadium',             'Estados Unidos', 'Los Ángeles',     100),
('Tribuna Norte',  'SoFi Stadium',             'Estados Unidos', 'Los Ángeles',     500),
('Tribuna Sur',    'SoFi Stadium',             'Estados Unidos', 'Los Ángeles',     500),
('General',        'SoFi Stadium',             'Estados Unidos', 'Los Ángeles',    1000),
 
('Palco',          'Hard Rock Stadium',        'Estados Unidos', 'Miami',            80),
('Tribuna Norte',  'Hard Rock Stadium',        'Estados Unidos', 'Miami',           400),
('Tribuna Sur',    'Hard Rock Stadium',        'Estados Unidos', 'Miami',           400),
('General',        'Hard Rock Stadium',        'Estados Unidos', 'Miami',           800),
 
('Palco',          'MetLife Stadium',          'Estados Unidos', 'East Rutherford', 100),
('Tribuna Norte',  'MetLife Stadium',          'Estados Unidos', 'East Rutherford', 500),
('Tribuna Sur',    'MetLife Stadium',          'Estados Unidos', 'East Rutherford', 500),
('General',        'MetLife Stadium',          'Estados Unidos', 'East Rutherford',1000),
 
('Palco',          'Lincoln Financial Field',  'Estados Unidos', 'Filadelfia',       80),
('Tribuna Norte',  'Lincoln Financial Field',  'Estados Unidos', 'Filadelfia',      400),
('Tribuna Sur',    'Lincoln Financial Field',  'Estados Unidos', 'Filadelfia',      400),
('General',        'Lincoln Financial Field',  'Estados Unidos', 'Filadelfia',      800),
 
('Palco',          'Levis Stadium',            'Estados Unidos', 'San Francisco',    80),
('Tribuna Norte',  'Levis Stadium',            'Estados Unidos', 'San Francisco',   400),
('Tribuna Sur',    'Levis Stadium',            'Estados Unidos', 'San Francisco',   400),
('General',        'Levis Stadium',            'Estados Unidos', 'San Francisco',   800),
 
('Palco',          'Lumen Field',              'Estados Unidos', 'Seattle',          80),
('Tribuna Norte',  'Lumen Field',              'Estados Unidos', 'Seattle',         400),
('Tribuna Sur',    'Lumen Field',              'Estados Unidos', 'Seattle',         400),
('General',        'Lumen Field',              'Estados Unidos', 'Seattle',         800)
 ON DUPLICATE KEY UPDATE capacidad_max=capacidad_max;
 
-- PARTIDOS: se agregan los partidos con sus fechas y horarios en zona horaria de Uruguay (UTC-3).
-- Los horarios corresponden a los indicados en el calendario oficial del Mundial 2026.

INSERT INTO partido (fecha_hora) VALUES
-- Jornada 1
('2026-06-11 16:00:00'), -- México vs Sudáfrica
('2026-06-11 23:00:00'), -- Corea del Sur vs Chequia
('2026-06-12 16:00:00'), -- Canadá vs Bosnia y Herzegovina
('2026-06-12 22:00:00'), -- Estados Unidos vs Paraguay
('2026-06-13 16:00:00'), -- Catar vs Suiza
('2026-06-13 19:00:00'), -- Brasil vs Marruecos
('2026-06-13 22:00:00'), -- Haití vs Escocia
('2026-06-14 01:00:00'), -- Australia vs Turquía
('2026-06-14 14:00:00'), -- Alemania vs Curazao
('2026-06-14 17:00:00'), -- Países Bajos vs Japón
('2026-06-14 20:00:00'), -- Costa de Marfil vs Ecuador
('2026-06-14 23:00:00'), -- Suecia vs Túnez
('2026-06-15 13:00:00'), -- España vs Cabo Verde
('2026-06-15 16:00:00'), -- Bélgica vs Egipto
('2026-06-15 19:00:00'), -- Arabia Saudí vs Uruguay
('2026-06-15 22:00:00'), -- Irán vs Nueva Zelanda
('2026-06-16 16:00:00'), -- Francia vs Senegal
('2026-06-16 19:00:00'), -- Irak vs Noruega
('2026-06-16 22:00:00'), -- Argentina vs Argelia
('2026-06-17 01:00:00'), -- Austria vs Jordania
('2026-06-17 14:00:00'), -- Portugal vs Congo
('2026-06-17 17:00:00'), -- Inglaterra vs Croacia
('2026-06-17 20:00:00'), -- Ghana vs Panamá
('2026-06-17 23:00:00'), -- Uzbekistán vs Colombia
-- Jornada 2
('2026-06-18 13:00:00'), -- Chequia vs Sudáfrica
('2026-06-18 16:00:00'), -- Suiza vs Bosnia y Herzegovina
('2026-06-18 19:00:00'), -- Canadá vs Catar
('2026-06-18 22:00:00'), -- México vs Corea del Sur
('2026-06-19 16:00:00'), -- Estados Unidos vs Australia
('2026-06-19 19:00:00'), -- Escocia vs Marruecos
('2026-06-19 21:30:00'), -- Brasil vs Haití
('2026-06-20 00:00:00'), -- Turquía vs Paraguay
('2026-06-20 14:00:00'), -- Países Bajos vs Suecia
('2026-06-20 17:00:00'), -- Alemania vs Costa de Marfil
('2026-06-20 21:00:00'), -- Ecuador vs Curazao
('2026-06-21 01:00:00'), -- Japón vs Túnez
('2026-06-21 13:00:00'), -- España vs Arabia Saudita
('2026-06-21 16:00:00'), -- Bélgica vs Irán
('2026-06-21 19:00:00'), -- Uruguay vs Cabo Verde
('2026-06-21 22:00:00'), -- Nueva Zelanda vs Egipto
('2026-06-22 14:00:00'), -- Argentina vs Austria
('2026-06-22 18:00:00'), -- Francia vs Irak
('2026-06-22 21:00:00'), -- Noruega vs Senegal
('2026-06-23 00:00:00'), -- Jordania vs Argelia
('2026-06-23 14:00:00'), -- Portugal vs Uzbekistán
('2026-06-23 17:00:00'), -- Inglaterra vs Ghana
('2026-06-23 20:00:00'), -- Panamá vs Croacia
('2026-06-23 23:00:00'), -- Colombia vs RD Congo
-- Jornada 3 (partidos paralelos)
('2026-06-24 16:00:00'), -- Suiza vs Canadá
('2026-06-24 16:00:00'), -- Bosnia y Herzegovina vs Catar
('2026-06-24 19:00:00'), -- Marruecos vs Haití
('2026-06-24 19:00:00'), -- Brasil vs Escocia
('2026-06-24 22:00:00'), -- Sudáfrica vs Corea del Sur
('2026-06-24 22:00:00'), -- República Checa vs México
('2026-06-25 17:00:00'), -- Curazao vs Costa de Marfil
('2026-06-25 17:00:00'), -- Ecuador vs Alemania
('2026-06-25 20:00:00'), -- Japón vs Suecia
('2026-06-25 20:00:00'), -- Túnez vs Países Bajos
('2026-06-25 23:00:00'), -- Paraguay vs Australia
('2026-06-25 23:00:00'), -- Turquía vs Estados Unidos
('2026-06-26 16:00:00'), -- Noruega vs Francia
('2026-06-26 16:00:00'), -- Senegal vs Irak
('2026-06-26 21:00:00'), -- Cabo Verde vs Arabia Saudita
('2026-06-26 21:00:00'), -- Uruguay vs España
('2026-06-27 00:00:00'), -- Egipto vs Irán
('2026-06-27 00:00:00'), -- Nueva Zelanda vs Bélgica
('2026-06-27 18:00:00'), -- Croacia vs Ghana
('2026-06-27 18:00:00'), -- Panamá vs Inglaterra
('2026-06-27 20:30:00'), -- Colombia vs Portugal
('2026-06-27 20:30:00'), -- RD Congo vs Uzbekistán
('2026-06-27 23:00:00'), -- Argelia vs Austria
('2026-06-27 23:00:00')  -- Jordania vs Argentina
ON DUPLICATE KEY UPDATE fecha_hora = fecha_hora;


-- EVENTOS

INSERT INTO evento (
    estadio_nombre, estadio_direccion_pais, estadio_direccion_ciudad,
    fecha_hora_partido, nombre_pais_equipo_local, nombre_pais_equipo_visitante
) VALUES
-- Jornada 1
('Estadio Azteca',          'México',         'Ciudad de México', '2026-06-11 16:00:00', 'México',          'Sudáfrica'),
('Estadio Akron',           'México',         'Guadalajara',      '2026-06-11 23:00:00', 'Corea del Sur',   'Chequia'),
('BMO Field',               'Canadá',         'Toronto',          '2026-06-12 16:00:00', 'Canadá',          'Bosnia y Herzegovina'),
('SoFi Stadium',            'Estados Unidos', 'Los Ángeles',      '2026-06-12 22:00:00', 'Estados Unidos',  'Paraguay'),
('Levis Stadium',           'Estados Unidos', 'San Francisco',    '2026-06-13 16:00:00', 'Catar',           'Suiza'),
('MetLife Stadium',         'Estados Unidos', 'East Rutherford',  '2026-06-13 19:00:00', 'Brasil',          'Marruecos'),
('Gillette Stadium',        'Estados Unidos', 'Foxborough',       '2026-06-13 22:00:00', 'Haití',           'Escocia'),
('BC Place',                'Canadá',         'Vancouver',        '2026-06-14 01:00:00', 'Australia',       'Turquía'),
('NRG Stadium',             'Estados Unidos', 'Houston',          '2026-06-14 14:00:00', 'Alemania',        'Curazao'),
('AT&T Stadium',            'Estados Unidos', 'Arlington',        '2026-06-14 17:00:00', 'Países Bajos',    'Japón'),
('Lincoln Financial Field', 'Estados Unidos', 'Filadelfia',       '2026-06-14 20:00:00', 'Costa de Marfil', 'Ecuador'),
('Estadio BBVA',            'México',         'Monterrey',        '2026-06-14 23:00:00', 'Suecia',          'Túnez'),
('Mercedes-Benz Stadium',   'Estados Unidos', 'Atlanta',          '2026-06-15 13:00:00', 'España',          'Cabo Verde'),
('Lumen Field',             'Estados Unidos', 'Seattle',          '2026-06-15 16:00:00', 'Bélgica',         'Egipto'),
('Hard Rock Stadium',       'Estados Unidos', 'Miami',            '2026-06-15 19:00:00', 'Arabia Saudí',    'Uruguay'),
('SoFi Stadium',            'Estados Unidos', 'Los Ángeles',      '2026-06-15 22:00:00', 'Irán',            'Nueva Zelanda'),
('MetLife Stadium',         'Estados Unidos', 'East Rutherford',  '2026-06-16 16:00:00', 'Francia',         'Senegal'),
('Gillette Stadium',        'Estados Unidos', 'Foxborough',       '2026-06-16 19:00:00', 'Irak',            'Noruega'),
('Arrowhead Stadium',       'Estados Unidos', 'Kansas City',      '2026-06-16 22:00:00', 'Argentina',       'Argelia'),
('Levis Stadium',           'Estados Unidos', 'San Francisco',    '2026-06-17 01:00:00', 'Austria',         'Jordania'),
('NRG Stadium',             'Estados Unidos', 'Houston',          '2026-06-17 14:00:00', 'Portugal',        'Congo'),
('AT&T Stadium',            'Estados Unidos', 'Arlington',        '2026-06-17 17:00:00', 'Inglaterra',      'Croacia'),
('BMO Field',               'Canadá',         'Toronto',          '2026-06-17 20:00:00', 'Ghana',           'Panamá'),
('Estadio Azteca',          'México',         'Ciudad de México', '2026-06-17 23:00:00', 'Uzbekistán',      'Colombia'),
-- Jornada 2
('Mercedes-Benz Stadium',   'Estados Unidos', 'Atlanta',          '2026-06-18 13:00:00', 'Chequia',         'Sudáfrica'),
('SoFi Stadium',            'Estados Unidos', 'Los Ángeles',      '2026-06-18 16:00:00', 'Suiza',           'Bosnia y Herzegovina'),
('BC Place',                'Canadá',         'Vancouver',        '2026-06-18 19:00:00', 'Canadá',          'Catar'),
('Estadio Akron',           'México',         'Guadalajara',      '2026-06-18 22:00:00', 'México',          'Corea del Sur'),
('Lumen Field',             'Estados Unidos', 'Seattle',          '2026-06-19 16:00:00', 'Estados Unidos',  'Australia'),
('Gillette Stadium',        'Estados Unidos', 'Foxborough',       '2026-06-19 19:00:00', 'Escocia',         'Marruecos'),
('Lincoln Financial Field', 'Estados Unidos', 'Filadelfia',       '2026-06-19 21:30:00', 'Brasil',          'Haití'),
('Levis Stadium',           'Estados Unidos', 'San Francisco',    '2026-06-20 00:00:00', 'Turquía',         'Paraguay'),
('NRG Stadium',             'Estados Unidos', 'Houston',          '2026-06-20 14:00:00', 'Países Bajos',    'Suecia'),
('BMO Field',               'Canadá',         'Toronto',          '2026-06-20 17:00:00', 'Alemania',        'Costa de Marfil'),
('MetLife Stadium',         'Estados Unidos', 'East Rutherford',  '2026-06-20 21:00:00', 'Ecuador',         'Curazao'),
('Estadio BBVA',            'México',         'Monterrey',        '2026-06-21 01:00:00', 'Japón',           'Túnez'),
('Mercedes-Benz Stadium',   'Estados Unidos', 'Atlanta',          '2026-06-21 13:00:00', 'España',          'Arabia Saudita'),
('Lumen Field',             'Estados Unidos', 'Seattle',          '2026-06-21 16:00:00', 'Bélgica',         'Irán'),
('Hard Rock Stadium',       'Estados Unidos', 'Miami',            '2026-06-21 19:00:00', 'Uruguay',         'Cabo Verde'),
('BC Place',                'Canadá',         'Vancouver',        '2026-06-21 22:00:00', 'Nueva Zelanda',   'Egipto'),
('AT&T Stadium',            'Estados Unidos', 'Arlington',        '2026-06-22 14:00:00', 'Argentina',       'Austria'),
('Lincoln Financial Field', 'Estados Unidos', 'Filadelfia',       '2026-06-22 18:00:00', 'Francia',         'Irak'),
('MetLife Stadium',         'Estados Unidos', 'East Rutherford',  '2026-06-22 21:00:00', 'Noruega',         'Senegal'),
('Levis Stadium',           'Estados Unidos', 'San Francisco',    '2026-06-23 00:00:00', 'Jordania',        'Argelia'),
('NRG Stadium',             'Estados Unidos', 'Houston',          '2026-06-23 14:00:00', 'Portugal',        'Uzbekistán'),
('Gillette Stadium',        'Estados Unidos', 'Foxborough',       '2026-06-23 17:00:00', 'Inglaterra',      'Ghana'),
('BMO Field',               'Canadá',         'Toronto',          '2026-06-23 20:00:00', 'Panamá',          'Croacia'),
('Estadio Azteca',          'México',         'Ciudad de México', '2026-06-23 23:00:00', 'Colombia',        'RD Congo'),
-- Jornada 3 (partidos paralelos)
('BC Place',                'Canadá',         'Vancouver',        '2026-06-24 16:00:00', 'Suiza',           'Canadá'),
('Lumen Field',             'Estados Unidos', 'Seattle',          '2026-06-24 16:00:00', 'Bosnia y Herzegovina', 'Catar'),
('Hard Rock Stadium',       'Estados Unidos', 'Miami',            '2026-06-24 19:00:00', 'Brasil',          'Escocia'),
('Mercedes-Benz Stadium',   'Estados Unidos', 'Atlanta',          '2026-06-24 19:00:00', 'Marruecos',       'Haití'),
('Estadio Akron',           'México',         'Guadalajara',      '2026-06-24 22:00:00', 'Sudáfrica',       'Corea del Sur'),
('Estadio Azteca',          'México',         'Ciudad de México', '2026-06-24 22:00:00', 'República Checa', 'México'),
('Lincoln Financial Field', 'Estados Unidos', 'Filadelfia',       '2026-06-25 17:00:00', 'Curazao',         'Costa de Marfil'),
('MetLife Stadium',         'Estados Unidos', 'East Rutherford',  '2026-06-25 17:00:00', 'Ecuador',         'Alemania'),
('AT&T Stadium',            'Estados Unidos', 'Arlington',        '2026-06-25 20:00:00', 'Japón',           'Suecia'),
('Arrowhead Stadium',       'Estados Unidos', 'Kansas City',      '2026-06-25 20:00:00', 'Túnez',           'Países Bajos'),
('Levis Stadium',           'Estados Unidos', 'San Francisco',    '2026-06-25 23:00:00', 'Paraguay',        'Australia'),
('SoFi Stadium',            'Estados Unidos', 'Los Ángeles',      '2026-06-25 23:00:00', 'Turquía',         'Estados Unidos'),
('Gillette Stadium',        'Estados Unidos', 'Foxborough',       '2026-06-26 16:00:00', 'Noruega',         'Francia'),
('BMO Field',               'Canadá',         'Toronto',          '2026-06-26 16:00:00', 'Senegal',         'Irak'),
('NRG Stadium',             'Estados Unidos', 'Houston',          '2026-06-26 21:00:00', 'Cabo Verde',      'Arabia Saudita'),
('Estadio Akron',           'México',         'Guadalajara',      '2026-06-26 21:00:00', 'Uruguay',         'España'),
('Lumen Field',             'Estados Unidos', 'Seattle',          '2026-06-27 00:00:00', 'Egipto',          'Irán'),
('BC Place',                'Canadá',         'Vancouver',        '2026-06-27 00:00:00', 'Nueva Zelanda',   'Bélgica'),
('Lincoln Financial Field', 'Estados Unidos', 'Filadelfia',       '2026-06-27 18:00:00', 'Croacia',         'Ghana'),
('MetLife Stadium',         'Estados Unidos', 'East Rutherford',  '2026-06-27 18:00:00', 'Panamá',          'Inglaterra'),
('Hard Rock Stadium',       'Estados Unidos', 'Miami',            '2026-06-27 20:30:00', 'Colombia',        'Portugal'),
('Mercedes-Benz Stadium',   'Estados Unidos', 'Atlanta',          '2026-06-27 20:30:00', 'RD Congo',        'Uzbekistán'),
('Arrowhead Stadium',       'Estados Unidos', 'Kansas City',      '2026-06-27 23:00:00', 'Argelia',         'Austria'),
('AT&T Stadium',            'Estados Unidos', 'Arlington',        '2026-06-27 23:00:00', 'Jordania',        'Argentina')
ON DUPLICATE KEY UPDATE fecha_hora_partido = VALUES(fecha_hora_partido);

-- SECTOR_EVENTO
-- Precios por categoría para cada partido

 
INSERT INTO sector_evento (
    nombre_sector, estadio_nombre, estadio_direccion_pais, estadio_direccion_ciudad,
    fecha_hora_partido, costo
)
SELECT s.nombre, e.estadio_nombre, e.estadio_direccion_pais, e.estadio_direccion_ciudad,
       e.fecha_hora_partido,
       CASE s.nombre
           WHEN 'Palco'         THEN 500.00
           WHEN 'Tribuna Norte' THEN 250.00
           WHEN 'Tribuna Sur'   THEN 250.00
           WHEN 'General'       THEN 100.00
       END AS costo
FROM evento e
JOIN sector s
    ON s.estadio_nombre         = e.estadio_nombre
   AND s.estadio_direccion_pais = e.estadio_direccion_pais
   AND s.estadio_direccion_ciudad = e.estadio_direccion_ciudad
ON DUPLICATE KEY UPDATE costo = VALUES(costo);
 

-- USUARIOS ADMINISTRADORES POR DEFECTO
-- Un admin por país sede: México, Canadá, Estados Unidos
-- Password: "admin1234" (esto es solo para pruebas, en producción se usan contraseñas seguras y mecanismos de autenticación adecuados)
 
INSERT INTO usuario (mail, password, documento_tipo, documento_numero_doc, direccion_pais) VALUES
('admin.mexico@mundial2026.com',  'admin1234', 'Pasaporte', 'ADM-MX-001', 'México'),
('admin.canada@mundial2026.com',  'admin1234', 'Pasaporte', 'ADM-CA-001', 'Canadá'),
('admin.usa@mundial2026.com',     'admin1234', 'Pasaporte', 'ADM-US-001', 'Estados Unidos')
ON DUPLICATE KEY UPDATE mail=mail;
 
INSERT INTO perfil (mail_usuario) VALUES
('admin.mexico@mundial2026.com'),
('admin.canada@mundial2026.com'),
('admin.usa@mundial2026.com')
ON DUPLICATE KEY UPDATE mail_usuario = VALUES(mail_usuario);
 

INSERT INTO administrador (id_administrador, fecha_asignado, pais_sede)
SELECT p.id, CURDATE(), u.direccion_pais
FROM perfil p
JOIN usuario u ON u.mail = p.mail_usuario
WHERE u.mail IN (
    'admin.mexico@mundial2026.com',
    'admin.canada@mundial2026.com',
    'admin.usa@mundial2026.com'
)
ON DUPLICATE KEY UPDATE pais_sede = VALUES(pais_sede);
 