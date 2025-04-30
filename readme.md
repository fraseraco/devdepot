# /dev/depot

A full-stack e-commerce platform for browsing and purchasing PC components.

This project integrates a secure Spring Boot backend with a React.js frontend, supported by a MySQL database. It implements stateless JWT-based authentication, modular UI components, and a flexible RBAC schema. Designed with layered architecture and modern development practices, /dev/depot was built using Agile workflows and delivered as part of a senior capstone.

---

## Tech Stack

- **Backend**: Java Spring Boot, Spring Security, Spring Data JPA
- **Frontend**: React + Vite, React Router, Axios, Context API
- **Database**: MySQL
- **Docs & API**: OpenAPI 3.1, Swagger UI (`/api-docs`)

---

## Directory Structure

```bash
.
├── backend/        # Spring Boot backend (API, services, auth, security)
├── depot-site/     # React + Vite frontend (SPA)
├── db/             # MySQL schema and insert scripts
├── docs/           # Presentation, ERD, API Docs
├── resources/      # Temporary storage (JSON, images, etc.)
├── auth/           # [DEPRECATED]
├── testAPI/        # [DEPRECATED]
└── untracked/      # [NOT INCLUDED IN GITHUB]
```

---

## Core Features

- JWT-secured login and registration
- Role-based access control (admin, customer)
- Product browsing with dynamic filtering
- Cart and checkout system with order tracking
- Admin endpoints for user and inventory management
- OpenAPI/Swagger UI for API testing and inspection

---

## Planned Enhancements

- Integration with Stripe/PayPal
- Admin dashboard for inventory and order fulfillment
- Docker-based deployment setup
- Expanded testing and modular service separation
