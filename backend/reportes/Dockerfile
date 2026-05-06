# Imagen base con Java 21
FROM eclipse-temurin:21-jdk

# Carpeta de trabajo
WORKDIR /app

# Copiar el jar
COPY target/reportes-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8082

# Ejecutar app
ENTRYPOINT ["java", "-jar", "app.jar"]