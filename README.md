<h1 align="center">
  <br>
  The WeatherMan Application
  <br>
</h1>

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.1.3-brightgreen.svg" />
    </a>    
    <a alt="License">
        <img src="https://img.shields.io/badge/Angular-v6-red.svg" />
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
1. [Application](#Application)
2. [Technology](#Technology)
3. [Projects Link](#Projects-Link)
4. [Run Insider Docker](#Running-the-server-through-Docker-Compose)
5. [Contributor](#Contributor)
6. [License](#License)

## Application ##
The idea of the application is to design a WeatherMan Analysis which shows approximate weather forecast of 5 days ,on the basis of the analysis done on the data from four well known and authorized weather service providers, for the place which user selects and also weatherman checks and compares the last year data of that particular day and thus apply its analysis and predict the forecast.These two are the major factor of the weatherman analysis .The aim of the application is to give the most accurate weather information out of all the service providers.
This application is unique in every aspect whether the idea of its creation or the technologies used to build it. It is very well justified in every phase. The technologies used are the ones which are on the boom in the techno-world right now and thus adds starts to this application.

## Technology ##

##### Angular 6 #####
Angular 6 is a JavaScript framework for building web applications and apps in JavaScript, html, and TypeScript, which is a superset of JavaScript. Angular provides built-in features for animation, http service, and materials which in turn has features such as auto-complete, navigation, toolbar, menus, etc. The code is written in TypeScript, which compiles to JavaScript and displays the same in the browser.

##### Spring Boot 2.0 #####
Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications including work in the areas of cloud computing, big data, schema-less data persistence, reactive programming, and client-side application development. Spring Boot offers a new paradigm for developing Spring applications with minimal friction. We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss.

##### Dotnet Core 2.2 #####
ASP.NET Core is a cross-platform, high-performance, open-source framework for building modern, cloud-based, Internet-connected applications. With ASP.NET Core -Build web apps and services, IoT apps, and mobile backends, Deploy to the cloud or on-premises, Use your favorite development tools on Windows, macOS, and Linux.

##### Cosmos DB #####
Azure Cosmos DB is a global distributed, multi-model database that is used in a wide range of applications and use cases. It is a good choice for any serverless application that needs low order-of-millisecond response times, and needs to scale rapidly and globally.

##### MySQL #####
MySQL is the most popular Open Source Relational SQL Database Management System. MySQL is one of the best RDBMS being used for developing various web-based software applications. It is a fast, easy to use and currently the most popular open-source database. It is very commonly used in conjunction with PHP scripts to create powerful and dynamic server-side applications.

##### Redis #####
Redis can be used to cache data that a web-server needs. Redis gives a structured way to store data in memory. Therefore it is faster than your traditional database that writes to disk. Redis data structures resolve very complex programming problems with simple commands executed within the data store, reducing coding effort, increasing throughput, and reducing latency.

## Projects Link ##
#####  #####
The link for backend which is on Spring Boot using MySql is:
[Backend](https://github.com/abhishek-sisodiya/theweatherman/tree/master/Backend)
#####  #####
The link for frontend which is on Angular is: 
[Frontend](https://github.com/abhishek-sisodiya/theweatherman/tree/master/Frontend)

## Running the server through Docker Compose ##

##### Create build #####
$ docker-compose build

##### Start the server in daemon thread #####
$ docker-compose up -d

<img src="https://miro.medium.com/max/1920/1*s815EK0zFngv1JAuK2q5MQ.png" alt="spring boot">

##### Stop the server #####
$ docker-compose down

## Contributors ##
[Abhishek Sisodiya](https://www.linkedin.com/in/abhisheksisodiya01/) | 
[Sakshi Badaya](https://www.linkedin.com/in/sakshi-badaya-70bb74137/)

## License ##
This project is licensed under the terms of the MIT license.
