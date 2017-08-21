#!/usr/bin/env bash
# Cats 컨테이너 실행
docker run --name docker-cats -it --rm -p 8080:8080 \
-e $ACTIVEMQ_SERVICE_HOST=jcha-OSX.local \
-e $ACTIVEMQ_SERVICE_PORT=61616 \
-e $MYSQL_SERVICE_HOST=$HOST_IP \
-e $MYSQL_SERVICE_PORT=3306 \
cats
