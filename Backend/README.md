<h1 align="center">
  <br>
  <a><img src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Backend/docs/images/logo-ss.png" alt="TheWeatherMan"></a>
  <br>
  TheWeatherMan Application (Backend)
  <br>
</h1>

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.1.3-brightgreen.svg" />
    </a>  
    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v18-yellowgreen.svg" />
    </a>
    <a alt="Dependencies">
        <img src="https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg" />
    </a>
    <a alt="Contributions">
        <img src="https://img.shields.io/badge/contributions-welcome-orange.svg" />
    </a>
    <a alt="License">
        <img src="https://img.shields.io/badge/license-MIT-blue.svg" />
    </a>
</p>

## Table of Contents ##
1. [Spring Boot](#Spring-Boot)
2. [Application](#Application)
3. [Database Schema](#Database-Schema)
4. [Technology](#Technology)
5. [Application Structure](#Application-Structure)
6. [Run Locally](#Running-the-server-locally)
7. [Run Insider Docker](#Running-the-server-in-Docker-Container)
8. [API Documentation](#API-Documentation)
9. [Contributor](#Contributor)
10. [License](#License)

## Spring Boot ##
_Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can just run. We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need very little Spring configuration._

**Spring Boot is opinionated** : This simply means that Spring Boot has its own configurations, application structures, dependencies, Servers and other environment configuration available inside its realm. Thus, to say Spring Boot has its own opinions about an application development environment. For example, most of the Java-based web applications use tomcat server. While working on Spring Boot you need not use any server, because Spring Boot already has an embedded tomcat container.

**Spring Boot is stand-alone** : What it means is that you don’t need to use any other third-party library or server to run or develop a spring boot application, it already has all of it.

**It is production-grade** : This implies that application developed using Spring Boot defaults is able to handle all complexities and requirements of a production environment.

**Still very customizable** : It is not worth using a framework which has its own rigid opinions, which you can’t customize or change according to your own business requirements. Although Spring Boot is opinionated you can easily change or customize its defaults to suit your own needs. 

## Application ##
What’s the weather? What will be tomorrow’s weather? These question are the ones which maximum people likes to know the answer on the daily basis and thus people wants to know the most accurate weather forecast. Their are many applications which gives the weather report and forecast but on which website user should rely on because every application shows different different data which makes user perplexed.

The WeatherMan Analysis shows approximate weather forecast of 5 days, on the basis of the analysis done on the data from four well known and authorized weather service providers, for the place which user selects and also weatherman checks and compares the last year data of that particular day and thus apply its analysis and predict the forecast.These two are the major factor of the weatherman analysis .The aim of the application is to give the most accurate weather information out of all the service providers.  :

## Database Schema ##
The current schema looks as follows:

<img src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Backend/docs/images/db-schema.jpg" alt="spring boot"></a>

- The authentication and authorization is governed by _User_ collection. (User model)
- The _Provider_ collection keeps the details of all the service providers which we are using. The 4 providers will also get   automatically added on app start. (Provider model)
- The _Vote_ collection keeps users' favorite option with date of voting and 2 foreign keys provider.id & user.id. (Vote model)

## Technology ##
Following libraries were used during the development of this starter kit :

- **Spring Boot** - Server side framework
- **Docker** - Containerizing framework
- **MySQL** - Database 
- **Swagger** - API documentation
- **Thymeleaf** - Templating engine
- **JWT** - Authentication mechanism for REST APIs
- **Redis** - Caching data to save hits on Weather api's

## Application Structure ##
Spring Boot is an opinionated framework that makes our life very easy since we don't have to choose the versions of different dependencies based on the version of Spring framework, its all taken care of by Spring Boot. I have tried to follow the same ideology while creating the project structure, at first it might seem like overwhelming, but do believe me once you start writing your pieces the structure will help you immensely by saving your time and thinking about questions which are already answered. The structure look as follows :

<img src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Backend/docs/images/project-structure.png" alt="project structure"></a>

**_Models & DTOs_**

The various models of the application are organized under the **_model_** package, their DTOs(data transfer objects) are present under the **_dto_** package. There are different opinions about whether we should use DTOs or not, I belong to the set of minds who think we definitely should and not using DTOs makes your model layer very tightly coupled with the UI layer and that is something that no enterprise project should ever get into. DTOs let us transfer only the data that we need to share with the user interface and not the entire model object that we may have aggregated using several sub-objects and persisted in the database. The mapping of models to the DTOs can be handled using ModelMapper utility, however its only useful when your DTO is almost similar (literally) to the concerned models which is not always the case and hence I prefer using custom mapper classes. You can find some examples under the dto/mapper package.

**_DAOs_**

The data access objects (DAOs) are present in the **_repository_** package. They are all extensions of the MongoRepository Interface helping the service layer to persist and retrieve the data from MongoDB. The service layer is defined under the **_service_** package, considering the current case study it made sense to create two basic services - UserService and BusReservationService to satisfy the different business operations that the users are executing using the UI.

**_Security_**

The security setting are present under the **_config_** package and the actual configurations are done under the class present in the **_security_** package. The application has different security concepts for the admin portal and the REST APIs, for the portal I have applied the default spring session mechanism that is based on the concept of sessionID and cookies. For the REST APIs I have used JWT token based authentication mechanism.

**_Controllers_**

Last, but the most important part is the controller layer. It binds everything together right from the moment a request is intercepted till the response is prepared and sent back. The controller layer is present in the **_controller_** package, the best practices suggest that we keep this layer versioned to support multiple versions of the application and the same practice is applied here. For now code is only present under v1 but over the time I expect to have different versions having different features. The Admin portal related controllers are present in the **_ui_** package and its concerning form command objects are located under the **_command_** package. The REST API controllers are located under the **_api_** package and the corresponding request classes are located under the **_request_** package. 

**_Request and Form Commands_**

Again, there are different opinions amongst the fraternity regarding the usage of separate classes for mapping the incoming request vs using the DTOs, I am personally a fan of segregating the two as far as possible to promote loose coupling amongst UI and controller layer. The request objects and the form commands do give us a way to apply an additional level of validations on the incoming requests before they get converted to the DTOs which transfer valid information to the service layer for persistence and data retrieval. We could use DTOs here and some developers prefer that approach as it reduces some additional classes, however I usually prefer to keep the validation logic separate from the transfer objects and hence am inclined to use the request/command objects ahead of them.

The static resources are grouped under the **_resources_** directory. All the UI objects and their styling aspects can be located here.

## Response and Exception Handling ##
I have tried to experiment a bit with the RuntimeExceptions and come up with a mini framework for handling the entire application's exceptions using a few classes and the properties file. If you carefully observe the **_exception_** package, it consists of two enums - EntityType and ExceptionType. The EntityType enum contains all the entity names that we are using in the system for persistence and those which can result in a run time exception. The ExceptionType enum consists of the different entity level exceptions such as the EntityNotFound and DuplicateEntity exceptions. 

The TWMException class has two static classes _EntityNotFoundException_ and _DuplicateEntityException_ which are the two most widely thrown exceptions from the service layer. It also contains a set of overloaded methods _throwException_ which take the EntityType, ExceptionType and arguments to come up with a formatted message whose template is present under the **_custom.properties_** file. Using this class I was able to empower the entire services layer to throw entity exceptions in a uniform manner without cluttering the code base with all sorts of NOT_FOUND and DUPLICATE entity exceptions.

For example, while login if you try to use a email address which is not registered, an exception is raised and thrown using the following single line of code -

``` java
throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
```

This results in clubbing the names of these two enums USER(user) and ENTITY_NOT_FOUND(not.found) and coming up with a key _user.not.found_ which is present in the custom.properties files as follows :

``` 
user.not.found=Requested user with email - {0} does not exist.
```
By simply replacing the {0} param with the email address included in the thrown exception you can get a well formatted message to be shown to the user or to be sent back as the response of the REST API call.

Another important part of this mini framework is the **_CustomizedResponseEntityExceptionHandler_** class. This class takes care of these RuntimeExceptions before sending a response to the API requests. Its a controller advice that checks if a service layer invocation resulted in a EntityNotFoundException or a DuplicateEntityException and sends an appropriate response to the caller.

Last, the API response are all being sent in a uniform manner using the **_Response_** class present in the dto/response package. This class allows us to create uniform objects which result in a response as shown below (this one is a response for the "http://localhost:8080/register" api) :

```
{
    "status": "OK",
    "payload": {
        "id": 3,
        "username": "abhishek@gmail.com"
    }
}
```

And when there is an exception (for example searching for a trip between two stops which are not linked by any bus) the following responses are sent back (result of "http://localhost:8080/register" POST request with same username) :

```
{
    "status": "DUPLICATE_ENTITY",
    "errors": "Username already exist"
}
```

```
{
    "status": "NOT_FOUND",
    "errors": {
        "timestamp": "2019-09-03T10:46:31.882+0000",
        "message": "Favorite provider for - 2019-09-02 & 2018-09-03 does not exist.",
        "details": "Favorite provider for - 2019-09-02 & 2018-09-03 does not exist."
    }
}
```

As you can observe, both type of responses, one with a HTTP-200 and another with HTTP-404 the response payload doesn't change its structure and the calling framework can blindly accept the response knowing that there is a status and a error or payload field in the JSON object.

## Running the server locally ##
To be able to run this Spring Boot app you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.

```
maven package
```
or you can also use

```
mvn install
```

To run the Spring Boot app from a command line in a Terminal window you can you the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.

```
java -jar target/theweatherman-0.0.1-SNAPSHOT.jar
```

You can also use Maven plugin to run the app. Use the below example to run your Spring Boot app with Maven plugin :

```
mvn spring-boot:run
```

If you do not have a mongo instance running and still just want to create the JAR, then please use the following command:

```
mvn install -DskipTests
```

This will skip the test cases and won't check the availability of a mongodb instance and allow you to create the JAR.

You can follow any/all of the above commands, or simply use the run configuration provided by your favorite IDE and run/debug the app from there for development purposes. Once the server is setup you should be able to access the admin interface at the following URL :

http://localhost:8080

Some of the important api endpoints are as follows :

- http://localhost:8080/register (HTTP:POST)
- http://localhost:8080/authenticate (HTTP:POST)
- http://localhost:8080/averageprovider?lat=22.7196&longi=75.8577 (HTTP:GET)
- http://localhost:8080/getweather/weatherbit?lat=22.7103637&longi=75.8417637 (HTTP:GET)
- http://localhost:8080/summaryprovider (HTTP:GET)

## Running the server in Docker Container ##
##### Docker #####
Command to build the container :

```
docker build -t spring/theweatherman .
```

Command to run the container :

```
docker run -p 8080:8080 spring/theweatherman
```

Please **note** when you build the container image and if mysql is running locally on your system, you will need to provide your system's IP address (or cloud hosted database's IP) in the application.properties file to be able to connect to the database from within the container.

## API Documentation ##
Its as important to document(as is the development) and make your APIs available in a readable manner for frontend teams or external consumers. The tool for API documentation used in this starter kit is Swagger2, you can open the same inside a browser at the following url -

http://localhost:8080/swagger-ui.html

You can use the User spec to execute the login api for generating the Bearer token. The token then should be applied in the "Authorize" popup which will by default apply it to all secured apis (get and post both).

<p align="center">
    <b>Swagger</b><br>
    <br>
    <img width="600" src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Backend/docs/images/swagger.jpg">
</p>

The configuration of Swagger is being taken care of by class SwaggerConfig. I have defined two specs there with the help of "api()" methods. Since the login part is by default taken care of by Spring Security we don't get to expose its apis implicitly as the rest of the apis defined in the system.

## Contributors ##
[Abhishek Sisodiya](https://www.linkedin.com/in/abhisheksisodiya01/)

## License ##
This project is licensed under the terms of the MIT license.
