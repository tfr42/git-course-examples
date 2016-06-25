mvn archetype:generate -DgroupId=net.gfu.seminar.ci -DartifactId=helloWorld -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
git remote add gitlab http://192.168.99.100:8083/root/helloWorld.git
git push -u gitlab HEAD:master 
