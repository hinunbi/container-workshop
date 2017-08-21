Lab two - 애플리케이션 개발, 빌드
===

이 실습에서는 전통적인 애플리케이션 개발 빌드 실행을 안내합니다. 

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## 개발 도구 설치
   
   이미 설치되어 있지 않은 경우, Open JDK 를 설치합니다.

```bash
root $ sudo yum install -y java-1.8.0-openjdk-devel
root $ java -version
```
   
   Maven 개발 도구를 설치하고 환경 변수를 등록 합니다.
```bash
root $ cd /opt
root $ wget http://mirror.apache-kr.org/maven/maven-3/3.5.0/binaries/apache-maven-3.5.0-bin.tar.gz
root $ tar xzf apache-maven-3.5.0-bin.tar.gz
root $ ln -s apache-maven-3.5.0 maven
root $ sh -c 'echo -e "export M2_HOME=/opt/maven\nexport PATH=/opt/maven/bin:${PATH}\n" > /etc/profile.d/maven.sh' 
root $ source /etc/profile.d/maven.sh
root $ rm -rf apache-maven-3.5.0-bin.tar.gz
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

cats 애플리케이션 패키지를 생성합니다. 
아래 과정이 완성되면 cats-1.0.jar 패키지 파일이 생성됩니다. 
생성된 cats-1.0.jar 패키지는 이후 실습에서 Docker 이미지에 포함될 것입니다.

```bash
student $ cd ~/container-workshop/cats
student $ mvn clean package -DskipTests
student $ ls -al target/*.jar
-rw-rw-r--. 1 student student 44158463  8월 21 16:42 target/cats-1.0.jar
```
 