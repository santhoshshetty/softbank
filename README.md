# SoftBank Microservices Project

## Overview
EazyBank is a microservices-based project designed to manage banking operations such as accounts, loans, and configurations. The project is built using **Java**, **Spring Boot**, and **Maven**, and follows a modular architecture to ensure scalability and maintainability.

## Features
- **Accounts Service**: Manage customer accounts with CRUD operations.
- **Loans Service**: Handle loan-related information and operations.
- **Config Server**: Centralized configuration management using Spring Cloud Config.
- **Environment-Specific Configurations**: Externalized configurations for different environments (e.g., `accounts.yml`, `loans.yml`).
- **API Documentation**: Swagger/OpenAPI integration for API documentation.
- **Validation**: Input validation using annotations like `@Valid` and `@Pattern`.
- **RabbitMQ Integration**: Message bus for configuration updates.

## Technologies Used
- **Languages**: Java
- **Frameworks**: Spring Boot, Spring Cloud
- **Build Tool**: Maven
- **Database**: SQL (can be configured)
- **Messaging**: RabbitMQ
- **Configuration Management**: Spring Cloud Config
- **Validation**: Jakarta Validation
- **API Documentation**: Swagger/OpenAPI

## Project Structure
```
eazybank/
├── accounts/                # Accounts microservice
├── loans/                   # Loans microservice
├── configserver/            # Spring Cloud Config Server
├── softbank-config/         # Centralized configuration repository
└── README.md                # Project documentation
```

### Key Modules
1. **Accounts Service**:
   - Handles account creation, updates, deletion, and retrieval.
   - Exposes REST APIs for account management.
   - Uses externalized configuration for contact details.

2. **Loans Service**:
   - Manages loan-related information.
   - Exposes REST APIs for loan operations.
   - Uses externalized configuration for contact details.

3. **Config Server**:
   - Centralized configuration management using Spring Cloud Config.
   - Fetches configurations from a Git repository (`softbank-config`).

4. **Softbank Config**:
   - Stores environment-specific configurations in YAML files.
   - Example: `accounts.yml`, `loans.yml`.

## Prerequisites
- **Java 21** or higher
- **Maven** (latest version)
- **RabbitMQ** (for message bus)
- **Git** (for configuration repository)

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/santhoshshetty/eazybank.git
   cd eazybank
   ```

2. Start RabbitMQ:
   Ensure RabbitMQ is running on `localhost:5672` with default credentials.

3. Configure the Config Server:
   - Clone the configuration repository:
     ```bash
     git clone https://github.com/santhoshshetty/softbank-config.git
     ```
   - Ensure the `configserver/src/main/resources/application.yml` points to the correct Git repository.

4. Build and Run the Services:
   - Build the project:
     ```bash
     mvn clean install
     ```
   - Start the Config Server:
     ```bash
     cd configserver
     mvn spring-boot:run
     ```
   - Start the Accounts and Loans services:
     ```bash
     cd accounts
     mvn spring-boot:run
     cd ../loans
     mvn spring-boot:run
     ```

5. Access the APIs:
   - Accounts Service: `http://localhost:<port>/api`
   - Loans Service: `http://localhost:<port>/api`
   - Config Server: `http://localhost:8071`

## Configuration
- Configurations are stored in the `softbank-config` repository.
- Example configuration for `accounts`:
  ```yaml
  accounts:
    contactDetails:
      name: "John Doe - Developer"
      email: "john@eazybank.com"
  ```

## API Endpoints
### Accounts Service
- **Create Account**: `POST /api/create`
- **Fetch Account**: `GET /api/fetch`
- **Update Account**: `PATCH /api/update`
- **Delete Account**: `DELETE /api/delete`
- **Contact Info**: `GET /api/contactInfo`

### Loans Service
- **Contact Info**: `GET /api/contactInfo`

## Externalized Configuration
- Configurations are managed in the `softbank-config` repository and fetched dynamically by the Config Server.
- Example: `accounts.yml`, `loans.yml`.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Author
Santhosh Shetty
