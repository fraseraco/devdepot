# Instructions – Running /dev/depot Locally

This guide walks you through setting up the MySQL database, running the Spring Boot backend, and starting the React frontend for the `/dev/depot` e-commerce platform.

---

## 1. Initialize the MySQL Database

### 1a. Create the database

From your MySQL client, execute the schema creation script:

```sql
SOURCE ./db/init_db/create_db.sql;
```

### 1b. Insert roles and products

Then populate the database with initial data:

```sql
SOURCE ./db/init_db/insertr.sql;
SOURCE ./db/init_db/insertp.sql;
```

### 1c. Ensure MySQL is active

Verify your MySQL server is running and accessible on the desired port.

### 1d. Note your connection details

You will need:

- **Host** (e.g. `localhost`)
- **Port** (default: `3306`)
- **Username** (e.g. `root`)
- **Password** (e.g. default typically `root`)
- **Database name** (e.g. `devdepot`)

---

## 2. Run the Spring Boot Backend

### 2a. Configure the database connection

Open and edit:

```
/backend/src/main/resources/application.properties
```

Update the following lines:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/devdepot
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 2b. Build and run the backend

Navigate to the `backend/` directory and run:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

If `mvnw` is not executable on your system, run:

```bash
chmod +x mvnw
```

### 2c. Confirm the backend is running

Visit Swagger UI in your browser:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 3. Run the React Frontend

### 3a. Install dependencies

From the `depot-site/` directory:

```bash
npm install
```

### 3b. Start the frontend

```bash
npm run dev
```

By default, the app runs at:

```
http://localhost:5173/
```

### 3c. Confirm functionality

- Open the frontend in your browser
- Navigate and interact with the app
- Ensure the backend (port `8080`) is running in parallel

---

## Final Tips

- Keep both backend and frontend running in separate terminal tabs
- Use Swagger to manually test backend endpoints
- Ensure JWTs are passed for protected routes (handled automatically by Axios)
