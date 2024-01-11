# Challenge
## Installation
1. Clone the repository: git clone https://github.com/mehmethcifci/Enoca_Challenge.git
2. The project runs locally. Firstly, manually create a database using pgAdmin.
3. Configure the database name, username, and password you created in the YAML file. Also, define the port information and path where the application will run in the YAML file.
   The database URL for this application is 'jdbc:postgresql://localhost:5432/EnocaChallengeDB', and it runs on port 'localhost:8080/api/v1'.
4. Build the Gradle.
5. Download Apache Solr Binary Distribution from below link : Apache Solr
6. Introduce the path to the 'bin' directory within the Solr folder you downloaded through the environment variable 'Path'.
7. Open a command prompt inside the 'bin' directory and enter the command 'solr start'.
8. Run the application.

## Used Technologies
- Java 17
- Spring Boot 3.0.1
- Spring Boot Data Jpa
- Spring Boot Web
- Spring Boot DevTools
- Gradle 7.4
- Lombok
- PostgreSQL 42.5.0
- Solrj 9.4.0

## Additional Information
In the application, there are two classes named 'Company' and 'Employee,' and these classes are related to each other. 
The application primarily performs CRUD operations. In addition, it can retrieve data from Solr by querying for records after a certain date.

##### Used Endpoints
- localhost:8080/api/v1/company/create
- localhost:8080/api/v1/company/update
- localhost:8080/api/v1/company/findall
- localhost:8080/api/v1/company/delete/{id}
- localhost:8080/api/v1/company/getbyupdatedat/{updatedAt} (It should be in the 'yyyy-MMM' format for the 'updatedAt' field.)
- localhost:8080/api/v1/employee/create
- localhost:8080/api/v1/employee/update
- localhost:8080/api/v1/employee/findall
- localhost:8080/api/v1/employee/delete/{id}
