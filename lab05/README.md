Lab 05 - Kubernetes 설치 및 Pod 실행
===

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


## kubectl 설치

kubectl은 Kubernetes 클러스터의 명령을 실행하기 위한 명령행 인터페이스입니다.

```
root $ cd /usr/local/bin
root $ curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl       
root $ chmod +x ./kubectl
root $ kubectl version
```

## Kubernetes yum 레파지토리 등록

kubeadm 을 설치하기 위한 yum 레파지토리를 로컬 시스템에 등록합니다.
 
```
root $ su - student
student $ cd ~/container-workshop/lab05
student $ sudo cp kubernetes.repo /etc/yum.repos.d
student $ sudo yum repolist -y
student $ sudo yum makecache
```

kubernetes.repo :

```
[kubernetes]
name=Kubernetes
baseurl=https://packages.cloud.google.com/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=1
repo_gpgcheck=1
gpgkey=https://packages.cloud.google.com/yum/doc/yum-key.gpg
       https://packages.cloud.google.com/yum/doc/rpm-package-key.gpg
```
