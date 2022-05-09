# ProjectExpenser
Xpenser App

Build
run build.bat

Run
run backend.bat
run the app at port 8080

#################Configuration that needs to be changed before build in application.properties##################################
#We are using oracle DB as the database

spring.datasource.username=<DB_USERNAME>
spring.datasource.password=>DB_PASSWORD>

#We are using Gmail to send mail via JAVA Mail 
spring.mail.username=<GMAIL_USERNAME>
spring.mail.password=<GMAIL_PASSWORD>

#We have to change the root directory of our project as per your deployment
spring.thymeleaf.prefix=file:///<YOUR_INSTALLTION_DIRECTORY>//target//classes//static/

################################################################################################################################

How to develop
run frontend.bat (frontend will run at PORT 4000, send API requests to backend at PORT 8080 via proxy)
run backend.bat (backend will run at 8080)







