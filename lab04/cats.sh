#!/usr/bin/env bash
# Cats 컨테이너 실행
docker run --name cats -it --rm -p 8080:8080 \
-e activemq.service.host=jcha-OSX.local \
-e activemq.service.port=61616 \
-e mysql.service.host=${HOST_IP} \
-e mysql.service.port=3306 \
cats
