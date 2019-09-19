FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD entersekt.jar entersekt.jar
ENTRYPOINT ["java", "-jar", "entersekt.jar"]
