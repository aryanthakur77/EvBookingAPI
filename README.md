# EvBookingAPI
The EV Booking Application is a Spring Boot-based backend system designed to manage electric vehicle (EV) charging station operations. It provides RESTful APIs for user authentication, station management, booking EV charging slots, and viewing station usage analytics. The system ensures secure access using JWT-based authentication with role-based controls for Admin and EV Owner.

Key features include:

Station management with available slot tracking

Slot booking system with conflict handling

Station usage analytics using custom DTOs

Spring AOP for logging and validation

Spring Security with JWT for secure access

H2 and JUnit 5-based unit testing

OpenAPI (Swagger) for interactive API documentation

The application is structured using standard packages like controller, service, repository, config, entity, DTO, and util for clean modular design.
