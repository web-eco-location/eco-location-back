### 배포 방법
- 배포 환경 : centos-release-7-9.2009.1.el7.centos.x86_64

#### 배포 과정 기록
1. ~.pem파일을 받았음
2. puttygen을 사용하여 pem파일을 ppk파일로 변환(save private key)
3. putty 설정 - session에서 ip, port, ssh 지정
4. putty 설정 - connection-Data에서 username 지정
5. putty 설정 - connection-Auth-Credentials에서 ppk 경로 지정
6. putty 설정 - session에서 현재 설정을 저장해둘 수 있다.
7. docker 설치

#### CentOS에 docker 설치
참조 https://docs.docker.com/engine/install/centos/
- yum update
```
$ sudo yum -y update
$ sudo yum install -y yum-utils(이미 최신 버전)
```
- Docker repository 시스템에 추가
```
$ yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
$ yum-config-manager --enable docker-ce-nightly
```
- Docker 설치
```shell
$ sudo yum -y install docker-ce docker-ce-cli containerd.io
```
- Docker 실행, 확인, 종료
```shell
$ sudo systemctl start docker
$ sudo systemctl status docker
$ sudo systemctl stop docker

권한 에러 발생 시
permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/images/json": dial unix /var/run/docker.sock: connect: permission denied
$ sudo chmod 666 /var/run/docker.sock
```

#### docker로 mariaDB 띄우기
```shell
$ docker pull mariadb
$ docker run --name mariadb -d -p 3306:3306 --restart=always -e MYSQL_ROOT_PASSWORD={초기 root 비밀번호} mariadb
```
- mariadb 유저, 테이블 생성
```shell
$ docker exec -it mariadb /bin/bash
$ mysql -u root -p
/ CREATE USER '{유저명}'@'%' IDENTIFIED BY '{비밀번호}';
/ grant all privileges on {스키마 명}.* to 'tester'@'%';
/ flush privileges;
```