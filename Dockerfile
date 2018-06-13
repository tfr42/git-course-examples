FROM java:8-jdk
ARG jarfile
ADD ${jarfile} /application.jar
CMD ["java", "-jar", "/application.jar"]