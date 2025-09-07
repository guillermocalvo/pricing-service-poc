
# pricing-application

The **application layer** of the pricing service.


## Purpose

- Implements **use cases**.
- Orchestrates domain objects to fulfill business flows.
- Defines **ports** for required infrastructure.


## Dependencies

- Depends on **pricing-domain** (uses domain entities and value objects).
- Defines interfaces that adapters must implement.


## Notes

This layer contains no framework code.
Spring annotations are avoided here to keep it framework-agnostic.
