#!/bin/bash
# Cats 컨테이너 실행
docker run --name docker-cats -it --net=host --rm -p 8080:8080 \
-e ACTIVEMQ_BROKER_URL=ssl://broker-amq-tcp-ssl-container-workshop.apps.52.78.204.231.nip.io:443 \
-e ACTIVEMQ_SERVICE_PORT=61616 \
-e MYSQL_SERVICE_HOST=$(hostname --ip-address) \
-e MYSQL_SERVICE_PORT=3306 \
-e _JAVA_OPTIONS=-Djava.net.preferIPv4Stack=true \
cats
