FROM openjdk:8-alpine
ADD ./proc/self/task/11769/root/var/lib/jenkins/workspace/JenkinBuildWork/JenkinBuildDeploy/serviceregigtry/target/serviceregigtry-0.0.1-SNAPSHOT.jar serviceregigtry-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "serviceregigtry-0.0.1-SNAPSHOT.jar"]
