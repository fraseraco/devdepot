# db – MySQL Database Setup

This folder contains all SQL scripts and supporting files required to initialize the MySQL database schema for the `/dev/depot` e-commerce platform. It includes user and role definitions, permission sets, and seed product data.

---

## Contents

- `init_db/`: Schema creation scripts and insert statements for roles and permissions
- `product_inserts/`: Product catalog insert scripts
- `json/`: Temporary JSON blobs for dev or testing use

---

## Schema Overview

- `user`, `role`, `permission`, `role_permission`: Auth and RBAC system
- `product`: Product catalog entries with JSON-based specifications field
- `cart`, `cart_item`: Shopping cart persistence before checkout
- `order`, `order_item`: Completed order records
- `payment`: Payment metadata, linked to orders

The schema supports referential integrity via foreign keys and includes indexing for efficient lookup.

A visual representation is available in:

```
/docs/ER_Diagram.pdf
```

---

## Setup Instructions

1. Ensure MySQL is installed and running
2. From your MySQL client:

```sql
SOURCE ./db/init_db/create_db.sql;
SOURCE ./db/init_db/insertr.sql;
SOURCE ./db/init_db/insertp.sql;
```

3. Confirm that:
   - Tables are created and populated
   - You can connect with valid credentials
   - The DB name and port match the backend's `application.properties`

---

## Notes

- Product specifications are stored as a JSON field in the `product` table
- Permissions are linked to roles using a join table (`role_permission`)
- Cart items and order items are normalized to reduce redundancy
- Be sure to match your local MySQL port and auth config to what the Spring backend expects
