APP_NAME = spring-boot-base-project
APP_VERSION = 1.0
BUILD_DIR = ./target

clean:
	rm -rf $(BUILD_DIR)/*

build:
	./gradlew build

docker.mariadb:
	docker run --rm -d \
		--name demo_mariadb \
		-e MYSQL_ROOT_PASSWORD=123456aA@ \
		-v data_mariadb:/var/lib/mysql \
		-p 3306:3306 \
		-d mariadb:10 \
		--character-set-server=utf8mb4 \
		--collation-server=utf8mb4_unicode_ci

docker.mysql:
	docker run --rm -d \
		--name demo_mysql \
		-e MYSQL_ROOT_PASSWORD=123456aA@ \
		-v data_mariadb:/var/lib/mysql \
		-p 3306:3306 \
		-d mysql:8 \
		--character-set-server=utf8mb4 \
		--collation-server=utf8mb4_unicode_ci

docker.sqlserver:
	docker run --rm -d \
		--name demo_sqlserver \
		-e ACCEPT_EULA=Y \
		-e SA_PASSWORD=123456aA@ \
		-p 1433:1433 \
		-d mcr.microsoft.com/mssql/server:2019-latest

docker.redis:
	docker run --rm -d \
	    --name demo_redis \
	    -p 6379:6379 \
	    -d redis:alpine

docker.sonar:
	docker run --rm -d \
    	--name demo_sonarqube \
    	-e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true \
    	-p 9000:9000 \
    	-d sonarqube:8-community


docker.build:
	docker image build -t $(APP_NAME):$(APP_VERSION) .

docker.run:
	docker-compose up --build -d