# spring_example*
Simple Spring Boot/React Example

This is a simple Spring Boot backend, React frontend example.

The root page "/" is handled by a standard Spring Web Controller, HomeController.java.

The React application running on that page uses a Spring REST Controller with a "/cars" mapping to access the
API for handling the Car objects in the application.

# Spring Packages Used:

* Spring Boot
* Spring Web
* Spring Thymeleaf
* Spring Data JPA
* Spring Devtools
* MySQL Connector
* frontend-maven-plugin for React development

# JS Packages Used:

* Babel
* Webpack
* Axios
* React
* react-router-dom

# Frontend Build

There are 3 maven rules that are used to build the frontend:

     mvn frontend:install-node-and-npm

If this is executed from the command line (or your IDE maven interface)
it will install Node.js and npm in your project directory.  This only
needs to be rerun after the first time if you change the version of node
specified in your pom.xml file.

     mvn frontend:npm

If this is executed from the command line (or your IDE maven interface)
it will the javascript packages specified in your package.json file.  This
only needs to be rerun if that file is changed.

     mvn frontend:webpack

If this is executed from the command line (or your IDE maven interface)
it will compile and package your javascript files into a single javascript
file which will run on older as well as current browsers.  It should be rerun
each time your frontend application is changed, before rerunning the backend
server project.

# Tests

The project performs 3 types of tests:  Unit tests for the Spring Controller,
Postman integration tests for the API, and TestCafe end-to-end web tests.

# Continuous Integration

The project uses GitHub's Actions to verify the build of the Java and JavaScript
portions of the project, as well as performing all 3 types of testing.




