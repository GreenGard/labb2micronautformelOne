How to run:

```
            docker volume create driver
```
```
            docker network create drivers
```
```
            docker run -d --network drivers--name <mongodb -p 8080:8080 mongo:latest
```
```
            mvnw package -Dpackaging=docker-native -Pgraalvm
```
