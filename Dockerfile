FROM adoptopenjdk/openjdk11

COPY /build/libs/learnable-0.0.1-SNAPSHOT.jar gpteacher.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "/gpteacher.jar"]
