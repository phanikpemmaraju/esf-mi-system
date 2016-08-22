REM ++---------------------------------------------------------------++
REM     This scripts starts NGINX, Mongo and the microservice!
REM ++---------------------------------------------------------------++
set NGINX_HOME=C:\apps\nginx\nginx-1.8.1
set MONGO_HOME=C:\apps\mongoDB
set PROJECT_LOCATION=C:\checkouts\esf-mi-system

REM Start NGINX
c:\
cd %NGINX_HOME%
start nginx.exe

REM Start Mongo (ensure data\db directory exists)
cd %MONGO_HOME%\Server\3.2\bin\
start mongod.exe

REM Start Micro-service
start java -jar -Dspring.profiles.active=dev %PROJECT_LOCATION%\esf-mi-ms\target\esf-mi-ms-0.0.1-SNAPSHOT.jar
cmd /k
