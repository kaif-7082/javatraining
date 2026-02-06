**Gateway Service**
* Overview The Gateway Service serves as the single entry point for all client requests. Built on Spring Cloud Gateway, it handles routing, load balancing, and cross-cutting concerns like distributed tracing. Clients communicate only with this gateway, which then forwards requests to the appropriate microservice.

**Tech Stack**

* Java 21

* Spring Boot 3.5.8

* Spring Cloud Gateway

* Spring Cloud Netflix Eureka Client

* Micrometer & Zipkin (Distributed Tracing)

**Configuration**

* Port: 8085

**Application Name:** gateway

**Key Features**

* Centralized Routing: Dynamically routes incoming requests to the correct microservice based on the URL path (e.g., /companies/** -> companyms, /jobs/** -> jobms).

* Load Balancing: Integrates with Eureka to load balance requests across multiple instances of a service using the lb:// URI scheme.

* Distributed Tracing: Acts as the start of the trace for incoming requests. It tags requests with Trace IDs and Span IDs before forwarding them, allowing full observability via Zipkin.

* Unified Configuration: Simplifies client interactions by exposing a single hostname and port (localhost:8085) for the entire backend ecosystem.

**Routes Configuration**

| Path Predicate | Target Service ID | Description |

| /companies/** | companyms | Routes to Company Service |

| /jobs/** | jobms | Routes to Job Service |

| /reviews/** | reviewms | Routes to Review Service |

| /signin, /signup | auth-service | Routes to Security Service |

**How to Run** 
Infrastructure: Ensure Eureka and Zipkin are running.

Run:

./mvnw spring-boot:run