# Spring Boot Simple Project

About Spring Boot base project architecture.

## Configuration

### Redis

```bash
docker pull redis:alpine
docker run --name demo_redis -p 6379:6379 -d redis:alpine
```

### MariaDB

```bash
MYSQL_ROOT_PASSWORD = 123456a@
```

```bash
docker pull mariadb:10
docker run --name demo_mariadb -p 3360:3306 -e MYSQL_ROOT_PASSWORD=123456a@ -d mariadb:10 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

### MySQL

```bash
MYSQL_ROOT_PASSWORD = 123456a@
```

```bash
docker pull mysql:8
docker run --name demo_mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456a@ -d mysql:8 --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
```

### SQL Server

```bash
SA_PASSWORD = 123456a@
```

```bash
docker pull mcr.microsoft.com/mssql/server:2019-latest
docker run --name demo_sqlserver -e ACCEPT_EULA=Y -e SA_PASSWORD=123456a@ -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
```

### SonarQube

```bash
docker pull sonarqube:8-community
docker run -d --name demo_sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:8-community
```

### Docker Build Image

```bash
./gradlew build && docker build -t spring-boot-base-project:1.0 .
```

## Architecture

```
spring-boot-bestpracite
├── main
│   ├── java
│   │    └── com.example.baseproject
│   │       ├── common
│   │       │   ├── config
│   │       │   ├── filter
│   │       │   ├── mapper
│   │       │   ├── validation
│   │       │   ├── constant
│   │       │   └── util
│   │       │
│   │       ├── controller
│   │       │
│   │       ├── exceptions
│   │       │
│   │       ├── model
│   │       │   ├── bo
│   │       │   ├── entity
│   │       │   ├── request
│   │       │   └── response
│   │       │
│   │       ├── repository
│   │       │
│   │       ├── service
│   │       │   └── impl
│   │       │
│   │       └── Application.java
│   │ 
│   └── resources
│       └── application.yml
└── test
    └── ApplicationTests.java
```
