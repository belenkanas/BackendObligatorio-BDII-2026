FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY backend/pom.xml ./
COPY backend/src ./src
RUN mkdir -p /root/.m2
RUN ./mvnw -f backend/pom.xml -q -DskipTests package
EXPOSE 8080
CMD ["java","-jar","backend/target/backend-0.0.1-SNAPSHOT.jar"]
