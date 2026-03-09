# Imagen base con JDK 17
FROM eclipse-temurin:17-jre

# Establecer directorio de trabajo
WORKDIR /app

# Copiar el JAR compilado
COPY app.jar calculadora.jar

# Exponer puerto (mismo que en application.properties)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "calculadora.jar"]
