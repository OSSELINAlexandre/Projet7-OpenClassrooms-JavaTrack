# Projet 7 - OpenClassrooms - Java Track

Poseidon is a financial software deployed on the web. The purpose of this project is to code all requirement from the client. Some Mock-up where given, and our task was to implement CRUD methods into existing code so that the software can interact with a database in back-end.

## Prerequisite

* Java 11.
* MySql 8.
* Maven 4.0.



## Frameworks used
* Spring Boot 2.0.4
* Spring Data
* Spring Security
* Bootstrap 5

## Test
* 78 tests
* 81% coverage from JaCoCo. 

## Miscellaneous

Spring version of this project is 2.0.4 [ last update one is : 2.5.3 ]. Therefore some functionality can be different.

* The password is hardcoded, but you can create environment variable for username and password to protect information.

* @SpringBootTest work with a @RunWith instead of @ExtendWith.

* Be carefull if you generate a Bcrypt password from internet an INSERT it into the database: SpringBoot version 2.0.4 only read password starting with '$2a$10$'. Generated password in Bcrypt can start with '$2y$10$'. It isn't an issue with Spring Boot 2.5.3.

* The application wasn't designed to be responsive, requirements from OpenClassroms where on the Back-end not on the Front end side. 

