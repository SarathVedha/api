FROM eclipse-temurin:17-jre-focal

LABEL version="1.0"
LABEL maintainer="sarath vedha"
LABEL description="SpringBoot API App"

WORKDIR /app/jar
COPY target/API-1.0.jar /app/jar/API.jar

#EXPOSE 443
#ENV APP_PROFILE=local
#HEALTHCHECK --interval=50s --timeout=3s CMD curl -fkv https://localhost/ || exit 1

RUN mkdir /app/apis
COPY src/main/resources/apis /app/apis

#Giving Write privilage for logs
RUN mkdir -m 777 /app/log

EXPOSE 80
ENV APP_PROFILE=prod
ENV CONFIG_URL=http://localhost:80
ENV GIT_BRANCH=master
ENV APP_PORT=80
ENV API_DELAY=5
ENV API_PATH=/app/apis
ENV SERVICE_HOSTNAME=localhost
ENV SERVICE_URL=http://localhost:80
ENV LOG_PATH=/app/log
HEALTHCHECK --interval=5m --timeout=3s CMD curl -fkv http://localhost/actuator/health || exit 1

#Giving Write privilage for heap and gc logs
RUN mkdir -m 777 /app/java

RUN useradd -r -d /app vedha
USER vedha

ENTRYPOINT [ "java", "-XX:MinRAMPercentage=25", "-XX:MaxRAMPercentage=50", "-XX:+HeapDumpOnOutOfMemoryError", "-XX:HeapDumpPath=/app/java/heapdump.log", "-Xlog:gc:/app/java/gc.log", "-jar", "API.jar" ]

#ENTRYPOINT [ "java", "-jar", "API.jar" ]
