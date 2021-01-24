# springboot-auth
Spring boot Authentication and Authorization Server using oauth2

    port : 9000
## Keystore

Create a keystore to create oauth tokens. 

    https://github.com/karthiga/springboot/wiki/Create-Keystores-and-keypair

## Database
It creates an inmemory database with the following schema

USERS

           ID  | USERNAME   | PASSWORD     
          -----| -----------|----------
           1   | admin      | password
           
ROLES

           ID  | ROLENAME        
          -----| -----------
           1   | ROLE_ADMIN 
           2   | ROLE_USER
     

USER_ROLE_MAPPING

           ID  | USERID  | ROLEID     
          -----| --------|----------
           1   |   1     |   1 
           
## Authenticate any operation protected by the zuulproxy using Bearer oauth token in the Authorization header

    curl --location --request POST 'http://localhost:9000/oauth/token' \
    --header 'Authorization: Basic YmFja3lhcmQ6cGFzc3dvcmQ=' \
    --header 'Content-Type: application/x-www-form-urlencoded' \
    --header 'Cookie: JSESSIONID=E18EC4879E10559B6F00AFDBA5450EDF' \
    --data-urlencode 'grant_type=password' \
    --data-urlencode 'username=user' \
    --data-urlencode 'password=password'
           
# Softwares
  Install an IDE eclipse/intellij <br />
  Install lombok jar <br /> 
    https://www.baeldung.com/lombok-ide

# Running the spring boot application
  Download the zip or clone the Git repository. <br />
    Unzip the zip file (if you downloaded one) <br />
  Open Command Prompt and Change directory (cd) to folder containing pom.xml <br />
  Open Eclipse <br />
  File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip <br />
  Select the right project <br />
  Choose the Spring Boot Application file (search for @SpringBootApplication) <br />
  Right Click on the file and Run as Java Application  <br />
