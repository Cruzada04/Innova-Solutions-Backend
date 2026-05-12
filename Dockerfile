# ETAPA 1: Compilación con Maven
FROM maven:3.9.6-amazoncorretto-17-al2023 AS build
WORKDIR /app
# Copiar el pom.xml y descargar dependencias (para caché)
COPY pom.xml .
RUN mvn dependency:go-offline
# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# ETAPA 2: Imagen de ejecución (ligera)
FROM amazoncorretto:17-alpine
WORKDIR /app
# Copiar el JAR desde la etapa 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]