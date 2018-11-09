Build Pipeline for Jenkins with Docker Integration


# How to build the Docker image with Docker

```
docker build -t ci/helloworld:latest .
```
# How to build the Docker image with Maven

```
mvn clean install fabric8:build 
```

# How to run a Docker Container based on the Docker Image

```
docker run --rm ci/helloworld:latest
```
