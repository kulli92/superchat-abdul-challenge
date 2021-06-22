# superchat.abdul project



## Running the application in dev mode

1- You can run your application in dev mode that enables live coding using:
```shell script
 mvn compile quarkus:dev 
```
2- please import challenge.postman_collection.json file to your postman app in order to test the application


##  running the application using docker
an image for the application uploaded to docker (https://hub.docker.com/r/klli/superchat-coding-challenge)
1- on terminal run  "docker pull klli/superchat-coding-challenge"
2- the "docker run -i --rm -p 8083:8083 klli/superchat-coding-challenge" command
3- test from postman
you can load it and run locally in you docker app...
