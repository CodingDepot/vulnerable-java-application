FROM gradle:7.5.1-jdk17 AS builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle downloadNewrelic && unzipNewrelic
RUN gradle bootJar --no-daemon


FROM eclipse-temurin:17
LABEL org.opencontainers.image.source="https://github.com/DataDog/vulnerable-java-application/"
EXPOSE 8080
RUN mkdir /app
WORKDIR /app
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
COPY --from=builder /home/gradle/src/newrelic/* /app/newrelic/*
COPY --from=builder /home/gradle/src/newrelic.yml /app/newrelic/newrelic.yml

CMD ["java", "-javaagent:/app/newrelic.jar", "-jar", "/app/spring-boot-application.jar"]
