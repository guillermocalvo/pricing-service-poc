
# pricing-query-rest

A **infrastructure layer** of the pricing service that implements the query port via REST.


## Purpose

- Exposes application use cases via **REST controllers**.
- Maps HTTP requests to application services.
- Handles serialization/deserialization.


## Dependencies

- Depends on **pricing-application** (to implement its query port).
- Uses Spring Web, Jackson, SpringDoc (for OpenAPI).


## Notes

Interfaces from **pricing-application** are mapped to DTOs here.
Keep this layer thin (request/response handling only).
