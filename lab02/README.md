Lab two - 애플리케이션 개발, 빌드, 실행
===

이 실습에서는 전통적인 애플리케이션 개발 빌드 실행을 안내합니다. 

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## 개발 도구 설치
   
   이미 설치되어 있지 않은 경우, Open JDK 를 설치합니다.

```bash
root $ sudo yum install -y java-1.8.0-openjdk-devel
root $ sudo yum install -y java-1.8.0-openjdk-devel  --downloadonly --downloaddir=/var/www/html/repo
root $ java -version
```
   
   Maven 개발 도구를 설치하고 환경 변수를 등록 합니다.
```bash
root $ cd /opt
root $ sudo wget http://mirror.apache-kr.org/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz
root $ sudo tar xzf apache-maven-3.5.0-bin.tar.gz
root $ sudo ln -s apache-maven-3.5.0 maven
root $ sudo sh -c 'echo -e "export M2_HOME=/opt/maven\nexport PATH=/opt/maven/bin:${PATH}\n" > /etc/profile.d/maven.sh' 
root $ source /etc/profile.d/maven.sh
root $ sudo rm -rf apache-maven-3.5.0-bin.tar.gz
root $ mvn -version
```   

## 사용자 계정 전환
   
   애플리케이션 개발 빌드, 실행은 개발자 계정으로 수행합니다. 
   그러므로 개발자 계정으로 사용자 계정을 전환합니다
```bash
root $ su - student 
student $
```   

## 애플리케이션 복제

```bash
student $ cd ~
student $ git clone https://github.com/hinunbi/container-workshop.git
student $ cd container-workshop
```

## 애플리케이션 빌드

```bash
student $ cd ~/container-workshop/cats
student $ mvn package
student $ ls -al target/*.jar
```
 