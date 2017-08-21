#!/bin/sh

# MySQL 컨테이너 실행
docker run --name mysql -it --rm -p 3306:3306 \
-e MYSQL_ROOT_PASSWORD=mysql \
-e MYSQL_DATABASE=cats \
-e MYSQL_USER=cat \
-e MYSQL_PASSWORD=meow \
mysql

# Cats 컨테이너 실행
docker run --name cats -it --rm -p 28080:8080 \
-e activemq.service.host=10.64.168.80 \
-e activemq.service.port=61616 \
-e mysql.service.host=10.64.168.80 \
-e mysql.service.port=3306 \
cats

