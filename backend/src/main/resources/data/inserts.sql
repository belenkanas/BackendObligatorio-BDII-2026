-- Se agregan los países y estadios que formarán parte del mundial 2026,
-- con la idea de que no se tengan que agregar a mano cada vez que se quiera probar la aplicación,
-- ni que se tenga que agregar un país o estadio que no formará parte del mundial.
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