# Etapa 1: Build usando Maven
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

# Define diretório de trabalho
WORKDIR /build

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Faz o build do projeto (gera o .jar)
RUN mvn clean package -DskipTests

# Etapa 2: Executar com JDK leve e seguro
FROM openjdk:17-jdk-slim

# Cria um usuário não-root
RUN useradd -m appuser

# Define diretório de trabalho
WORKDIR /app

# Copia o JAR gerado do estágio anterior
COPY --from=builder /build/target/HidroSafe-0.0.1-SNAPSHOT.jar app.jar

# Variável de ambiente para customizar a JVM
ENV JAVA_OPTS="-Xms128m -Xmx256m"

# Usa usuário não-root
USER appuser

# Expondo a porta
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
