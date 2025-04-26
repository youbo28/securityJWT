# Security Setup Overview

This section describes the steps taken to implement a secure and scalable authentication system using JWT (JSON Web Tokens).

---

## 1. Create User and Role Entities

- Defined the `UserDetails` entity representing application users.
- Created a `Role` entity to manage user roles and permissions.
- Established a relationship between `UserDetails` and `Role`.

✅ Focused on a flexible, extendable design to support future growth.

---

## 2. Create Repositories

- Built `UserDetailsRepository` for user entity operations.
- Built `RoleRepository` for role entity operations.
- Used Spring Data JPA for simplified CRUD operations.

✅ Simplified data access and management for authentication.

---

## 3. Create User Service

- Implemented a `UserDetailsService` for user-specific business logic.
- Integrated it with Spring Security's authentication process.
- Managed user registration, retrieval, and security interactions.

✅ Achieved clean separation of concerns and maintainability.

---

## 4. Add JJWT Dependencies

- Added the JJWT library to handle JWT creation, signing, and validation.

✅ Prepared the environment for secure token operations.

---

## 5. Create JWT Authentication Filter

- Implemented a custom `JWTAuthenticationFilter` extending `OncePerRequestFilter`.
- Responsibilities:
  - Intercept each HTTP request.
  - Extract JWT token from headers.
  - Validate the token and authenticate the user.
  - Populate Spring Security context if valid.

✅ Centralized token validation logic.

---

## 6. Configure Security Filter Chain

- Created a `SecurityFilterChain` bean to define security policies.
- Added the JWT authentication filter before the username-password authentication filter.
- Enabled stateless session management for a true RESTful API.

✅ Customized authentication flow and secured endpoints.

---

## 7. Provide Authentication Provider

- Created an `AuthenticationProvider` bean using `DaoAuthenticationProvider`.
- Linked it with the custom `UserDetailsService` and a password encoder.

✅ Enabled user authentication against the database securely.

---

## 8. Provide Password Encoder

- Provided a `PasswordEncoder` bean using BCrypt algorithm.

✅ Ensured strong password encryption and protection.

---

## 9. Provide Authentication Manager

- Provided an `AuthenticationManager` bean for manual authentication support.

✅ Enabled custom login flows with Spring Security.

---

## 10. Create JWT Service

- Developed a `JWTService` with the following responsibilities:
  - Generate secure, signed JWT tokens for authenticated users.
  - Validate JWT tokens, including expiration and signature.
  - Extract claims like username and roles from the token.

✅ Centralized all JWT-related operations for clean architecture and easier maintenance.

---

## 11. Create Authentication Endpoints

- Created a **register** endpoint at `/api/v1/auth/register`:
  - Allows new users to register and receive a JWT token immediately after registration.
- Created an **authenticate** endpoint at `/api/v1/auth/authenticate`:
  - Allows existing users to log in and receive a fresh JWT token.

✅ Provided a simple and secure user onboarding and login experience.

---

## 12. Create Secured Test Endpoint

- Created a secured test controller at `/api/v1/demo-controller`.
- Accessible only with a valid JWT token.
- Used to verify that authentication and authorization are correctly applied.

✅ Confirmed that the security setup works correctly and only authenticated users can access protected resources.

---

# 🔥 Authentication Flow

```plaintext
[Client Request]
        ↓
[JWTAuthenticationFilter]
        ↓
[AuthenticationManager]
        ↓
[UserDetailsService]
        →
[UserDetailsRepository]
        ↓
[JWTService (generate token, validate token, extract claims)]
        ↓
[Spring Security Context Setup]
```
# 🙌 Huge Shoutout

A massive **thank you** and **huge shoutout** to [**Bouali**](https://www.youtube.com/watch?v=BVdQ3iuovg0&pp=ygUWYm91YWxpIHNwcmluZyBzZWN1cml0edIHCQmECQGHKiGM7w%3D%3D) for his excellent Spring Security tutorial! 🎥🔥

> His clear explanations, hands-on examples, and structured approach were incredibly helpful in guiding the implementation of this secure authentication system.

✅ Highly recommend checking out his work if you're building security with Spring Boot and JWT!

---


