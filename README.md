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

Desde la raíz del repositorio:

```bash
docker-compose up -d
```

Esto levanta MySQL en el puerto **3307** y crea el esquema automáticamente. Podes verificar que esté listo con:

```bash
docker logs obligatorio2026_db
```

---

## 3. Levantar el backend

```bash
cd backend
./mvnw spring-boot:run
```

> En Windows usar `mvnw.cmd spring-boot:run`

El servidor arranca en **http://localhost:8080**.

Al iniciar, Hibernate crea/actualiza las tablas automáticamente y se insertan los datos iniciales (48 equipos del Mundial 2026 y 16 estadios sede) desde `src/main/resources/data/inserts.sql`.

---

### Verificar que funciona

```bash
curl http://localhost:8080/eventos
```

---

## 4. Frontend

El frontend de la aplicación se encuentra en un repositorio separado:
https://github.com/belenkanas/FrontendObligatorio-BDII-2026.git

Para ejecutarlo y ver los primeros pasos, seguir las instrucciones del README correspondiente.

---

## Estructura del proyecto

```
/
├── backend/
  ├── src/
    ├── main/
      ├──java/com/obligatorio/backend/
        ├── config/
        ├── controller/
        ├── model/
        ├── repository/
        ├── scheduler/
        ├── security/
        ├── service/
        ├── Main.java
      ├──resources/
        ├── data/
        ├── application.properties
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
| Seguridad | BCrypt (Spring Security Crypto) |
| Frontend | React Native, Expo Router |

---

## Endpoints


---


## Notas

- El archivo `.env` está en `.gitignore` y **no debe commitearse**.
- La base de datos corre en el puerto `3307` para evitar conflictos con instancias locales de MySQL.
- Los datos iniciales se insertan automáticamente al levantar el backend usando `ON DUPLICATE KEY UPDATE`, por lo que pueden ejecutarse múltiples veces sin generar duplicados.