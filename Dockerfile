FROM adoptopenjdk:8-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} linea3.jar
ENTRYPOINT ["java","-jar","/linea3.jar"]