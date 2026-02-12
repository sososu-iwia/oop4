# SOLID in this project

## DIP (Dependency Inversion Principle) — mandatory

**Before:** A service could depend directly on a concrete repository (e.g. `UserJdbcRepository`).

**After:** Services depend on abstractions (interfaces):
- `AuthService` depends on `UserRepository` and `EmailService`, not on `UserJdbcRepository` or `EmailServiceImpl`.
- `StudentServiceImpl` depends on `StudentService` (interface) and `StudentRepository` (interface).

**Benefit:** We can swap implementations (e.g. another `UserRepository` implementation or a mock for tests) without changing the service. Database and email logic stay behind interfaces.

## SRP (Single Responsibility Principle)

**Example:** `AuthService` is responsible only for registration and validation. Sending email is delegated to `EmailService`. User persistence is delegated to `UserRepository`. Each class has one reason to change.

**Benefit:** Easier to test and maintain; changes in email or storage do not affect auth logic.
