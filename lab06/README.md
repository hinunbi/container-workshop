Lab 06 - Kubernets 환경에서 애플리케이션 배포와 실행
===

* 이 문서는 Red Hat Enterprise Linux 를 기반으로 작성됐습니다.
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
student $ kubectl expose deployment mysql --session-affinity=ClientIP
student $ kubectl get service mysql
student $ kubectl describe service mysql
```

## cats 파드 실행

```
root $ su - student
student $ cd ~/container-workshop/lab06
student $ kubectl create -f cats-deployment.yml 
student $ kubectl describe deployment cats
student $ kubectl get pods -l app=cats
student $ kubectl exec -it cats-121008331-nckdp -- sh
```
## cats 서비스 노출

```
student $ kubectl expose deployment cats --session-affinity=ClientIP
student $ kubectl get service cats
student $ kubectl describe service cats

```
kubectl get 또는 describe service 명령으로 찾은 cat 서비스의 IP 를 가상 머신 내 웹 브라우저를 이용해 접속합니다. 
서비스 IP 가 "10.106.18.56"인 경우 웹 브라우저에서 "http://10.106.18.56:8080/" 로 접속을 시도합니다.
cats 실시간 트위어 고양이 이미지를 확인할 수 있습니다. 
참고로 cats 서비스의 IP 주소는 Kubernetes의 가상 네트워크의 IP 주소입니다. 
그러므로 가상 머신 외부에서는 cats 서비스에 접속할 수 없습니다.   

## cats 파드 실행 개수 조정

kubernets 는 scale 명령으로 파드 개수를 간단히 늘이거나, 줄일 수 있습니다

```
student $ kubectl scale deployment cats --replicas=3
```

## 사용된 파드 맻 서비스 제거

실습에서 사용된 파드와 서비스를 제거합니다. 

```
kubectl delete services cats
student $ kubectl delete deployment cats
kubectl delete service mysql
student $ kubectl delete deployment mysql
```