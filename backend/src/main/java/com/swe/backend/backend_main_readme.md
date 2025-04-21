backend/src/main/java/com/swe/backend – Application Source Code
This directory contains the core business logic and structure for the Spring Boot backend powering the /dev/depot eCommerce platform. Each package is modularized for maintainability and clear responsibility separation.

### Folder Structure & Purpose
•	- `Config/`: Spring Boot configuration classes (e.g., CORS, WebSecurity, beans)
•	- `Controller/`: REST controllers exposing endpoints to the frontend
•	- `DTOs/`: Data Transfer Objects used for request and response payloads
•	- `Entity/`: JPA entity classes mapping to database tables
•	- `Exceptions/`: Custom exception classes and global exception handling
•	- `Mappers/`: Mapper interfaces or classes for DTO-Entity conversion
•	- `Repository/`: JPA repositories for CRUD operations
•	- `Security/`: Security configurations including JWT filters, encoders, and auth logic
•	- `Service/`: Business service layer that handles core processing logic
•	- `UserDto/`: Specialized user-related DTOs and converters
•	- `Util/`: Utility/helper classes for tasks like validations or string formatting
•	- `Views/`: Classes representing views or projections (if applicable)

### Entry Point
`BackendApplication.java` – This is the Spring Boot main class that serves as the application's entry point.

### Notes
• Organized following standard MVC + service-layer practices.
• DTOs improve data encapsulation between layers.
• Secure architecture using Spring Security and JWT.
• Exception handling ensures robust API responses.
