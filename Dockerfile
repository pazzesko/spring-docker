FROM openjdk:14-jdk-alpine
RUN addgroup -S user && adduser -S user -G user
USER user:user
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]