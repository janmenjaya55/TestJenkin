FROM openjdk:8-alpine
-f "serviceregigtry/Dockerfile
ADD -f "/var/lib/jenkins/workspace/JenkinBuildWork/JenkinBuildDeploy/serviceregigtry/target/serviceregigtry-0.0.1-SNAPSHOT.jar" serviceregigtry-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "serviceregigtry-0.0.1-SNAPSHOT.jar"]
