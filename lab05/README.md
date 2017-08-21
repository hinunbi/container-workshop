Lab 05 - Kubernetes 설치
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

## kubectl bash autocompletion 기능 추가
```
$ echo "source <(kubectl completion bash)" >> ~/.bashrc
```


## kubelet 설치, 실행

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

kubeadm init 은 최초로 실행하면 아래와 같은 오류를 발생시킵니다. 
이 오류를 해결하기 위해서는 kubeadm 설정 파일의 수정이 필요합니다.

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

### kubeadm 설정 파일 수정
kubeadm 최토 실행 후 아래와 같이 kubeadm 설정 파일에서 **--cgroup-driver=cgroupfs** 로 오류를 수정합니다.

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
오류가 수정된 10-kubeadm.conf : 



/etc/systemd/system/kubelet.service.d/10-kubeadm.conf : 
```
...
Environment="KUBELET_CGROUP_ARGS=--cgroup-driver=cgroupfs"
...
```

## kubeadm 다시 실행


```
root $ systemctl daemon-reload
root $ setenforce 0
root $ kubeadm reset
root $ kubeadm init

[[kubeadm] WARNING: kubeadm is in beta, please do not use it for production clusters.
 [init] Using Kubernetes version: v1.7.4
 [init] Using Authorization modes: [Node RBAC]
 [preflight] Running pre-flight checks
 [preflight] WARNING: firewalld is active, please ensure ports [6443 10250] are open or your cluster may not function correctly
 [preflight] Starting the kubelet service
 [kubeadm] WARNING: starting in 1.8, tokens expire after 24 hours by default (if you require a non-expiring token use --token-ttl 0)
 [certificates] Generated CA certificate and key.
 [certificates] Generated API server certificate and key.
 [certificates] API Server serving cert is signed for DNS names [teacher kubernetes kubernetes.default kubernetes.default.svc kubernetes.default.svc.cluster.local] and IPs [10.96.0.1 192.168.181.62]
 [certificates] Generated API server kubelet client certificate and key.
 [certificates] Generated service account token signing key and public key.
 [certificates] Generated front-proxy CA certificate and key.
 [certificates] Generated front-proxy client certificate and key.
 [certificates] Valid certificates and keys now exist in "/etc/kubernetes/pki"
 [kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/kubelet.conf"
 [kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/controller-manager.conf"
 [kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/scheduler.conf"
 [kubeconfig] Wrote KubeConfig file to disk: "/etc/kubernetes/admin.conf"
 [apiclient] Created API client, waiting for the control plane to become ready
 [apiclient] All control plane components are healthy after 29.000947 seconds
 [token] Using token: e10759.5e9336f1a93d2a0b
 [apiconfig] Created RBAC rules
 [addons] Applied essential addon: kube-proxy
 [addons] Applied essential addon: kube-dns
 
 Your Kubernetes master has initialized successfully!
 
 To start using your cluster, you need to run (as a regular user):
 
   mkdir -p $HOME/.kube
   sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
   sudo chown $(id -u):$(id -g) $HOME/.kube/config
 
 You should now deploy a pod network to the cluster.
 Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
   http://kubernetes.io/docs/admin/addons/
 
 You can now join any number of machines by running the following on each node
 as root:
 
   kubeadm join --token e10759.5e9336f1a93d2a0b 192.168.181.62:6443
```


## student 를 Kubernetes 클러스터 접속 계정으로 설정
```
root $ su - student
student $ mkdir -p $HOME/.kube
student $ sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
student $ sudo chown $(id -u):$(id -g) $HOME/.kube/config

```

## Kubernetes Weave Net 설치

Kubernetes 네트워크를 Weave Net 으로 설치합니다. 
Kubernetes 클러스터에 네트워크를 설치하면 노드 정보를 조회할 수 있게 됩니다.
그러나 네크워크를 설치하더라도, 아직 로컬 시스템이 애플리케이션 노드로 전환되지 않았습니다 
```
student $ export kubever=$(kubectl version | base64 | tr -d '\n')
student $ kubectl apply -f "https://cloud.weave.works/k8s/net?k8s-version=$kubever"
student $ kubectl get node
NAME      STATUS     AGE       VERSION
teacher   NotReady   6m        v1.7.3
```

## 로컬 시스템을 Kubernetes 애플리케이션 노드로 전환
아래 명령을 실행해 로컬 시스템이 Kubernetes 파드 실행 노드가 되게 전환합니다. 
```
student $ kubectl taint nodes --all node-role.kubernetes.io/master-
student $ kubectl get nodes
NAME      STATUS    AGE       VERSION
teacher   Ready     8m        v1.7.3

student $ kubectl get pod --all-namespaces 

NAMESPACE     NAME                              READY     STATUS             RESTARTS   AGE
kube-system   etcd-teacher                      1/1       Running            0          31m
kube-system   kube-apiserver-teacher            1/1       Running            0          31m
kube-system   kube-controller-manager-teacher   1/1       Running            0          31m
kube-system   kube-dns-2425271678-lg0zp         1/3       CrashLoopBackOff   15         32m
kube-system   kube-proxy-c345j                  1/1       Running            0          32m
kube-system   kube-scheduler-teacher            1/1       Running            0          31m
kube-system   weave-net-qlb1f                   2/2       Running            0          26m
```
