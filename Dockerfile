FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia o Maven Wrapper e pom.xml primeiro (cache eficiente)
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Dá permissão de execução ao mvnw antes de rodar
RUN chmod +x mvnw

# Baixa dependências (melhora cache)
RUN ./mvnw dependency:go-offline

# Copia o código-fonte
COPY src src

# Executa o Spring Boot usando o wrapper
CMD ["./mvnw", "spring-boot:run"]
