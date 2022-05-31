FROM openjdk:8-jdk-slim
MAINTAINER fangTang
ARG JAR_FILE
WORKDIR /opt
COPY ${JAR_FILE} app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["bash", "-c", "java $JAVA_OPTS -jar app.jar"]