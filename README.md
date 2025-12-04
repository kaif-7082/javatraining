**Security Microservice**
* Overview The Security Microservice (Auth Service) acts as the centralized identity provider for the system. It handles user registration, authentication, and the issuance of JWT (JSON Web Tokens). Other microservices rely on the tokens generated here to authorize requests.

**Tech Stack**

* Java 21

* Spring Boot 3.5.7

* Spring Security

* JSON Web Tokens (JJWT)

* MySQL Database

* Spring Cloud Netflix Eureka Client

**Configuration**

* Port: 8084

* Database: MySQL (auth_db)

**Application Name:** auth-service

**Key Features**

* **JWT Token Generation:** Generates signed JWTs containing user identity and roles (claims) upon successful login. These tokens have a configurable expiration time.

* **User Management:** Provides endpoints for new users to sign up and for existing users to sign in. It checks for duplicate usernames and validates credentials.

* **Role Management:** Assigns roles such as USER or ADMIN during registration. These roles are embedded in the JWT and used by downstream services to enforce RBAC (Role-Based Access Control).

* **Password Encryption:** Utilizes BCryptPasswordEncoder to securely hash and store user passwords in the database.

* **JDBC User Details:** Implements Spring Security's JdbcUserDetailsManager to manage user persistence directly in a relational database.

**API Endpoints**

| Method | Endpoint | Description | Auth |

| POST | /signup | Register a new user account | Public |

| POST | /signin | Authenticate and receive JWT | Public |

| GET | /hello | Simple connectivity test | Public |

| GET | /user | Test endpoint for USER role | User |

| GET | /admin | Test endpoint for ADMIN role | Admin |

How to Run Infrastructure: Ensure MySQL and Eureka are running.

Run:

./mvnw spring-boot:run