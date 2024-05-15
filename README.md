# REST Microservices architecture for E-commerce

> Implementation of a REST Microservices in an E-Commerce with Spring boot, Cloud and multiple modules.                

### Table of contents

- [SYSTEM DESIGN AND PROJECT ARCHITECTURE](#System-Design-And-Architecture)
- [Tools and Technologies](#technologies)
- [Features](#features)

### SYSTEM DESIGN AND PROJECT ARCHITECTURE
 Check out the sytem Ecommerce Diagram.drawio.png in the Ecommerce root directory.
 https://raw.githubusercontent.com/soloprojects/ecommerce/master/Ecommerce%20Diagram.drawio.png

### Tools and Technologies

- **Java 17**
- **Spring Boot** -- Version 3.2.5 
- **Spring Web starter** 
- **Spring Validator** 
- **Spring Cloud Gateway** 
- **Spring web-flux-starter**  
- **Netflix Eureka Client/Server** 
- **Zipkin** 
- **Spring Security** 
- **Spring Data REDIS** 
- **Spring Data JPA** 
- **Hibernate**  
- **Prometheus**  
- **Actuator**  
- **spring-doc-open-api**  
- **JSONWEBTOKEN**  
- **Lombok**  
- **Resilience4J** 
- **POSTGRESQL Database engine** 
- **Config Client/Server** 
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
    Ensure to have java 17** jdk, mvn, docker (preferably latest version), git, Postman, intellij/Eclipse/STS/VS Code are installed in your machine
    Run the docker-compose.yml file in the root directory of the ecommerce folder
    Run in the following order via Intellij or mvn clean verify -DskipTests, then run mvn spring-boot:run
        DISCOVERY-SERVICE
        CONFIG-SERVICE
        API-GATEWAY
        BOOK-INVENTORY SERVICE
        SHOPPING-CART SERVICE
        PAYMENT SERVICE
    
    TESTING (ENSURE THE SERVICES ARE RUNNING BEFORE TESTING)
        POSTMAN (USE THE ENDPOINTS BELOW)
            Add Book () - localhost:8085/api/v1/book-inventory/create
            Search Book - localhost:8085/api/v1/book-inventory/search?search_query=string

            Add Role - localhost:8087/api/v1/shopping-cart/role/create
            Register Card - localhost:8087/api/v1/shopping-cart/register
            Authenticate Card - localhost:8087/api/v1/shopping-cart/auth/authenticate
            Add items to cart - localhost:8087/api/v1/shopping-cart/add
            View Cart Content - localhost:8087/api/v1/shopping-cart/items
            Checkout/Order Cart Items - localhost:8087/api/v1/shopping-cart/checkout

            Payment Simulation/Create Payment(Use Existing OrderId in Cart-service) - localhost:8086/api/v1/payment-service/accept
            Get Payment By OrderId - localhost:8086/api/v1/payment-service/order/{id}

            User Purchase History - localhost:8087/api/v1/shopping-cart/purchase_history/user/{id}

        SPIRNG BOOT UNIT AND INTEGRATION TESING (Ensure all microservices are running)
            Run mvn test or mvn clean install by going inside each folder/microservice to build the applications.


            

    






