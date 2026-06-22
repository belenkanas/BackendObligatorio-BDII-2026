# Mundial 2026 — Sistema de Ticketing Digital

Sistema integral de ticketing para partidos del Mundial 2026. Permite la compra y transferencia de entradas entre usuarios, y la validación de acceso a los eventos mediante códigos QR dinámicos que se regeneran cada 30 segundos para evitar fraudes. La plataforma contempla tres roles de usuario: General, Administrador y Funcionario, cada uno con funcionalidades específicas dentro del sistema.

---

## Requisitos previos

- [Docker](https://www.docker.com/) y Docker Compose
- Java 21 o superior
- Maven 3.9+ (o usar el wrapper `./mvnw` incluido)

---

## 1. Configurar variables de entorno

Crear un archivo `.env` en la raíz del repositorio:

```env
MYSQL_ROOT_PASSWORD=root1234
MYSQL_DATABASE=obligatorio
MYSQL_USER=admin
MYSQL_PASSWORD=admin123
```

---

## 2. Levantar la base de datos

Desde la raíz del repositorio ejecutar:

```bash
docker-compose up -d
```

Esto levanta una instancia de MySQL en el puerto **3307**. El esquema de la base de datos se genera automáticamente durante la inicialización del backend. Puede verificarse que el contenedor se encuentre en funcionamiento mediante:

```bash
docker logs obligatorio2026_db
```

---

## 3. Levantar el backend

Para levantar el backend, *el contenedor de Docker debe estar en ejecución en todo momento*.

```bash
cd backend
./mvnw spring-boot:run
```

El servidor arranca en **http://localhost:8080**.

Al iniciar, Hibernate crea las tablas automáticamente y se insertan los datos iniciales (48 equipos del Mundial 2026 y 16 estadios sede) desde `src/main/resources/data/inserts.sql`.

---

## 4. Frontend

El frontend de la aplicación se encuentra en un repositorio separado. Para acceder a él:

https://github.com/belenkanas/FrontendObligatorio-BDII-2026.git

Para ejecutarlo y ver los primeros pasos, seguir las instrucciones del README.md correspondiente.

---

## Estructura del proyecto

```
/
├── backend/
|  └── src/
|    └── main/
|      ├── java/com/obligatorio/backend/
|      |  ├── config/
|      |  ├── controller/
|      |  ├── dto/
|      |  ├── model/
|      |  ├── repository/
|      |  ├── scheduler/
|      |  ├── security/
|      |  ├── service/
|      |  └── Main.java
|      └── resources/
|        ├── data/
|        └── application.properties
├── mer/
├── sql/
├── .env  
├── .gitignore 
└── docker-compose.yml          
```

---

## Tecnologías

| Capa | Tecnología |
|------|-----------|
| Backend | Java 21, Spring Boot 3.5, Spring Data JPA |
| Base de datos | MySQL 8.0 (Docker) |
| Contenedorización | Docker, Docker Compose |
| Seguridad | BCrypt (Spring Security Crypto) |
| Frontend | React Native, Expo Router |

---

## Endpoints más importantes

### Autenticación

| Endpoint | Descripción |
|----------|-------------|
| `POST /auth/registro` | Registra un nuevo usuario (genera un hash en la contraseña indicada) |
| `POST /auth/login` | Inicia sesión en el sistema y devuelve un token JWT |

---

### Administrador

| Endpoint | Descripción |
|----------|-------------|
| `GET /administradores` | Devuelve una lista con los datos de los administradores (`id_administrador`, `pais_sede`, `fecha_asignado`) |
| `POST /administradores` | Crea un nuevo administrador en el sistema (un perfil con el rol de administrador) |
| `POST /administradores/cambiar-rol` | Tomando los datos del `id_perfil` y rol del usuario seleccionado, el administrador puede asignarle un nuevo rol (`general`, `funcionario` o `administrador`) |
| `POST /administradores/verificaciones/responder` | Responde a las solicitudes de verificación de identificación de los usuarios recientemente registrados |

---

### General

| Endpoint | Descripción |
|----------|-------------|
| `GET /generales` | Obtiene todos los usuarios con el rol de `general` |

---

### Funcionario

| Endpoint | Descripción |
|----------|-------------|
| `GET /funcionarios` | Obtiene todos los usuarios con el rol de funcionario |
| `GET /funcionarios/{id}/asignaciones` | Muestra todas las asignaciones a eventos del funcionario con el `id_perfil` dado |

---

### DispositivoEscaneo

| Endpoint | Descripción |
|----------|-------------|
| `GET /dispositivos` | Obtiene todos los dispositivos de escaneo del sistema (`id`, `nro_serie`, `nro_legajo` del funcionario asignado) |
| `POST /{id}/asignar` | Tomando el `id` del dispositivo, lo asigna al funcionario especificado por su nro de legajo |
| `POST /{id}/desasignar` | Tomando el `id` y el `nro_legajo` del funcionario, elimina la relación de validación entre ambas entidades |

---

### Validacion

| Endpoint | Descripción |
|----------|-------------|
| `GET /validaciones` | Retorna todas las agregaciones entre los dispositivos de escaneo con sus funcionarios asignados |
| `GET /validaciones/{nroLegajoFuncionario}/{idDispositivoEscaneo}` | Retorna una instancia de la agregación especificando tanto el dispositivo como el funcionario |

---

### Estadio

| Endpoint | Descripción |
|----------|-------------|
| `GET /estadios` | Obtiene el nombre y dirección de cada estadio |

---

### Partido

| Endpoint | Descripción |
|----------|-------------|
| `GET /partidos` | Obtiene todos los partidos |
| `GET /partidos/{fechaHora}` | Obtiene el partido correspondiente a la fecha y hora indicada |
| `POST /partidos` | Crea un nuevo partido |
| `DELETE /partidos/{fechaHora}` | Elimina el partido correspondiente a la fecha y hora indicada |

---

### Evento

| Endpoint | Descripción |
|----------|-------------|
| `GET /eventos` | Retorna todos los eventos del sistema |
| `PATCH /eventos/actualizar-estado` | Actualiza el estado de un evento (`activo` o `suspendido`) según las circunstancias del sistema |

---

### Equipo

| Endpoint | Descripción |
|----------|-------------|
| `GET /equipos` | Devuelve todos los países participantes del torneo |
| `POST /equipos` | Crea un nuevo equipo indicando el nombre del país |

---

### Venta

| Endpoint | Descripción |
|----------|-------------|
| `GET /ventas` | Obtiene todas las ventas registradas |
| `GET /ventas/usuario/{idGeneral}` | Obtiene las ventas realizadas por un usuario en específico |
| `POST /ventas/comprar` | Controla la lógica y restricciones del sistema para comprar la cantidad justa de entradas |

---

### Entrada

| Endpoint | Descripción |
|----------|-------------|
| `GET /entradas` | Devuelve todas las entradas del sistema (id, datos del comprador y evento al que asistirá) |
| `POST /entradas/{id}/generar-token` | Genera el token necesario para la seguridad y validación de la entrada |

---

### TransfEntrada

| Endpoint | Descripción |
|----------|-------------|
| `GET /transferencias-entrada` | Devuelve los datos de todas las transferencias de entradas realizadas |
| `POST /transferencias-entrada/iniciar` | Inicia la transferencia de una entrada, cumpliendo con las restricciones del sistema |
| `POST /transferencias-entrada/responder` | Responde a las transferencias solicitadas por otros usuarios |
| `POST /transferencias-entrada/cancelar` | Rechaza las solicitudes de transferencia de entradas |
| `GET /transferencias-entrada/pendientes/{idGeneral}` | Obtiene los movimientos pendientes correspondientes a un usuario en específico |
| `GET /transferencias-entrada/historial/{idGeneral}` | Obtiene el historial completo de transferencias de entradas de un usuario |

---
## Notas

- El archivo `.env` está en `.gitignore` y **no debe commitearse**.
- La base de datos corre en el puerto `3307` para evitar conflictos con instancias locales de MySQL.
- Los datos iniciales se insertan automáticamente durante la inicialización del backend utilizando la cláusula `ON DUPLICATE KEY UPDATE`, evitando la generación de registros duplicados en ejecuciones sucesivas.