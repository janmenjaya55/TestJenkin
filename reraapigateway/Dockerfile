FROM openjdk:8-alpine
ADD ./reraapigateway/target/reraapigateway-0.0.1-SNAPSHOT.jar reraapigateway-0.0.1-SNAPSHOT.jar
EXPOSE     30002

ENTRYPOINT ["java", "-jar", "reraapigateway-0.0.1-SNAPSHOT.jar"]
