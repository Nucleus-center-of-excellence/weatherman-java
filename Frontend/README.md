<h1 align="center">
  <br>
  <a><img src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Frontend/docs/images/angular.png" alt="Angular"></a>
  <br>
The WeatherMan(Angular 6)
  <br>
</h1>

## Table of Contents ##
1. [Angular 6](#Angular-6)
2. [Application](#Application)
3. [Live Application url](#Live-Application-url)
4. [Prerequites to Run the Application](#Prerequites-to-Run-the-Application)
5. [Application Structure](#Application-Structure)
6. [UI Design](#UI-Design)
7. [Run Insider Docker](#Running-the-server-in-Docker-Container)
8. [Angular 6 References](#Angular-6-References)
9. [Contributor](#Contributor)
10. [License](#License)


## Angular 6 ##
Angular 6 is a right choice to build web Single Page Applications. It helps to publish web component which is used in an HTML page. Angular 6 was released on May 4, 2018. It includes the Component Development Kit (CDK), Angular CLI (Command Line Interface), and Angular Material Package Update. JavaScript was initially used by designers to develop the image, but now it is used for front-end development.
It has several features and specifications, that will definitely benefit the app development industry at large. Faster, Smaller and Easier is the main motto of Angular 6 functions. This update is helpful for mobile and web app development.Angular 6 has given more emphasis on the toolchain instead of the underlying framework, which indeed makes it simpler and faster to use the Angular. 

**Component Development Kit (CDK)**
CDK was released in Dec 2017. With this, a user can build own library for UI components. It also supports for Responsive Web Design.

**Angular Command Line Interface (CLI)**
CLI helps to update code and dependencies, it is also equipped for ng-update command. ng-add helps to append the application features in CLI. Each and every command helps developers to prefer ng-package for different libraries using Bazel tool.

**Angular Material Design Library**
This design library helps to visualize tree structures in hierarchical order.

**Bazel Compiler**
It is almost used in all software such as optimized dependency, distributed caching, parallel execution, mobile application is helpful to build a web in a short duration. Bazel Compiler includes 300+ apps which are written in Angular.

**Closure Compiler**
Bundling optimizer which allows you to build JavaScript modules. At the same time, it helps to generate minimum bundles. Upcoming releases of Angular framework use toolchain for app development.

## Application ##
The idea of the application is to design a WeatherMan Analysis which shows approximate weather forecast of 5 days ,on the basis of the analysis done on the data from four well known and authorized weather service providers, for the place which user selects.It also compares and check the data of last year on that particular date and thus makes it analysis more accurate.The aim of the application is to give the most accurate weather information out of all the service providers.
This application is unique in every aspect whether the idea of its creation or the technologies used to build it. We have used Angular 6 as frontend technology along with two backend technologies-

1. Spring Boot 2.0 with MySQL
2. Dotnet Core 2.2 with cosmosDB

We have used two backend technologies so that user can choose the one in which he/she is more comfortable working with. The aim of the application is to give the most accurate weather information out of all the service providers.
This application is unique in every aspect whether the idea of its creation or the technologies used to build it. It is very well justified in every phase. The technologies used are the ones which are on the boom in the techno-world right now and thus adds starts to this application.

## Live Application url ##

http://52.165.145.32
This is the link where the app is deployed.

## Prerequites to Run the Application ##

** Install NodeJS **
Refer https://nodejs.org/en/ to install NodeJS

** Install Angular CLI **
npm install -g @angular/cli
Refer https://cli.angular.io/ to know more about angular CLI

** Clone the repo **

```
git clone https://github.com/
cd 
```

** Install npm packages **
Install the npm packages described in the package.json and verify that it works:

```
npm install
npm start
```

The npm start command builds (compiles TypeScript and copies assets) the application into dist/, watches for changes to the source files, and runs lite-server on port 4200.

## Application Structure ##
We have tried to follow the basic ideology while creating the file structure and the structure look as follows :

<img src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Frontend/docs/images/application-structure.png" alt="project structure"></a>

## UI Design ##

This application is user friendly as all the features are on the single page i.e home page which comprises of -
1- Google maps showing locations based on the user search and the recently searched feature is also provided.
2- Different service providers showing their respective weather data of the place searched by the user.
3- The key feature of the app that is THE WEATHERMAN ANALYSIS section which shows the aggregate weather forecast report based on the analysis made out of the data fetched by the 4 service providers and analysis on the last year data of that particular day. And on the basis of the forecast, background will be changed likewise.

<p align="center">
<img width="600" src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Frontend/docs/images/application-1.png">
</p>

<p align="center">
<img width="600" src="https://github.com/abhishek-sisodiya/theweatherman/blob/master/Frontend/docs/images/application-2.png">
</p>

## Running the server in Docker Container ##
##### Docker #####

Running the server in Docker Container
Docker is a platform for developers to develop, deploy, and run applications with containers.A container is launched by running an image. An image is an executable package that includes everything needed to run an application — the code, a runtime, libraries, environment variables, and configuration files.
A container is a runtime instance of an image — what the image becomes in memory when executed (that is, an image with state, or a user process). You can see a list of your running containers with the command, docker ps.

Command to build the container :

```
docker build -t TheWeatherMan .
```

Command to run the container :

```
docker run -p 8080:8080 TheWeatherMan
```

##### Docker Compose #####
Another alternative to run the application is to use the docker-compose.yml file and utility. To build the application using docker-compose simply execute the following command :
```
docker-compose build
```

And to run the application, please execute the following command :
```
docker-compose up
```


## Angular 6 References ##
Refer to https://angular.io/guide/quickstart to get an understanding of how angular 6 works

## Contributors ##
[Sakshi Badaya](https://www.linkedin.com/in/sakshi-badaya-70bb74137/)

## License ##
This project is licensed under the terms of the MIT license.
