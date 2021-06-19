FROM openjdk:11-jre

COPY build/libs/baseproject-*.jar application.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/application.jar"]