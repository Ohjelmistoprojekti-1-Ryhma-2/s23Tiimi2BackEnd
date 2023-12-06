FROM maven:3.8.5-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

FROM eclipse-temurin:17-jre-focal
COPY --from=build /home/app/target/dogstore-0.0.1-SNAPSHOT.jar /usr/local/lib/dogstore.jar
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/usr/local/lib/dogstore.jar" ]