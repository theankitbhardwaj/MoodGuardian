#
# Build stage
#
FROM maven:3.9.0-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#

FROM eclipse-temurin:17-alpine
COPY --from=build /target/*.jar MoodGuardian.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","MoodGuardian.jar"]