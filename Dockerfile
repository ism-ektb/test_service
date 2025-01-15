FROM openjdk:22
COPY service/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]