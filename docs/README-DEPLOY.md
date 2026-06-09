# Deployment notes

Development notes to run the backend locally:

1. Ensure Java 17 and Maven are installed.
2. From repo root:

```bash
mvn -f backend/pom.xml clean test
```

3. To run with Docker (build from repo root):

```bash
docker build -t obligatorio-backend -f backend/Dockerfile .
docker run -p 8080:8080 obligatorio-backend
```
