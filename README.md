# GroupA_API
GroupA API


Config
---
1. The following environment variables need to be set to enable database connection:
```
DB_USERNAME
DB_PASSWORD
DB_HOST
DB_NAME
```
1. The following environment variables need to be set allow for encryption:
```
JWT_SECRET_TOKEN
JWT_SECRET_KEY
```
How to start the java-api application
---

1. Run `mvn clean install -DskipTests=true` to build your application
1. Start application with `java -jar target/JavaWebService-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Swagger
---

To see your applications Swagger UI `http://localhost:8080/swagger`

Tests
---

1. Run `mvn clean test` to run unit tests
2. Run `mvn clean integration-test` to run integration tests

```
If the tests are not running please make sure Java version is set to 11

If the above commands aren't working you can run them manually by opening the test file, right clicking and runnning them
```
