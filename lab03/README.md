Lab 03 - Docker 설치 및 컨테이너 실행
===

* 이 문서는 Red Hat Enterprise Linux 를 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## Docker 설치

```
root $ rpm --import "https://sks-keyservers.net/pks/lookup?op=get&search=0xee6d536cf7dc86e2d7d56f59a178ac6c6238f52e"
root $ yum-config-manager --add-repo https://packages.docker.com/1.12/yum/repo/main/centos/7
root $ yum makecache fast
root $ yum install -y docker-engine
root $ systemctl start docker
root $ systemctl enable docker
root $ docker version
root $ docker images -q | xargs docker rmi
```

## Docker Compose 설치

Compose는 Docker 컨테이너 애플리케이션들을 정의하고 실행하기 위한 도구입니다.
```
root $ curl -L https://github.com/docker/compose/releases/download/1.14.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
root $ chmod +x /usr/local/bin/docker-compose
root $ docker-compose version
```

## 사용자 계정 Docker 명령 사용 설정

student 계정이 docker 명령을 직접 사용할 수 있게 설정합니다. 

```
root $ usermod -aG docker student
root $ systemctl restart docker
```

## 사용자 계정으로 Docker 명령 실행

```
root $ su - student
student $ docker version
```
 
## Docker 컨테이너 실행

```
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
  