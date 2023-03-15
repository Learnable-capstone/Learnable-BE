## database (local) [Apple Silicon 아닌 경우]

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 실행

```shell
docker-compose -f docker-compose-local.yml up -d
```

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 종료

```shell
docker-compose -f docker-compose-local.yml down
```

---

## database (local) [Apple Silicon 인 경우]

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 실행

```shell
docker-compose -f docker-compose-local-m.yml up -d
```

도커를 이용한 MySQL 로컬 데이터베이스 컨테이너 종료

```shell
docker-compose -f docker-compose-local-m.yml down
```