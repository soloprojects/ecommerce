# REST Microservices architecture for E-commerce

> Implementation of a REST Microservices in an E-Commerce with Spring boot, Cloud and multiple modules.                

### Table of contents

- [Project architecture](#Project-architecture)
- [Tools and Technologies](#technologies)
- [Features](#features)

### Project architecture
 Check out the sytem design.png

### Tools and Technologies

- **Java 17**
- **Spring Boot** 
- **Spring Web starter** 
- **Spring SESSION** 
- **Spring Cloud Gateway** 
- **Spring web-flux-starter**  
- **Netflix Eureka Client/Server** 
- **Netflix Ribbon** 
- **Redis Client : JEDIS** 
- **Spring Data REDIS** 
- **Spring Data JPA** 
- **Hibernate** 
- **SQL Database engine** 
- **Config Server** 
- **Maven**
- **Elastic Search**
- **Docker**

### Features

1. **BOOK-INVENTORY SERVICE :**

   - Get Book By Id
   - List of Books
   - Add Book

2. **SHOPPING-CART SERVICE** :

   - Registration (Shopping Card)
   - Authentication (Shopping Card)
   - Shopping cart (for guest or logged user)
   - Create Order
   - Purchase History
   
1. **PAYMENT-SERVICE :**

   - Get Payment By OrderId
   - Do Payment

3. **HOW TO RUN AND TEST**
    Ensure to have java 17** jdk, mvn, docker, git, Postman are installed in your machine
    Run the docker-compose.yml file in the root directory of the ecommerce folder
    Run in the following order
        DISCOVERY-SERVICE
        CONFIG-SERVICE
        API-GATEWAY
        BOOK-INVENTORY SERVICE
        SHOPPING-CART SERVICE
        PAYMENT SERVICE
    
    TESTING (ENSURE THE SERVICES ARE RUNNING BEFORE TESTING)
        POSTMAN (USE THE ENDPOINTS BELOW)
        localhost:8085/api/v1/book-inventory/add


    R

    Ensure the services are running before testing.






