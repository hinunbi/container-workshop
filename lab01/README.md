Lab one - 사전 준비 
===

이 워크샵은 전체 실습을 위한 사전 준비 환경을 안내합니다. 

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  

## 호스트 이름 설정

실습 시스템의 호스트 이름 **student** 뒤에 실습 참석자의 번호를 입력합니다. 
아래 예에서는 01 을 추가했습니다. 실습 참석자는 각자에게 부여된 번호를 입력합니다

```bash
root $ gedit /etc/hostname
```
/etc/hostname : 
```
studentend01
```

```bash
root $ gedit /etc/hosts
```
/etc/hosts : 
```bash
...
192.168.181.61 student01
...
```

## yum 저장소 등록

실습에서 사용할 yum 경로를 추가합니다. 

```bash
root $ gedit /etc/yum.repo.d/utils.repo
```
/etc/yum.repos.d/utils.repo : 

```
 [utils-repo]
 name=rhel-7-utils
 baseurl=http://jcha-osx.local:8000/
 enabled=1
 gpgcheck=0
```


## 전역 환경변수 설정

/etc/profile 에 실습에서 사용할 경로를 추가합니다.

```bash
 root $ sh -c 'echo -e "export PATH=$PATH:/usr/local/bin" >> /etc/profile' 
```

## 실습 사용자 생성

root 계정으로 워크샵 실습 사용자 계정 **student** 을 생성합니다. 
워크샵에서 사용하는 사용자 패스워드는 **student** 입니다.

```bash
root $ useradd -m -s /bin/bash student
root $ echo 'student:student' | chpasswd
root $ usermod -aG wheel student
``` 

## 실습 사용자 sudo 사용 설정
실습 사용자 student 계정이 패스워드 없이 sudo 를 사용할 수 있게 설정합니다.


```bash
root $ gedit /etc/sudoers
```

/etc/sudoers :

```
..
## Allows people in group wheel to run all commands
%wheel  ALL=(ALL)       NOPASSWD: ALL
...
```
아래 명령을 실행하면 에디터를 이용하지 않고 수정할 수 있습니다.
```bash 
root $ sed -i 's/^#\s*\(%wheel\s\+ALL=(ALL)\s\+NOPASSWD:\s\+ALL\)/\1/' /etc/sudoers
```

## 유틸리티 설치

원할한 실습 진행을 위해 몇몇 유틸리티를 설치합니다

```bash
root $ yum install -y wget bash-completion 
```


 