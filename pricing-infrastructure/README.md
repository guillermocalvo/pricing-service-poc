
# pricing-infrastructure

A **infrastructure layer** of the pricing service that implements the query port via REST and JPA.


## Purpose

- Provides technical implementations for the ports defined in **pricing-application**.
- Exposes application use cases via **REST controllers**.
- Maps HTTP requests to application services.
- Handles serialization/deserialization.
- Integrates with frameworks and external systems.


## Dependencies

- Depends on **pricing-application** (to implement its ports).
- Uses Spring Web, Jackson, SpringDoc (for OpenAPI).
- Uses Spring Data JPA.


## Notes

Interfaces from **pricing-application** are mapped to DTOs here.
Keep this layer thin (request/response handling only).
This module may change when infrastructure changes, but should not affect domain or application modules.
