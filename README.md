# Spring Boot JWT Authentication with MongoDB

This repository contains a Spring Boot application example that implements JWT (JSON Web Token) authentication using a MongoDB database to store user information.

## Prerequisites

Make sure you have the following prerequisites installed before running the application:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (version 8 or higher)
- [Maven](https://maven.apache.org/download.cgi)
- [MongoDB](https://docs.mongodb.com/manual/installation/) (ensure MongoDB is running on the default port)

## Configuration

1. Clone the repository:

  `git clone https://github.com/your-username/spring-boot-jwt-mongodb.git`

2. Configure the MongoDB connection in the application.properties file:
   
   `spring.data.mongodb.uri= YOUR MONGO URI`
   
   `spring.data.mongodb.database= YOUR DB`
   
5. execute the project with SpringBoot dashboard or VSCode debug
  
6. Use postman to test the endpoints:

* register a new user on localhost 8080: POST `http://localhost:8080/auth/register`

  body ex:

{   

 
"username": "Test1",    
"firstName": "John",    
"lastName": "Doe",    
"password": "12345"  

}

* register a new user on localhost 8080: POST `http://localhost:8080/auth/login`

  body ex:

{   

 
"username": "Test1",      
"password": "12345"  

}

