# Multi-stage build — Railway 自动编译 + 运行
# Build stage
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
# 先下载依赖（利用 Docker cache）
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

# Run stage
FROM eclipse-temurin:17-jre
COPY --from=build /app/target/*.jar /app.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","/app.jar"]
