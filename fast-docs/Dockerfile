FROM maven:3.9.9-eclipse-temurin-21-jammy AS dependencies
WORKDIR /app
COPY pom.xml ./
RUN mvn dependency:resolve

FROM dependencies AS build
COPY /src ./src
RUN mvn -DskipTests package

FROM eclipse-temurin:21-jre-jammy AS production
COPY --from=build /app/target/fast-docs-*.jar /fast-docs.jar
CMD ["java", "-jar", "fast-docs.jar"]