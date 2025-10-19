FROM eclipse-temurin:21-jdk

WORKDIR /app

# Só copia o POM e baixa dependências (cache eficiente)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copia tudo (mas código será sobrescrito via volume)
COPY src src

# Rodamos via mvnw para compilar+executar
CMD ["./mvnw", "spring-boot:run"]
