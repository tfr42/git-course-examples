# How to build the Docker image

```
mvn clean install fabric8:build 
```

# How to run a Docker Container based on the Docker Image

```
docker run --rm ci/helloworld:latest
``` 