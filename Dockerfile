FROM maven:3.9.8-amazoncorretto-17-al2023 AS build

RUN mkdir /home/app
COPY . /home/app
RUN cd /home/app && mvn package

# Package stage
FROM openjdk:17.0.2-slim
COPY --from=build /home/app/target/odontologo-0.0.1-SNAPSHOT.jar /myproject.jar

EXPOSE 8080

CMD ["java", "-Delastic.apm.service_name=myproject -Delastic.apm.application_packages=com.example.myproject","-jar", "/myproject.jar"]
