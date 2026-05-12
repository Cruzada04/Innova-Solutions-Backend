# Dockerfile para la aplicación Spring Boot
FROM amazoncorretto:17-alpine

# Directorio de trabajo
WORKDIR /app

# Copiar el archivo jar generado
COPY target/*.jar app.jar

# Exponer el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
