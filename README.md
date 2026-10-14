# Car Rental Microservices Backend Application

A backend application built with **Java, Spring Boot, Spring Cloud, and MySQL** to practice designing a car-rental system using a microservices architecture.

This project is being developed step by step by evolving an earlier monolithic car-rental backend into separate services.

## Project Goals

- Split car-rental functionality into independently organized Spring Boot services.
- Use **Eureka Server** for service discovery.
- Use **Spring Cloud Gateway** as the single entry point for API requests.
- Secure APIs with **JWT (JSON Web Token)** authentication.
- Keep service data in separate databases.
- Practise service-to-service communication, testing, and deployment as the project evolves.

## Architecture

```text
                         Client / Postman
                                |
                                v
                     API Gateway : 8762
                     (JWT validation)
                                |
                 +--------------+--------------+
                 |              |              |
                 v              v              v
          Car Service      Owner Service   Customer Service
            : 8081            : 8082           : 8083
                 |              |              |
                 v              v              v
           Car database    Owner database  Customer database

                    Eureka Discovery Server
                            : 8761
                 (service registration/discovery)
```

> The diagram represents the current intended architecture. Confirm that all services are running and registered in Eureka before testing the complete flow.

## Services

| Component | Port | Responsibility |
|---|---:|---|
| Discovery Server | `8761` | Registers services and helps them discover one another |
| API Gateway | `8762` | Routes incoming requests to the appropriate service and validates JWTs |
| Car Service | `8081` | Car-related APIs, persistence, and request validation |
| Owner Service | `8082` | Car-owner-related APIs |
| Customer Service | `8083` | Customer registration/login and customer-related APIs |

## Technology Stack

- **Language:** Java (project configured for Java 21)
- **Framework:** Spring Boot
- **Microservices ecosystem:** Spring Cloud
- **Service discovery:** Netflix Eureka
- **API gateway:** Spring Cloud Gateway Server Web MVC
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** MySQL
- **Authentication:** JWT with JJWT
- **Object mapping:** MapStruct
- **Build tool:** Maven
- **API testing:** Postman

## Current Features

- Separate Spring Boot applications for car, owner, customer, discovery, and gateway responsibilities.
- Separate MySQL databases configured for car, owner, and customer services.
- Eureka-based service discovery.
- Gateway routes for `/api/cars/**`, `/api/owners/**`, and `/api/customers/**`.
- Customer login that issues a JWT.
- JWT validation at the Gateway and in backend services.
- Basic request validation and exception-handling work in the services.

This is a work in progress. Check the source code and API endpoints for the exact functionality currently implemented.

## Gateway Routes

The Gateway runs at `http://localhost:8762`.

| Route pattern | Destination |
|---|---|
| `/api/cars/**` | Car Service |
| `/api/owners/**` | Owner Service |
| `/api/customers/**` | Customer Service |

The Gateway uses Eureka service IDs to resolve service destinations.

## Prerequisites

Install the following before running the project:

- JDK 21
- Maven (or use the Maven Wrapper if one is included)
- MySQL 8+
- Git
- Postman or another HTTP client

## Local Setup

### 1. Clone the repository

```bash
git clone https://github.com/shanikushwaha45/car-rental-microservices-backend-application.git
cd car-rental-microservices-backend-application
```

### 2. Configure MySQL

Create the databases used by your local service configurations, if they are not created automatically:

```sql
CREATE DATABASE car_rental_car_db;
CREATE DATABASE car_rental_owner_db;
CREATE DATABASE car_rental_customer_db;
```

Check each service's `application.properties` (or `application.yml`) and make sure its JDBC URL, username, and password match your local MySQL setup.

**Security note:** Do not commit real database passwords, JWT secrets, or other credentials. Prefer environment variables or local configuration files excluded from Git.

### 3. Check service configuration

Verify the ports, service names, Eureka URL, database connection, and JWT secret in each service's configuration.

All services that validate the same JWT must use a compatible signing secret and token format. For local development, keep the secret consistent across the Gateway and relevant backend services; use environment variables rather than hard-coding it for a shared or production environment.

### 4. Build each service

Run Maven from each service's own directory (the repository root may not contain a parent `pom.xml`).

For example:

```powershell
cd discovery-server
mvn clean package
```

Repeat for `car-service`, `owner-service`, `customer-service`, and `api-gateway`.

### 5. Start the applications

Start each service in a separate terminal, in this order:

1. `discovery-server`
2. `car-service`
3. `owner-service`
4. `customer-service`
5. `api-gateway`

Use the following command from each service directory:

```powershell
mvn spring-boot:run
```

Open the Eureka dashboard at:

`http://localhost:8761`

Confirm the services have registered before testing Gateway routes.

## Testing with Postman

Use the API paths defined by the controllers in each service. The examples below show the Gateway base URL; confirm the exact endpoint names and request bodies in your code.

### JWT authentication checks

1. Call the Customer Service login endpoint through the Gateway.
2. Copy the JWT returned by a successful login.
3. Send a protected request through the Gateway, for example:

   `GET http://localhost:8762/api/cars/getCars`

4. In Postman, choose **Authorization → Bearer Token** and paste the token.

Expected results for the checks already performed during development:

| Scenario | Expected result |
|---|---|
| Protected request without a token | `401 Unauthorized` |
| Protected request with a valid token | Successful response if the service and endpoint are available |
| Protected request with an invalid token | `401 Unauthorized` |

Customer registration, customer login, and owner registration are intended to be public endpoints in the current Gateway filter configuration. Confirm their exact paths and HTTP methods against your controllers.

## Repository Structure

The repository contains separate application folders. The structure is expected to be similar to:

```text
car-rental-microservices-backend-application/
├── car-service/
├── owner-service/
├── customer-service/
├── discovery-server/
└── api-gateway/
```

Each service has its own `pom.xml`, source code, and configuration. The exact folders may vary as development continues.

## Planned Improvements

- Complete and verify CRUD operations across the services.
- Add role-based authorization for customers, owners, and administrators.
- Finish owner-specific car management.
- Add a dedicated Booking Service and booking lifecycle.
- Use OpenFeign for required service-to-service calls.
- Add automated unit and integration tests.
- Improve consistent API error responses and validation.
- Add Swagger/OpenAPI documentation.
- Add health checks, centralized logging, and resilience settings.
- Externalize configuration and secrets.
- Containerize and deploy the services.

## Learning Outcomes

This project is intended to provide practical experience with:

- Microservices architecture and service boundaries
- Spring Boot REST APIs
- Eureka service discovery
- API Gateway routing
- JWT authentication
- MySQL and Spring Data JPA
- DTO mapping and validation
- Service-to-service communication
- Testing and deployment fundamentals

## Author

**Shani Kushwaha**

GitHub: [shanikushwaha45](https://github.com/shanikushwaha45)

## Project Status

**In progress** — core infrastructure and JWT validation are being developed and tested. Booking workflows, full feature verification, automated tests, and deployment remain part of the roadmap.
