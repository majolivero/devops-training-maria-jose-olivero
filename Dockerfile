# Imagen base con JDK 17
FROM eclipse-temurin:17-jre

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado
COPY build/libs/calculator-java-gradle-0.0.1-SNAPSHOT.jar calculadora.jar

# Recibir el BuildId desde el pipeline para mostrarlo en la app
ARG BUILD_ID
ENV BUILD_ID=$BUILD_ID

# Exponer puerto (mismo que en application.properties)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "calculadora.jar"]
