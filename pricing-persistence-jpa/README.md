
# pricing-persistence-jpa

A **infrastructure layer** of the pricing service that provides persistence via JPA.


## Purpose

- Provides technical implementations for the persistence port defined in **pricing-application**.
- Integrates with frameworks and external systems.


## Dependencies

- Depends on **pricing-application** (to implement its persistence port).
- Uses Spring Data JPA.


## Notes

This module may change when infrastructure changes, but should not affect domain or application modules.
