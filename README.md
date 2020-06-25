### docker-mysql

```docker
docker run -d -p 3306:3306 --name mysql3306 -e MYSQL_ROOT_PASSWORD=xxx -v /home/HiramHe/docker/mysql/conf.d/my.cnf:/etc/mysql/conf.d/my.cnf -v /home/HiramHe/docker/mysql/log:/var/log/mysql -v /home/HiramHe/docker/mysql/data:/var/lib/mysql -v /home/HiramHe/docker/mysql/mysql-files:/var/lib/mysql-files mysql:latest
```

