GET localhost:8080/drivers

POST localhost:8080/drivers 

{
  "name" : "Allan",
  "specification" : "snabb som bara den"
}





How to run:

```
          docker network create hej
```

```
            docker run -d --name hejsan --network hej -p 27017:27017 mongo
```
```
          docker run -d --name hejsan --network hej -p 8080:8080 -e MONGO_HOST=mongo greengard/labb2micronautformelone
```
