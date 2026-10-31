# igikomera

**Student:** NGABONZIZA Kim Gakuba
**Student ID:** 27670

Igikomera is an online marketplace where people can register, advertise domestic animals for sale,
discover animals in different locations, communicate with sellers, negotiate prices, request purchases,
track transactions, manage listings, and report suspicious activity. Administrators supervise users,
animals, categories, listings, reports, and transactions.
The platform is inspired by traditional livestock markets where farmers, traders, and buyers physically
meet to exchange information, negotiate prices, and complete animal transactions. Igikomera moves the
information and marketplace-management part of this process into an organized digital environment.

## Prerequisites

- Java 21
- PostgreSQL running on `localhost:5432`
- The Maven wrapper is included, so no separate Maven installation is required


## Steps to run the API

1. Create the PostgreSQL database:

   ```bash
   sudo -u postgres psql

   enter pasword for your pc

   CREATE DATABASE igikomera;

   \c igikomera
   ```

2. Check the database settings in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/igikomera
   spring.datasource.username=postgres
   spring.datasource.password=12345
   ```

   Update the username and password if your PostgreSQL setup is different.

3. Start the application from the project root:

   ```bash
   ./mvnw spring-boot:run
   ```

4. The API will be available at:

   ```text
   http://localhost:8080/api
   ```

5. (Optional) Load the sample data after the application is running:

   ```bash
   ./seed.sh
   ```

## API endpoints

All URLs below are relative to `http://localhost:8080`.

### Animal types

| Method | URL | Description |
| ------ | --- | ----------- |
| POST   | `http://localhost:8080/api/animal-types/save` | Create an animal type |
| GET    | `http://localhost:8080/api/animal-types/all` | Get all animal types |
| GET    | `http://localhost:8080/api/animal-types/{id}` | Get an animal type by ID |
| PUT    | `http://localhost:8080/api/animal-types/update/{id}` | Update an animal type |
| DELETE | `http://localhost:8080/api/animal-types/delete/{id}` | Delete an animal type |
| GET    | `http://localhost:8080/api/animal-types/unused` | Get animal types not used by any seller |

### Stalls

| Method | URL | Description |
| ------ | --- | ----------- |
| POST   | `http://localhost:8080/api/stalls/save` | Create a stall |
| GET    | `http://localhost:8080/api/stalls/all` | Get all stalls |
| GET    | `http://localhost:8080/api/stalls/{id}` | Get a stall by ID |
| PUT    | `http://localhost:8080/api/stalls/update/{id}` | Update a stall |
| DELETE | `http://localhost:8080/api/stalls/delete/{id}` | Delete a stall |
| GET    | `http://localhost:8080/api/stalls/free` | Get stalls with no seller |

### Sellers

| Method | URL | Description |
| ------ | --- | ----------- |
| POST   | `http://localhost:8080/api/sellers/save` | Create a seller |
| GET    | `http://localhost:8080/api/sellers/all` | Get all sellers |
| GET    | `http://localhost:8080/api/sellers/{id}` | Get a seller by ID |
| PUT    | `http://localhost:8080/api/sellers/update/{id}` | Update a seller |
| DELETE | `http://localhost:8080/api/sellers/delete/{id}` | Delete a seller |
| GET    | `http://localhost:8080/api/sellers/by-animal-type?name={name}` | Get sellers by animal type name |
| PATCH  | `http://localhost:8080/api/sellers/{sellerId}/stall?stallNumber={stallNumber}` | Move a seller to another stall |
| PUT    | `http://localhost:8080/api/sellers/{sellerId}/animal-types/{animalTypeId}` | Add an animal type to a seller |

### Buyers

| Method | URL | Description |
| ------ | --- | ----------- |
| POST   | `http://localhost:8080/api/buyers/save` | Create a buyer |
| GET    | `http://localhost:8080/api/buyers/all` | Get all buyers |
| GET    | `http://localhost:8080/api/buyers/{id}` | Get a buyer by ID |
| PUT    | `http://localhost:8080/api/buyers/update/{id}` | Update a buyer |
| DELETE | `http://localhost:8080/api/buyers/delete/{id}` | Delete a buyer |
| GET    | `http://localhost:8080/api/buyers/by-last-name?lastName={lastName}` | Get buyers by last name |
| GET    | `http://localhost:8080/api/buyers/of-seller/{sellerId}` | Get buyers who bought from a seller |

### Animal sales

| Method | URL | Description |
| ------ | --- | ----------- |
| POST   | `http://localhost:8080/api/animal-sales/save` | Create an animal sale |
| GET    | `http://localhost:8080/api/animal-sales/all` | Get all animal sales |
| GET    | `http://localhost:8080/api/animal-sales/{id}` | Get an animal sale by ID |
| PUT    | `http://localhost:8080/api/animal-sales/update/{id}` | Update an animal sale |
| DELETE | `http://localhost:8080/api/animal-sales/delete/{id}` | Delete an animal sale |
| GET    | `http://localhost:8080/api/animal-sales/by-status?status={status}` | Get sales by status (`INQUIRED`, `NEGOTIATED`, `SOLD`, `WITHDRAWN`) |
| GET    | `http://localhost:8080/api/animal-sales/between?start={yyyy-MM-dd}&end={yyyy-MM-dd}` | Get sales between two dates |
| PATCH  | `http://localhost:8080/api/animal-sales/{id}/status?status={status}` | Change the status of a sale |
| GET    | `http://localhost:8080/api/animal-sales/stats/by-status` | Get sale counts grouped by status |
| PATCH  | `http://localhost:8080/api/animal-sales/cancel-day?sellerId={sellerId}&date={yyyy-MM-dd}` | Cancel all sales of a seller on a date |
| GET    | `http://localhost:8080/api/animal-sales/page?page={page}&size={size}&sort={field,asc\|desc}` | Get a paginated and sorted list of sales |

### Payments

| Method | URL | Description |
| ------ | --- | ----------- |
| POST   | `http://localhost:8080/api/payments/save?animalSaleId={animalSaleId}` | Create a payment for a sale |
| GET    | `http://localhost:8080/api/payments/buyer/{nationalId}` | Get payments by buyer national ID |
| DELETE | `http://localhost:8080/api/payments/delete/{id}` | Delete a payment |

### Dashboard

| Method | URL | Description |
| ------ | --- | ----------- |
| GET    | `http://localhost:8080/api/dashboard/summary` | Get the dashboard summary |

A Postman collection is included in `igikomera-midterm.postman_collection.json` for testing the API.
