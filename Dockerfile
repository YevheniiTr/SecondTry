#
# Build stage
#
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:17.0.2-jdk-slim
COPY --from=build /target/university_organizer-0.0.1-SNAPSHOT.jar university_organizer.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","university_organizer.jar"]