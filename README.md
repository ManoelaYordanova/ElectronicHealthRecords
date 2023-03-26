# ElectronicHealthRecords

## Backend setup:

##### 1. Inside the `src/main/resources` folder create a new file called `application.properties` with the following structure (Remember to add the PostgreSQL connection string, username and password):
```
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect
```
##### 2. Inside the `src/main/java/com.group12.ElectronicHealthRecords` create a new folder called `config`, containing JWTConfig.java file with the following structire:
```
public class JWTConfig {
    public static final String JWT_SECRET = "your jwt secret here";
}
```
