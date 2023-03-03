FROM openjdk:11
EXPOSE 9090
ADD target/projet-cv-image.jar projet-cv-image.jar
ENTRYPOINT ["java", "-jar","/projet-cv-image.jar"]