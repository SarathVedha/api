FROM eclipse-temurin:17-jre-focal

LABEL version="1.0"
LABEL maintainer="sarath vedha"
LABEL description="SpringBoot API-SERVICE App"

WORKDIR /app/jar
COPY target/API-REGISTRY-1.0.jar /app/jar/API-REGISTRY.jar

RUN mkdir -m 777 /app/log

EXPOSE 80
ENV APP_PROFILE=prod
ENV APP_PORT=80
ENV SERVICE_URL=http://localhost:80
ENV LOG_PATH=/app/log
HEALTHCHECK --interval=50s --timeout=3s CMD curl -fkv http://localhost/actuator/health || exit 1

RUN useradd -r -d /app vedha
USER vedha

ENTRYPOINT [ "java", "-jar", "API-REGISTRY.jar" ]