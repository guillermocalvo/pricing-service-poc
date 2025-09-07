
# pricing-domain

The **domain layer** of the pricing service.


## Purpose

- Defines the **ubiquitous language** of pricing service.
- Contains **business rules** without dependencies on frameworks or infrastructure.
- Acts as the single source of truth for the core pricing logic.


## Dependencies

- No dependencies on other modules.
- No framework dependencies. Pure Java.


## Notes

Changes here should only affect the **business model**.
Do not introduce persistence, HTTP, or framework logic in this module.
