backend – Spring Boot API Server
This directory contains the main Spring Boot application powering the backend services of the /dev/depot eCommerce platform. It handles business logic, API routing, database communication, and user management.

Contents:
Main is in separate README
•	- `src/`: Java source files organized by feature (e.g., auth, products, orders)
•	- `AuthController_TEMP.java.bak`: Backup or legacy version of an authentication controller
•	- `instructions-for-local-deployment.txt`: Notes on setting up and running the backend locally
•	- `pom.xml`: Maven configuration for managing dependencies
•	- `mvnw`, `mvnw.cmd`: Maven wrapper scripts for Unix and Windows systems

Setup Instructions:
1. Navigate to the `backend` directory
2. Run `./mvnw clean install` to build the project
3. Run `./mvnw spring-boot:run` to start the API server
4. Server will run on `http://localhost:8080` by default

Notes:
Ensure that the database is running and the schema has been created before starting the backend. Update `application.properties` as needed for your local DB credentials.
