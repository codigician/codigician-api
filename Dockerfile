FROM openjdk:17

COPY core-*.jar app/core.jar

ENTRYPOINT ["java", "-jar", "app/core.jar"]