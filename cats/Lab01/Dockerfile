FROM openjdk:8-jre-slim
MAINTAINER jcha@redhat.com
WORKDIR /
COPY cats-1.0.jar /

ENV ACTIVEMQ_SERVICE_HOST amq.svc
ENV ACTIVEMQ_SERVICE_PORT 61616
ENV MYSQL_SERVICE_HOST mysql.svc
ENV MYSQL_SERVICE_PORT 3306

EXPOSE 8080
CMD ["java","-Duser.timezone=GMT+09:00","-jar","cats-1.0.jar"]