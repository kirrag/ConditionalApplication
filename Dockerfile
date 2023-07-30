FROM adoptopenjdk/openjdk11:alpine-jre
EXPOSE 8081
COPY target/ConditionalApplication-1.0-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]

