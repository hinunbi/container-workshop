#!/bin/sh

# Cats 컨테이너 빌드
cp ../target/cat-1.0.jar .
docker rmi cats
docker build -t cats -f Dockerfile .
rm cat-1.0.jar

# Eclipse STS 개발 도구 설치
