# Etapa 1: Importación de JDK y copia de los archivos necesarios
FROM openjdk:19-jdk AS build
WORKDIR /app
COPY pom.xml .
COPY src src

# Copiar el contenedor Maven
COPY mvnw .
COPY .mvn .mvn

# Establecer el permiso de ejecución para el contenedor Maven
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Etapa 2: Crear la imagen final de Docker usando OpenJDK 19
FROM openjdk:19-jdk
VOLUME /tmp

# Copiar el JAR desde la etapa de compilación
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
EXPOSE 8080