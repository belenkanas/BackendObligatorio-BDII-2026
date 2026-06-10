## 🛠 Instalación y Ejecución

### 1. Levantar la base de datos
Desde la raíz del repositorio:
```bash
docker-compose up -d
```

### 2. Ejecutar el schema
Ejecutar el archivo `schema.sql` desde la consola de DataGrip.

### 3. Levantar el backend
```bash
cd backend
mvn spring-boot:run
```

### 4. Cargar datos de prueba
Con el backend corriendo, ejecutar:
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

Credenciales de prueba:
- **Mail:** test@test.com
- **Contraseña:** 1234