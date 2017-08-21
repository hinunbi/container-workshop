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

```bash
root $ su - student
student $ cd ~/container-workshop/lab04
student $ docker-compose -f mysql-compose.yml down
student $ docker ps
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES 
```

## 사용자 계정 Docker 명령 사용 설정

student 계정이 docker 명령을 직접 사용할 수 있게 설정합니다. 

```bash
root $ usermod -aG docker student
root $ systemctl restart docker
```

## 사용자 계정으로 Docker 명령 실행

```bash
root $ su - student
student $ docker version
student $ docker run hello-world

Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
b04784fba78d: Pull complete 
Digest: sha256:f3b3b28a45160805bb16542c9531888519430e9e6d6ffc09d72261b0d26ff74f
Status: Downloaded newer image for hello-world:latest

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://cloud.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/engine/userguide/

```
 
## MySQL Docker 컨테이너 실행

```bash
root $ su - student
student $ docker version
student $ docker run hello-world

  