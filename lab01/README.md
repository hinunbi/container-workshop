Lab 01 - 사전 준비 
===

이 워크샵은 전체 실습을 위한 사전 준비 환경을 안내합니다. 

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## 아마존 인스턴스

실습에서 사용하는 AMI 이미지 식별자와 인스턴스 생성 정보는 다음과 같습니다. 
실습 참석자는 아래 정보를 참조해 자신의 아마존 계정에 실습용 인스턴스를 생성합니다.  

* AMI(Seoul Region) : ami-a46ab2ca (container-workshop-base-rhel-7.3-1)
* AMI(Tokyo Region) : ami-ea61a88c (container-workshop-base-rhel-7.3-1)
* Instance Type : t2.large (Cores 2, Memory 8G)
* EBS : 20G bytes
* Inbound port : 22, 80, 8080, 5901, 443, 8443


## VNC 클라이언트

* 실습은 아마존 인스턴스에서 진행됩니다. 
* 아마존 인스턴스 접속 정보는 실습 현장에서 알려드립니다. 아래 링크를 참조하여 주십시오.

    [Container Workshop 2017](https://docs.google.com/spreadsheets/d/1ptFys3dCTHiFYZxrcshd7XuoSzLxrsxmwQCtZ91Kr74/edit?usp=sharing)
* 제공되는 아마존 인스턴스의 VNC 접속 패스워드는 **student** 입니다. 
* 아래 주소에서 로컬 시스템에 맞는 VNC 설치 프로그램을 다운로드해 설치합니다. 

    [https://www.realvnc.com/en/connect/download/vnc/](https://www.realvnc.com/en/connect/download/vnc/)

* 워크샵에서 제공하는 아마존 VM ssh 접속을 위한 키 파일입니다. 아마존 VM 접속 정보와 키 파일은 워크샵 현장에서 알려드립니다.   

    [container-workshop-20170923-tokyo](./guest-tokyo.pem)
   

```
# ssh -i [키 파일 경로] ec2-user@[아마존 VM 공개 DNS 이름/IP 주소]
$ ssh -i /path/my-key-pair.pem ec2-user@ec2-198-51-100-1.compute-1.amazonaws.com
```

## 호스트 이름 설정

실습 시스템의 호스트 이름 **workshop** 뒤에 실습 참석자의 번호를 입력합니다. 
아래 예에서는 01 을 추가했습니다. 
실습 참석자는 각자에게 부여된 번호를 입력합니다. 
호스트 이름을 반영하기 위해서는 로그 아웃 후 다시 로그인 합니다.
재로그인 후 쉘 프롬프트에 호스트 이름이 정상적으로 수정되어 표시 되지 않는 경우 시스템을 재시작 합니다.

```
ec2-user $ sudo su -
root $ hostnamectl set-hostname workshop01
root $ hostnamectl status
root $ exit
```

hosts 파일에 실습 참석자 호스트 IP와 호스트 이름을 추가합니다. 
호스트 IP는 "ip a" 명령을 이용해 확인합니다. 
**eth0** 의 IP 주소를 확인합니다
```
root $ ip a
root $ gedit /etc/hosts
```
/etc/hosts :
```
...
172.31.6.241 workshop01
...
```

## 실습 사용자 생성

실습에 제공된 아마존 VM 에는 이미 student 계정이 생성되어 있습니다. root 계정으로 워크샵 실습 사용자 계정 **student** 을 생성합니다. 
워크샵에서 사용하는 사용자 패스워드는 **student** 입니다. 

```
root $ useradd -m -s /bin/bash student
root $ echo 'student:student' | chpasswd
root $ usermod -aG wheel student
``` 

## 실습 사용자 sudo 사용 설정

실습에 제공된 아마존 VM 에는 이미 student 계정의 sudo 사용이 설정되어 있습니다. 
실습 사용자 student 계정이 패스워드 없이 sudo 를 사용할 수 있게 설정을 추가합니다.

```
root $ visudo
```

/etc/sudoers :

```
...
ec2-user        ALL=(ALL)       NOPASSWD: ALL
student         ALL=(ALL)       NOPASSWD: ALL
```

## 유틸리티 설치

실습에 제공된 아마존 VM 에는 jq를 제외한 유틸리티들이 이미 설치되어 있습니다.
원할한 실습 진행을 위해 몇몇 유틸리티를 설치합니다.


```
root $ yum install -y wget git bash-completion telnet 
```
jq는 JSON 데이터를 위한 sed 같은 도구입니다. 
jq는 sed, awk, grep 와 같이 텍스트로 재생할 수 있도록 구조화된 
데이터를 슬라이스, 필터링, 매핑 및 변환에 사용됩니다.


```
root $ curl -LO https://github.com/stedolan/jq/releases/download/jq-1.5/jq-linux64
root $ chmod +x ./jq-linux64
root $ mv ./jq-linux64 /usr/local/bin/jq
```

 