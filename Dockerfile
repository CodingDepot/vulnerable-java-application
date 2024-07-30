FROM gradle:8.9.0-jdk17 AS builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN apt-get update && apt-get install -y unzip curl
RUN curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip \
        && unzip newrelic-java.zip
RUN gradle bootJar --no-daemon

FROM amazoncorretto:17-alpine
EXPOSE 8000
RUN mkdir /app
WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
COPY --from=builder /home/gradle/src/newrelic/* /app/newrelic/
COPY --from=builder /home/gradle/src/newrelic.yml /app/newrelic/newrelic.yml

CMD ["java", "-javaagent:/app/newrelic/newrelic.jar", "-jar", "/app/spring-boot-application.jar"]
