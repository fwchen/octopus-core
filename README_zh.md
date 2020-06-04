
# Octopus Core Service

## 开发
需要使用到 [minio](https://min.io/)

可以通过 Docker 命令启动：
``` bash
docker run -p 9000:9000 -d --name=minio -e MINIO_ACCESS_KEY=minio -e MINIO_SECRET_KEY=miniostorage minio/minio server /data
```