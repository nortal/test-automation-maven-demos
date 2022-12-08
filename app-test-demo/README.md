# Todo Application Test Demo

This project is intended to demonstrate use of multiple Test Automation Framework modules together.

Demo project executes tests against Todo application that consists of frontend, backend and Postgres database.

## Test Automation Framework modules
* Core - main functionality that ties all other modules.
* Containers - [TestContainers](https://www.testcontainers.org/) based module that provides a capability to test against a docker container.
* Jdbc - JDBC support to execute database queries.
* Feign - [OpenFeign](https://github.com/OpenFeign/feign/) + [OkHttp](https://square.github.io/okhttp/) based client. Makes calls to backend API.
* Assert - provides helper services to create data assertions and has visual representation in reports. 
* Allure - report generation module. Based on [Allure](https://qameta.io/allure-report/).
* Selenide - UI testing capability module. Based on [Selenide](https://selenide.org/).

## Local Setup

Install [Docker](https://docs.docker.com/get-docker/). Recommend installing Docker Desktop 
as this demo has been tested to work properly with Docker Desktop and installation process is more simple.

To build the project run:
```
mvn compile
```

Execute tests by triggering Cucumber test runner or use Cucumber plugin in your IDE to run scenarios you want.

Docker should start pulling images from Docker Hub and run containers of Todo application frontend, backend and database.

When the tests are finished running, the Docker containers will be stopped.

* Generated report is accessible from: `build/allure-report/index.html`
* Demo project properties are set in `application-override.yml` file