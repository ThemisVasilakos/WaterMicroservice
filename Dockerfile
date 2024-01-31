FROM openjdk:17
EXPOSE 8080:8080
ADD target/water-microservice.jar water-microservice.jar
ENTRYPOINT ["java","-jar","/water-microservice.jar"]