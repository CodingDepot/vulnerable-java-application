FROM gradle:7.5.1-jdk17 AS builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle downloadNewrelic && gradle unzipNewrelic
RUN gradle bootJar --no-daemon


FROM eclipse-temurin:17
LABEL org.opencontainers.image.source="https://github.com/DataDog/vulnerable-java-application/"
EXPOSE 8000
RUN mkdir /app
WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
COPY --from=builder /home/gradle/src/newrelic/* /app/newrelic/
COPY --from=builder /home/gradle/src/newrelic.yml /app/newrelic/newrelic.yml
RUN apt-get update && apt-get install -y iputils-ping

CMD ["java", "-javaagent:/app/newrelic/newrelic.jar", "-jar", "/app/spring-boot-application.jar"]
