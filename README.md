# superchat.abdul project



## Running the application in dev mode

1- You can run your application in dev mode that enables live coding using:
```shell script
 mvn compile quarkus:dev 
```
2- please import challenge.postman_collection.json file to your postman app in order to test the application


##  running the application using docker
an image for the application uploaded to docker (https://hub.docker.com/r/klli/superchat-coding-challenge)  
1- on terminal run  
```shell script
 docker pull klli/superchat-coding-challenge
```
2- then run the docker image with this command
```shell script
 docker run -i --rm -p 8083:8083 klli/superchat-coding-challenge
```
3- test from postman  
