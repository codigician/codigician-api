FROM openjdk:17

COPY target/core-*.jar app/core.jar

ENTRYPOINT ["java", "-jar", "app/core.jar"]