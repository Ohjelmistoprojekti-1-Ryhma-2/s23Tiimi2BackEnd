FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn -f home/app/pom.xml clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build home/app/target/dogstore-0.0.1-SNAPSHOT.jar s23Tiimi2BackEnd.jar
EXPOSE 8080
ENTRYPOINT [ "java", "jar", "/s23Tiimi2BackEnd.jar" ]