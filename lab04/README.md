Lab four - Docker 환경에서 애플리케이션 개발과 실행
===

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## MySQL Docker 컨테이너 실행

```bash
root $ su - student
student $ cd ~/container-workshop/lab04
student $ docker-compose -f mysql-compose.yml up -d
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
student $ docker-compose -f mysql-compose.yml down
student $ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES 
```
 
## Cats Docker 이미지 생성

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


```
root $ su - student
student $ cd ~/container-workshop/lab04
student $ HOST_IP=$(hostname --ip-address) docker-compose -f cats-compose.yml up
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
  