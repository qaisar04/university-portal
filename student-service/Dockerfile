FROM openjdk:17

WORKDIR /app

COPY target/student-service-0.0.1-SNAPSHOT.jar /app/studentservice.jar

EXPOSE 8081

CMD ["java", "-jar", "studentservice.jar"]