Lab 04 - Docker 환경에서 컨테이너 이미지 빌드와 실행
===

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## MySQL Docker 컨테이너 실행

```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ docker-compose -p myslq -f mysql-compose.yml up -d
student $ docker ps
[student@teacher lab04]$ docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                    NAMES
e28ffb60a7ae        mysql               "docker-entrypoint.sh"   About a minute ago   Up 2 seconds        0.0.0.0:3306->3306/tcp   docker-mysql
```
mysql-compose.yml : 
```yaml
docker-mysql:
  image: mysql
  container_name: docker-mysql
  ports:
    - "3306:3306"
  environment:
    MYSQL_DATABASE: "cats"
    MYSQL_USER: "cat"
    MYSQL_PASSWORD: "meow"
    MYSQL_ROOT_PASSWORD: "mysql"
```

## MySQL Docker 컨테이너 종료

```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ docker-compose -p mysql -f mysql-compose.yml down
student $ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES 
```
 
## Cats Docker 이미지 빌드

lab02에서 생성한 cats-1.0.jar 패키지를 Docker 이미지로 빌드합니다.

```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ cp ~/container-workshop/cats/target/cats-1.0.jar .
student $ docker build -t cats .
student $ docker images

REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
cats                latest              15546c8440c6        30 minutes ago      250.6 MB
openjdk             8-jre-slim          9799b08757cc        3 weeks ago         206.5 MB
mysql               latest              c73c7527c03a        3 weeks ago         412.3 MB
hello-world         latest              1815c82652c0        9 weeks ago         1.84 kB
```

## Cats Docker 컨테이너 실행

Docker 이미지를 실행하는 방법은 docker run 명령을 이용하거나 docker-compse 명령을 이용합니다. 
* Docker 컨테이너 실행 시 docker run 과 docker-compose 명령은 혼용할 수 없습니다
### Docker run 을 이용한 컨테이너 실행

Docker run 명령을 이용해 Cats Docker 이미지를 컨테이너로 실행하는 방법입니다. 

```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ docker run --name docker-cats -d -p 8080:8080 \
          -e ACTIVEMQ_SERVICE_HOST=jcha-OSX.local \
          -e ACTIVEMQ_SERVICE_PORT=61616 \
          -e MYSQL_SERVICE_HOST=$(hostname --ip-address) \
          -e MYSQL_SERVICE_PORT=3306 \
          cats
          
student $ docker ps          
student $ docker logs -f docker-cats
```
Docker 명령을 이용한 Cats 컨테이너 실행 종료 방법입니다. 

```
student $ docker rm -v docker-cats
```

### Docker Compose 를 이용한 실행

Docker Compose 명령을 이용해 Cats Docker 이미지를 컨테이너로 실행하는 방법입니다.

```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ export HOST_IP=$(hostname --ip-address) && \
          docker-compose -p cats -f cats-compose.yml up
```

cats-compose.yml : 
```
version: '2'
services:
  docker-cats:
    image: cats
    container_name: docker-cats
    ports:
      - "8080:8080"
    environment:
      ACTIVEMQ_SERVICE_HOST: "jcha-OSX.local"
      ACTIVEMQ_SERVICE_PORT: 61616
      MYSQL_SERVICE_HOST: ${HOST_IP}
      MYSQL_SERVICE_PORT: 3306
```

Docker Compose 이용한 Cats 컨테이너 실행 종료 방법입니다. 
```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ export HOST_IP=$(hostname --ip-address) && \
          docker-compose -p cats -f cats-compose.yml down
```

## Cats Docker 컨테이너 애플리케이션 웹 페이지 조회

```
root $ su - student
student $ firefox http://localhost:8080 &
```  