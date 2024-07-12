Before running the application please update the application.properties file uncomment the driverClassName property

**#spring.datasource.driverClassName=org.postgresql.Driver**

In docker-compose.yml file  correct the path as per your computer : **
    volumes:
      - ~/Documents/postgres-data/:/var/lib/postgresql/data**


Before running application please create package using mvn 
**mvn clean package**


To run the unit test cases comment the line of driver class in application.properties file,

For API documentation here is swagger URL : 
http://localhost:8080/swagger-ui/index.html



