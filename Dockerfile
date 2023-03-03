FROM openjdk:11
EXPOSE 8080
ADD target/projet-cv-image.jar projet-cv-image.jar
ENTRYPOINT ["java", "-jar","/projet-cv-image.jar"]