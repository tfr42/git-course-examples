# How to create a simple Maven project 

```
mvn archetype:generate -DgroupId=net.gfu.seminar.ci -DartifactId=helloWorld -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
git remote add gitlab http://localhost:8083/tf/helloWorld.git
git push -u gitlab HEAD:master 
```