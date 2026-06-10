# Mundial 2026 — Sistema de Ticketing Digital

Sistema integral de ticketing para partidos del Mundial 2026. Permite la compra, transferencia y validación de entradas con QR dinámico.

---

## Requisitos previos

- [Docker](https://www.docker.com/) y Docker Compose
- Java 17 o superior
- Maven 3.9+ (o usar el wrapper `./mvnw` incluido)
- Node.js 18+
- [Expo CLI](https://docs.expo.dev/get-started/installation/): `npm install -g expo-cli`

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

Esto levanta MySQL en el puerto **3307**. Podés verificar que esté listo con:

```bash
docker logs obligatorio2026_db
```

---

## 3. Crear el esquema

Con la base de datos corriendo, ejecutar el script SQL:

```bash
# Opción A: desde línea de comandos
mysql -h 127.0.0.1 -P 3307 -u admin -padmin123 < sql/schema.sql

# Opción B: desde DataGrip u otro cliente SQL
# Conectarse a localhost:3307, usuario: admin, contraseña: admin123
# Ejecutar el archivo sql/schema.sql
```

---

## 4. Levantar el backend

```bash
cd backend
./mvnw spring-boot:run
```

El servidor arranca en **http://localhost:8080**.

> En Windows usar `mvnw.cmd spring-boot:run`

### Verificar que funciona

```bash
curl http://localhost:8080/eventos
```

---

## 5. Cargar datos de prueba

Con el backend corriendo, registrar un usuario de prueba:

```bash
curl -X POST http://localhost:8080/auth/registro \
  -H "Content-Type: application/json" \
  -d '{
    "mail": "test@test.com",
    "password": "1234",
    "documentoTipo": "CI",
    "documentoNumeroDoc": "22222222",
    "direccionCalle": "Av. Italia",
    "direccionNumero": "100",
    "direccionCodigoPostal": "11600",
    "direccionPais": "Uruguay",
    "direccionLocalidad": "Montevideo"
  }'
```

**Credenciales de prueba:**
- Mail: `test@test.com`
- Contraseña: `1234`

---

## 6. Levantar el frontend

```bash
cd frontend
npm install
npx expo start
```

Opciones al iniciar Expo:
- Presionar `a` para abrir en emulador Android
- Presionar `i` para abrir en simulador iOS (solo macOS)
- Presionar `w` para abrir en navegador web
- Escanear el QR con la app **Expo Go** en tu celular

---

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/auth/registro` | Registrar nuevo usuario |
| POST | `/auth/login` | Iniciar sesión |
| GET | `/eventos` | Listar eventos disponibles |
| GET | `/entradas` | Listar entradas |
| POST | `/ventas` | Crear una venta |
| GET | `/sectores` | Listar sectores |
| GET | `/estadios` | Listar estadios |
| GET | `/equipos` | Listar equipos |
| GET | `/partidos` | Listar partidos |

---

## Estructura del proyecto

```
.
├── backend/                  # API REST (Spring Boot + Java 21)
│   ├── src/main/java/        # Código fuente
│   │   └── com/obligatorio/backend/
│   │       ├── controller/   # Endpoints REST
│   │       ├── model/        # Entidades JPA
│   │       ├── repository/   # Repositorios Spring Data
│   │       └── service/      # Lógica de negocio
│   └── src/main/resources/
│       └── application.properties
├── frontend/                 # App móvil (React Native + Expo)
│   └── src/app/              # Pantallas (login, registro, eventos)
├── sql/
│   └── schema.sql            # Script de creación de la base de datos
├── mer/                      # Diagramas MER (draw.io)
├── docker-compose.yml
└── .env                      # Variables de entorno (no commitear)
```

---

## Tecnologías

- **Backend:** Java 21, Spring Boot 3.5, Spring Data JPA, Spring Security Crypto, MySQL
- **Frontend:** React Native, Expo Router
- **Base de datos:** MySQL 8.0 (Docker)

---

## Notas

- El archivo `.env` está en `.gitignore` y **no debe commitearse**.
- La base de datos corre en el puerto `3307` (no el 3306 estándar) para evitar conflictos con instancias locales de MySQL.
- `spring.jpa.hibernate.ddl-auto=update` crea/actualiza las tablas automáticamente al iniciar el backend, por lo que el paso del schema es opcional si el usuario de la DB tiene permisos de DDL.