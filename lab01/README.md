Lab 01 - 사전 준비 
===

이 워크샵은 전체 실습을 위한 사전 준비 환경을 안내합니다. 

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  

## 호스트 이름 설정

실습 시스템의 호스트 이름 **student** 뒤에 실습 참석자의 번호를 입력합니다. 
아래 예에서는 01 을 추가했습니다. 
실습 참석자는 각자에게 부여된 번호를 입력합니다.
DHCP로 할당된 경우도 

```bash
root $ gedit /etc/hostname
```
/etc/hostname : 
```
student01
```
hosts 파일에 실습 참석자 호스트 IP와 호스트 이름을 추가합니다
```bash
root $ gedit /etc/hosts
```
/etc/hosts :
```bash
...
192.168.181.61 student01
192.168.181.210 jcha-OSX.local
...
```

## yum 저장소 등록

실습에서 사용할 yum 경로를 추가합니다. 

```bash
root $ gedit /etc/yum.repos.d/utils.repo
```
/etc/yum.repos.d/utils.repo : 

``` 
[utils-repo]
name=rhel-7-utils
baseurl=http://jcha-osx.local:8000/
enabled=1
gpgcheck=0
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
root $ yum install -y wget git bash-completion 
```
jq는 JSON 데이터를 위한 sed 같은 도구입니다. 
jq는 sed, awk, grep 와 같이 텍스트로 재생할 수 있도록 구조화된 
데이터를 슬라이스, 필터링, 매핑 및 변환에 사용됩니다.


```bash
root $ curl -LO https://github.com/stedolan/jq/releases/download/jq-1.5/jq-linux64
root $ chmod +x ./jq-linux64
root $ mv ./jq-linux64 /usr/local/bin/jq
```

 