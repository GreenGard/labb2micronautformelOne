Test formula-app with content type applicationJSON

GET localhost:8080/drivers

POST localhost:8080/drivers 

{
  "name" : "Sebastian",
  "specification" : "the best race car driver in the world"
}





How to run:

Create network called cars
```
docker network create cars
```
Create volume for database
```
docker volume create cars
```
Start container
```
docker run -d --name cars --network cars -p 27017:27017 mongo
```
Start the app
```
docker run -d --name formula -app --network cars -p 8080:8080 -e MONGO_HOST=mongo greengard/labb2micronaut:0.1

```
