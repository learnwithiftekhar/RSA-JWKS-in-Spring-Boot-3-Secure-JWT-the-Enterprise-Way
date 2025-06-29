# RSA & JWKS in Spring Boot 3: Secure JWT the Enterprise Way

A Spring Boot application demonstrating JWT-based authentication and authorization using Spring Security OAuth2 Resource Server, with a focus on enterprise-grade security using RSA key pairs and JWKS (JSON Web Key Set).

This project uses RSA key pairs for signing and verifying JWT tokens. You can generate your own keys using OpenSSL:

```bash
# 1. Generate a 2048-bit RSA *private* key (PKCS #8 -- Nimbus & Spring accept this as-is)
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048

# 2. Derive the matching *public* key
openssl rsa -pubout -in private.pem -out public.pem
```

Place the generated `private.pem` and `public.pem` files in `src/main/resources/certs/` and reference them in your configuration.

> **Note:**
> - The provided RSA key files (`private.pem` and `public.pem`) are for demonstration purposes only. **Do not use these keys in production.**
> - Every developer must generate their own unique RSA key pair for any real-world or production use. See the instructions below for generating your own keys.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Development](#development)
- [Testing](#testing)
- [License](#license)
- [Author](#author)

## Related YouTube Tutorials
- [Spring Boot 3: Secure Your API with HS256 JWTs (Part 1)](https://www.youtube.com/watch?v=nX66hgUcmS0)
- [RSA & JWKS in Spring Boot 3: Secure JWT the Enterprise Way (Part 2)](https://youtu.be/1-Bf7nrLSds)
- [Spring Boot 3 JWT RBAC — Lock Down Endpoints with User & Admin Roles (Part 3)](https://youtu.be/aDEkqNcSzuA)

## Overview
This project showcases how to implement secure JWT (JSON Web Token) authentication in a Spring Boot application. It provides a robust security layer for REST APIs with token-based authentication, leveraging RSA key pairs and JWKS for enterprise-level security.

## Features
- JWT-based authentication and authorization
- Spring Security integration
- Token generation and validation
- Secure API endpoints
- MySQL database integration with Spring Data JPA
- Input validation using Spring Validation
- RSA key pair support for JWT signing and verification (with OpenSSL key generation instructions)
- JWKS (JSON Web Key Set) ready configuration for scalable public key distribution

## Project Structure
```
src/main/java/com/learnwithiftekhar/spring_jwt_demo/
├── config/           # Security configuration
├── controller/       # REST controllers (Auth, Home)
├── dto/              # Data Transfer Objects (LoginRequest, LoginResponse)
├── model/            # JPA entities (User)
├── ropository/       # Spring Data JPA repositories
├── service/          # Business logic (JWT, User)
src/main/resources/
├── application.properties  # Main configuration
├── certs/                  # RSA keys for JWT
```

## Prerequisites
- Java 17
- Maven
- MySQL database

## Dependencies
- Spring Boot 3.5.0
- Spring Data JPA
- Spring Security OAuth2 Resource Server
- Spring Validation
- Spring Web
- MySQL Connector

## Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/learnwithiftekhar/RSA-JWKS-in-Spring-Boot-3-Secure-JWT-the-Enterprise-Way.git
cd RSA-JWKS-in-Spring-Boot-3-Secure-JWT-the-Enterprise-Way
```

### 2. Configure database
Edit the `src/main/resources/application.properties` file to configure your MySQL database connection:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<your_database>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure JWT keys

Generate RSA key pair for JWT signing and verification:

```bash
# 1. Generate a 2048-bit RSA *private* key (PKCS #8 -- Nimbus & Spring accept this as-is)
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048

# 2. Derive the matching *public* key
openssl rsa -pubout -in private.pem -out public.pem
```

Place the generated `private.pem` and `public.pem` files in `src/main/resources/certs/`.

**Note:** This RSA key is for demonstration only. Do not use in production.

Reference these in your `application.properties`:
```properties
security.jwt.private-key=classpath:certs/private.pem
security.jwt.public-key=classpath:certs/public.pem
```

### 4. Build and run the application
```bash
mvn clean install
mvn spring-boot:run
```
The application will start on port 8080 by default.

## API Endpoints

### Authentication
- `POST /api/v1/login`  
  Request: `{ "username": "admin", "password": "admin" }`  
  Response: `{ "token": "<JWT_TOKEN>" }`

### Protected Endpoint
- `GET /api/v1/hello`  
  Requires: `Authorization: Bearer <JWT_TOKEN>`  
  Response: `Hello <username>`

## Configuration
- Database and JWT key configuration is in `src/main/resources/application.properties`.
- Default user is created on startup: `admin` / `admin`.
- Token expiration is 15 minutes (see `JwtService`).

## Development
- To build:  
  `mvn clean install`
- To run:  
  `mvn spring-boot:run`

## Testing
- To run tests:  
  `mvn test`

## License
This project is for educational/demo purposes.

## Author
Iftekhar Hossain  
Email: learnwithiftekhar@gmail.com

[My YouTube Channel](https://www.youtube.com/@learnWithIfte)

[LinkedIn](https://www.linkedin.com/in/hossain-md-iftekhar/)

