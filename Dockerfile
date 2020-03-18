FROM openjdk:14

WORKDIR /usr/src/app
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
