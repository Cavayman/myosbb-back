# myosbb
Project “OSBB” – a web-application which is  a godsend for condominium head, managers and residents.It offers a very easy way to manage accounting and residents, events and organizational issues.It represents a simple design and great functionality that is needed  for managing.In this project you'll see the following technologies: 
Spring Data JPA
Spring Boot
Spring Oauth2.0 (Jwt based)
Hibernate
Spring MVC 
Spring Security 
Liquibase
RESTful resource server 
Angular2 


HOW TO RUN PROJECT 
You need to install external libraries:
1) install nodejs
2) run "npm install" in ../myosbb/web/src/main/resources/public/
3) run "npm start" in ../myosbb/web/src/main/resources/public/

***
How to run project with profile:
- you should add "-P" and profile name at the end of command without space
- for example: "mvn clean install -Ppostgres"
by default is used profile "main"

How to use external properties:
- you should add "-D" and full path to property file in deployment package without space
- for example: "mvn clean install -Dspring.config.location=/home/nataliia/myosbb1/deployment/external.properties"

You can add this command to "Edit Configuration" in Intellij IDEA to field "VM Options",
then you environment will run them every time.
***


How to use git:
1) git add .
2) git commit -a (opens a notepad instance --- > enter commit description) ( -m inline comment)
3) git pull --rebase
3) If you have conflicts ---> resolve them, git add . ,  git rebase --continue
4) git push

LVJAVAAA-74: Commit Description.

How to run project with Tomcat8.
1) install Tomcat8
2) run project with it

