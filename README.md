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

## Endpoints

**Autenticación**

```
POST /auth/registro - Registra un nuevo usuario
POST /auth/login - Inicia sesión y devuelve un token JWT

```

---

## Notas

- El archivo `.env` está en `.gitignore` y **no debe commitearse**.
- La base de datos corre en el puerto `3307` para evitar conflictos con instancias locales de MySQL.
- Los datos iniciales se insertan automáticamente durante la inicialización del backend utilizando la cláusula `ON DUPLICATE KEY UPDATE`, evitando la generación de registros duplicados en ejecuciones sucesivas.