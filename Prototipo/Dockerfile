# Usar la imagen de Java
FROM openjdk:17-jdk-slim

# Configurar directorio de trabajo
WORKDIR /app

# Copiar el archivo JAR del microservicio
COPY target/prototipo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto del microservicio
EXPOSE 8004

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
