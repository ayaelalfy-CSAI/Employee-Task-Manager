#  Employee Task Manager - Microservices Architecture

A Spring Boot-based Microservices project designed to manage employees and their tasks. It utilizes Spring Cloud Gateway for routing, Eureka for service discovery,
and JWT for secure authentication.

---------

##  Microservices Overview

  ###  Auth Service
- Handles user login and JWT token generation
- Verifies roles like `ADMIN` and `EMPLOYEE`

  ###  Task Manager Service
- Create, assign, and update tasks
- Assign tasks to employees
- Protected via JWT authorization

  ###  API Gateway
- Central entry point for all services
- Configured with routing and filtering

  ###  Eureka Server
- Service registration and discovery
- Enables communication between microservices

---------

##  Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Security with JWT**
- **Spring Cloud Gateway**
- **Eureka Discovery Server**
- **MySQL**
- **IntelliJ IDEA**

---------

##  How to Run (Locally)

1. Start MySQL and configure the required databases
2. Run `EurekaServer` first
3. Then start the APIGateway
4. Then run `AuthService` and `TaskManagerService`
5. Use tools like **Postman** to test login and task management endpoints


