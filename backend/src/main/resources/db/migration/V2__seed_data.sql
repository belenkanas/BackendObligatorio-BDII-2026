-- Seed data: small dataset for development
USE obligatorio;

INSERT INTO Equipo (nombrePais) VALUES ('Uruguay') ON DUPLICATE KEY UPDATE nombrePais=nombrePais;
INSERT INTO Equipo (nombrePais) VALUES ('Argentina') ON DUPLICATE KEY UPDATE nombrePais=nombrePais;

INSERT INTO Estadio (nombre, direccion_pais, direccion_ciudad) VALUES ('Estadio Centenario','Uruguay','Montevideo') ON DUPLICATE KEY UPDATE nombre=nombre;

-- simple partido
INSERT INTO Partido (fecha_hora) VALUES ('2026-11-21 18:00:00') ON DUPLICATE KEY UPDATE fecha_hora=fecha_hora;

-- Note: more seeds to be added as needed
