# hexagonal-example

Implementation of a customer service in an hexagonal architecture.

### Technologies

* Spring Boot
* MongoDB

### How to run

To run this application you need to first run a

```cmd
mvn clean install
```

in the root directory of this project. This will download all required dependecies and bundle your project properly.

The database for this application is a mongo db. You either need to have one running on your system, or you can
alternatively start the MongoDB in docker with the included docker-compose file.

```cmd
docker-compose -f mongodb-docker-compose up
```

Docker / docker-compose need to be installed for this.

The application can then be started by running the main function in:

```
hexagonal-example\config\src\main\java\de\strasser\peter\hexagonal\HexagonalApplication.java
```
