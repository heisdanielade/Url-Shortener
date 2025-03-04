# URL Shortener API

A simple URL Shortener API built using Spring Boot. This API allows users to shorten long URLs and retrieve the original URL using a generated short code.

## Features
- Shorten a given URL
- Retrieve the original URL using a short code

## Technologies Used
- Java
- Spring Boot
- Spring Web
- Spring Data JPA

## Installation & Setup
### Prerequisites
- Java 17+
- Maven 3+
- (Optional) PostgreSQL/MySQL for persistent storage

### Running the Application
1. Clone the repository:
   ```sh
   git clone https://github.com/heisdanielade/Url-Shortener.git
   cd urlshortener
   ```
2. Build and run the application using Maven:
   ```sh
   ./mvnw spring-boot:run
   ```
3. The API will be available at `http://localhost:8080`

## API Endpoints
### 1. Shorten URL
**Endpoint:** `POST /api/v1/shorten`

**Request Body:**
```json
{
  "url": "https://example.com"
}
```

**Response:**
```json
{
  "shortUrl": "http://localhost:8080/api/v1/abc123"
}
```

### 2. Redirect to Original URL
**Endpoint:** `GET /api/v1/{shortUrl}`

**Example Request:**
```
GET /api/v1/abc123
```

**Response:**
Redirects to the original URL (e.g., `https://example.com`).

## Configuration
You can configure the database and other properties in `application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```
For persistent storage, configure it for PostgreSQL or MySQL.
