FROM openjdk:8 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN  ./mvnw dependency:resolve


COPY src src
RUN ./mvnw package

FROM openjdk:8
WORKDIR employee
COPY --from=build target/*.jar employee.jar
ENTRYPOINT ["java", "-jar", "employee.jar"]