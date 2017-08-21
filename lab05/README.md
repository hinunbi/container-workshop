Lab 05 - Kubernetes 설치 및 Pod 실행
===

* 이 문서는 Red Hat Enterprise Linux 7.3 을 기반으로 작성됐습니다.
* 반드시 명령 실행은 프롬프트 앞에 *표시된 계정* 으로 명령을 실행합니다.  


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

## kubectl 설치

kubectl은 Kubernetes 클러스터의 명령을 실행하기 위한 명령행 인터페이스입니다.

* 이후 과정은 root 계정으로 실행합니다.
```
root $ cd /usr/local/bin
root $ curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl       
root $ chmod +x ./kubectl
root $ kubectl version
```

## kubelet 설치, 패치, 실행

kubelet은 각 노드에서 실행되는 기본 "노드 에이전트"입니다.

### kubelet 설치, 실행
아래 명령을 실행해 kubelet을 로컬 시스템에 설치합니다.
```   
root $ yum install -y kubelet
root $ setenforce 0
root $ systemctl enable kubelet 
root $ systemctl start kubelet

```

## kubeadm 설치

```
root $ yum install -y kubeadm
```

## kubeadm 최초 실행

kubeadm init 을 최초로 실행하면 아래와 같은 오류를 발생시킵니다. 
이 오류를 해결하기 위해서는 kubeadm 설정 파일의 패치가 필요합니다.

```
root $ setenforce 0
root $ kubeadm init

[kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
[init] Using Kubernetes version: v1.7.4
[init] Using Authorization modes: [Node RBAC]
[preflight] Running pre-flight checks
[preflight] WARNING: firewalld is active, please ensure ports [6443 10250] are open or your cluster may not function correctly
[preflight] Some fatal errors occurred:
        Port 10250 is in use
        /var/lib/kubelet is not empty
[preflight] If you know what you are doing, you can skip pre-flight checks with `--skip-preflight-checks`

```


### kubeadm 설정 파일 패치
kubeadm 최토 실행 후 아래와 같이 kubeadm 설정 파일에서 **--cgroup-driver=cgroupfs** 로 패치합니다.

이슈 참조) [https://github.com/kubernetes/kubernetes/issues/43805](https://github.com/kubernetes/kubernetes/issues/43805)

```   
root $ gedit /etc/systemd/system/kubelet.service.d/10-kubeadm.conf
```

원래 10-kubeadm.conf : 

```
...
Environment="KUBELET_CGROUP_ARGS=--cgroup-driver=systemd"
...
```
수정된 10-kubeadm.conf : 



/etc/systemd/system/kubelet.service.d/10-kubeadm.conf : 
```
...
Environment="KUBELET_CGROUP_ARGS==--cgroup-driver=cgroupfs"
...
```

## kubeadm 다시 실행


```
root $ setenforce 0
root $ systemctl daemon-reload
root $ kubeadm reset
root $ kubeadm init

[kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
[init] Using Kubernetes version: v1.7.4
[init] Using Authorization modes: [Node RBAC]
[preflight] Running pre-flight checks
[preflight] WARNING: firewalld is active, please ensure ports [6443 10250] are open or your cluster may not function correctly
[preflight] Some fatal errors occurred:
        Port 10250 is in use
        /var/lib/kubelet is not empty
[preflight] If you know what you are doing, you can skip pre-flight checks with `--skip-preflight-checks`

```