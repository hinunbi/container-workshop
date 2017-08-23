Lab 06 - Kubernets 환경에서 애플리케이션 배포와 실행
===

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  

## mysql 파드 실행

```
root $ su - student
student $ cd ~/container-workshop/lab06
student $ kubectl create -f mysql-deployment.yml 
student $ kubectl describe deployment mysql
student $ kubectl get pods -l app=mysql
```

## mysql 서비스 노출

```
student $ kubectl expose deployment mysql --type=LoadBalancer --name=mysql-service
student $ kubectl get service mysql-service
student $ kubectl 
...
kubectl delete services mysql-service

```

## cats 파드 실행

```
root $ su - student
student $ ~/container-workshop/lab06
student $ kubectl create -f cats-deployment.yml 
student $ kubectl describe deployment cats
student $ kubectl get pods -l app=cats
```
## cats 서비스 노출

```
student $ kubectl expose deployment mysql --type=LoadBalancer --name=mysql-service
student $ kubectl get service mysql-service
student $ kubectl 
...
kubectl delete services mysql-service