FROM maven:3.10.0-amazoncorretto-25 as builder
WORKDIR /app
COPY src ./src
RUN mvn clean package

FROM eclipse-temurin:25.0.5_8-jre-noble as runner
WORKDIR /app
COPY --from=builder ./app/target/patient-service-0.0.1-SNAPSHOT.jar ./app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "app.jar"]
