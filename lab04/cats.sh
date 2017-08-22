#!/bin/bash
# Cats 컨테이너 실행
docker run --name docker-cats -it --rm -p 8080:8080 \
-e ACTIVEMQ_BROKER_URL=ssl://amq-broker-ssl-amq-tcp-ssl-container-workshop.1d35.starter-us-east-1.openshiftapps.com:443 \
-e ACTIVEMQ_SERVICE_PORT=61616 \
-e MYSQL_SERVICE_HOST=$HOST_IP \
-e MYSQL_SERVICE_PORT=3306 \
cats
