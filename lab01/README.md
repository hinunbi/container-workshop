Lab one - 사전 준비 
===

이 워크샵은 전체 실습을 위한 사전 준비 환경을 안내합니다 

## 실습 사용자 생성

root 계정으로 워크샵 사용자 계정(student)을 생성합니다. 
워크샵에서 사용하는 사용자 패스워드는 student 입니다.
```
root $ useradd -m -s /bin/bash student
root $ echo 'student:student' | chpasswd
root $ usermod -aG wheel student
``` 
 
 