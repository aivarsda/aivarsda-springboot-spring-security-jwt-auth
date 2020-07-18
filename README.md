# Spring Boot JWT Authentication with Spring Security, PostgreSQL & Spring Data JPA.

For more details, please visit:
> [TODO](https://)

## User Registration & Authentication 
##### Register new User : localhost:8080/api/auth/signup
> Request Body
```
{
	"name": "test",
	"username": "usertest",
	"email": "test@blabla.com",
	"role": ["ROLE_USER"],
	"password": "123qwe"
}
```
> Response
```
{
  "user": {
    "id": 4,
    "name": "test",
    "username": "usertest",
    "email": "test@blabla.com",
    "password": "",
    "roles": [
      {
        "id": 1,
        "name": "ROLE_USER"
      }
    ]
  },
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGJsYWJsYS5jb20iLCJpYXQiOjE1OTUwOTcyMDYsImV4cCI6MTU5NTE4MzYwNn0.v8iYp2QZSyskxLCm_zW2FJhk5HjeATOF7qTOq-be5NJIaNPGgLf2BKO-czRjW4P2k4BrmnGRwABSPWzW2SC3mQ",
  "type": "Bearer",
  "message": "Sign-Up Successful, welcome usertest !"
}
```
user_id |email             |username |user_role_id |user_id |role_id |role_name  |
--------|------------------|---------|-------------|--------|--------|-----------|
1       |aivars@blabla.com |Aivars   |1            |1       |1       |ROLE_USER  |
2       |admin@blabla.com  |admin    |2            |2       |3       |ROLE_ADMIN |
3       |pm@blabla.com     |pm       |3            |3       |2       |ROLE_PM    |
4       |test@blabla.com   |usertest |4            |4       |1       |ROLE_USER  |

##### Login with existing User : localhost:8080/api/auth/signin
> Request Body
```
{
	"username": "usertest",
	"password": "123qwe"
}
 ```
> Response
```
{
    "user": {
        "id": 4,
        "name": "test",
        "username": "usertest",
        "email": "test@blabla.com",
        "password": "",
        "roles": [
            {
                "id": 1,
                "name": "ROLE_USER"
            }
        ]
    },
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGJsYWJsYS5jb20iLCJpYXQiOjE1OTUwOTgyMDIsImV4cCI6MTU5NTE4NDYwMn0.57Z2jgmE5WSxxgiD-qbs3j063CgpDxrbQWJGBMZPBPnKYZn-sbEHHmm98LeHep3pPYDk8PZeGk4sP9LDGDxWqg",
    "type": "Bearer",
    "message": "Log-In Successful, welcome usertest !"
}
```
##### Testing APIs
- localhost:8080/api/test/user
- localhost:8080/api/test/pm
- localhost:8080/api/test/admin

> Example - Trying to get Admin access with the "usertest" JWT Token : localhost:8080/api/test/admin
```
Request Headers:
Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGJsYWJsYS5jb20iLCJpYXQiOjE1OTUwOTgyMDIsImV4cCI6MTU5NTE4NDYwMn0.57Z2jgmE5WSxxgiD-qbs3j063CgpDxrbQWJGBMZPBPnKYZn-sbEHHmm98LeHep3pPYDk8PZeGk4sP9LDGDxWqg
```
> Response
```
{
    "timestamp": "2020-07-18T19:05:15.619+0000",
    "status": 403,
    "error": "Forbidden",
    "message": "Forbidden",
    "path": "/api/test/admin"
}
```
> Example - Trying to get User access with the "usertest" JWT Token : localhost:8080/api/test/user
```
Request Headers:
Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0QGJsYWJsYS5jb20iLCJpYXQiOjE1OTUwOTgyMDIsImV4cCI6MTU5NTE4NDYwMn0.57Z2jgmE5WSxxgiD-qbs3j063CgpDxrbQWJGBMZPBPnKYZn-sbEHHmm98LeHep3pPYDk8PZeGk4sP9LDGDxWqg
```
> Response
```
>>> UserName: usertest, UserEmail: test@blabla.com, Roles: [ROLE_USER] -> got Access to : User Contents
```

## Dependencies
```xml
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<!-- For the Json Web Tokens (JWT) -->
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.0</version>
		</dependency>
	
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.12</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
```
## Application properties configuration: Datasource, JPA & App.
Source: `src/main/resources/application.properties`
```
spring.datasource.url=jdbc:postgresql://192.168.99.101:30834/postgres
spring.datasource.username=POSTGRES_USER
spring.datasource.password=POSTGRES_PASSWORD

spring.jpa.generate-ddl=true
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation= true
spring.jpa.properties.hibernate.dialect= org.hibernate.dialect.PostgreSQLDialect

# App Properties
# JWT pseudo secret key: Base64 encoded 256 characters.
# Use the com.aivarsd.jwtauth.security.utils.RandomUtil.genRandom256CharsAsBase64() to generate one.
aivarsd.app.jwtSecret=JWT_SECRET
aivarsd.app.jwtExpiration=86400

#cors
aivarsapp.cors.urls=http://localhost:8082
```
## Run Spring Boot application
```
mvn spring-boot:run
```

## Postgre DB creation script
```
CREATE TABLE "user"(
   id BIGSERIAL PRIMARY key,
   email VARCHAR(40) NOT NULL,
   username VARCHAR(15),
   password VARCHAR(100)
);

CREATE TABLE "role"(
   id BIGSERIAL PRIMARY key,
   name VARCHAR(60) NOT NULL
);

CREATE TABLE user_roles(
   id BIGSERIAL PRIMARY key,
   user_id BIGINT references "user"(id),
   role_id BIGINT references "role"(id)
);

INSERT INTO "user" (email,username,password) VALUES ('aivars@blabla.com','Aivars','123qwe');
INSERT INTO "user" (email,username,password) VALUES ('admin@blabla.com','admin','admin123');
INSERT INTO "user" (email,username,password) VALUES ('pm@blabla.com','pm','pm123');

INSERT INTO "role"(name) VALUES('ROLE_USER');
INSERT INTO "role"(name) VALUES('ROLE_PM');
INSERT INTO "role"(name) VALUES('ROLE_ADMIN');


INSERT INTO "user_roles" (user_id, role_id ) VALUES (1,1);
INSERT INTO "user_roles" (user_id, role_id ) VALUES (2,3);
INSERT INTO "user_roles" (user_id, role_id ) VALUES (3,2);
```

user_id |email             |username |user_role_id |user_id |role_id |role_name  |
--------|------------------|---------|-------------|--------|--------|-----------|
1       |aivars@blabla.com |Aivars   |1            |1       |1       |ROLE_USER  |
2       |admin@blabla.com  |admin    |2            |2       |3       |ROLE_ADMIN |
3       |pm@blabla.com     |pm       |3            |3       |2       |ROLE_PM    |