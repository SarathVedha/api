FROM eclipse-temurin:17-jre-focal

LABEL version="1.0"
LABEL maintainer="sarath vedha"
LABEL description="SpringBoot API-CONFIG App"

WORKDIR /app/jar
COPY target/API-CONFIG-1.0.jar /app/jar/API-CONFIG.jar

#Giving Write privilage for logs
RUN mkdir -m 777 /app/log

EXPOSE 80
ENV APP_PROFILE=prod
ENV GIT_URL=https://github.com/SarathVedha/api-configs.git
ENV GIT_BRANCH=master
ENV GIT_PATH=/
ENV BASE_CONFIG_PATH=configs
#ENV SERVICE_URL=http://localhost:80
ENV LOG_PATH=/app/log

HEALTHCHECK --interval=5m --timeout=3s CMD curl -fkv http://localhost/actuator/health || exit 1

#RUN useradd -m -d /app vedha
#USER vedha

ENTRYPOINT [ "java", "-jar", "API-CONFIG.jar" ]