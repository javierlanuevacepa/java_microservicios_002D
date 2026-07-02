# ===== ETAPA 1: Compilar el proyecto =====
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar primero el pom para aprovechar la caché
COPY pom.xml .

# Descargar dependencias
RUN mvn dependency:go-offline

# Copiar el resto del proyecto
COPY src ./src

# Compilar el proyecto sin ejecutar tests
RUN mvn clean package -DskipTests

# ===== ETAPA 2: Imagen final =====
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar","app.jar"]