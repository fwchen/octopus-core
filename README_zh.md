
# Octopus Core Service

## 环境

###  MinIO
需要使用到 [minio](https://min.io/)

可以通过 Docker 命令启动：
``` bash
docker run -p 9000:9000 -d --name=minio -e MINIO_ACCESS_KEY=minio -e MINIO_SECRET_KEY=miniostorage minio/minio server /data
```

### RabbitMQ
`docker-compose.yml`

``` yaml
rabbitmq:
  image: rabbitmq:management
  ports:
    - "5672:5672"
    - "15672:15672"
```

```
docker run -p 5672:5672 -p 15672:15672 rabbitmq:management -d
```

### MongoDB
```
docker run --name some-mongo -p 27017:27017 -d mongo:4.4.1-bionic
```
