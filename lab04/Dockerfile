FROM openjdk:8-jre-slim
MAINTAINER jcha@redhat.com

WORKDIR /
COPY cats-1.0.jar /
RUN mkdir /ssl
COPY client.ts /ssl

ENV ACTIVEMQ_BROKER_URL ssl://amq-broker-ssl-amq-tcp-ssl-container-workshop.1d35.starter-us-east-1.openshiftapps.com:443
ENV MYSQL_SERVICE_HOST mysql.svc
ENV MYSQL_SERVICE_PORT 3306

EXPOSE 8080

CMD ["java","-Djavax.net.ssl.trustStore=ssl/client.ts", "-Duser.timezone=GMT+09:00","-jar","cats-1.0.jar"]