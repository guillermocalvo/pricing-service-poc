
# pricing-bootstrap

The **bootstrap module** of the pricing service.


## Purpose

- Application entry point.
- Configures Spring Boot, dependency injection, and runtime setup.
- Wires together domain, application, infrastructure, and API modules.


## Dependencies

- Depends on all other modules.


## Notes

Contains the Spring Boot `@SpringBootApplication` class.
Sets up database schema and test data.
Environment-specific config also live here.
