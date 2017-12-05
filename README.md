# Example Project for demonstration purposes only. Showing the integration of Maven with Jenkins, git and Nexus.

## Branches

| Branch | Description |
| ------ | ---------- |
| master | - | 
| develop | simple Java Project | 
| hotfix | - | 
| docker-infrastructure | docker-compose file with settings for Nexus, Jenkins, gitlab |

## How to work with git:

```
git help -a
git help config

git config user.name "Vorname Nachname"
git config user.email "name@example.com"

mkdir git-course-examples
cd git-course-examples
git init

echo 'Hello, world' > README
git add .
git commit 

vi README
# ! hinzufügen
git status
git add README
git commit -m 'Fixed'
git log

vi .gitignore
# .project
#
git add .gitignore 
git commit -m 'add gitignore'
echo 'foo' > .project
git status

vi README
# make some changes
git add README 
git commit -m 'some silly changes'
git log --oneline
git revert HEAD
git revert <commit>

git reset <commit>
git reset --hard <commit>

git checkout -- .gitignore

git branch feature/a
git branch
git checkout feature/a

vi helloWorld.sh
#!/bin/bash 
echo 'Hello, world!'
#eof

git add helloWorld.sh 
git commit -m 'add shell script'

git checkout -b crazy-experiment
git checkout feature/a
git branch -d crazy-experiment


git checkout master
git merge feature/a
git branch -D feature/a

git checkout -b feature/b
vi helloWorld.sh
# make changes
git add helloWorld.sh 
git commit -m 'implement feature b'

git checkout master
vi .gitignore
# make changes
git add .gitignore
git commit -m 'update files to ignore'

git checkout feature/b
git rebase master

git checkout -b feature/c
vi helloWorld.sh 
# make changes
git add helloWorld.sh
git commit -m 'implement feature c'
git checkout master
vi .gitignore 
git add .gitignore 
git commit -m 'add .settings'
git checkout feature/c
git checkout -b hotfix
git branch
vi helloWorld.sh 
# make changes
git add helloWorld.sh 
git commit -m 'hotfix implemented'
git rebase --onto master feature/c hotfix

git tag -a v0.1 -m 'a nice fix'
git describe --contains <commit>

git remote -v
git remote add origin https://github.com/tfr42/git-course-examples.git
git remote add local_fs file:///home/user/git-course-examples.git

git fetch
git pull

git push 
```
