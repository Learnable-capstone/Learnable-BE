FROM adoptopenjdk/openjdk11

COPY ./build/libs/*.jar gpteacher.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/gpteacher.jar"]
