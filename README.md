# Celsius to Fahrenheit Converter REST API

A simple yet robust RESTful web service built with Spring Boot that converts temperatures between Celsius and Fahrenheit.

## Project Structure

```
CtoFconverterAPI/
├── src/
│   ├── main/
│   │   ├── java/com/converter/
│   │   │   ├── TemperatureConverterApplication.java (Main class)
│   │   │   ├── controller/
│   │   │   │   └── TemperatureController.java (REST endpoints)
│   │   │   ├── service/
│   │   │   │   └── TemperatureConverter.java (Business logic)
│   │   │   └── dto/
│   │   │       └── ConversionResponse.java (Response object)
│   │   └── resources/
│   │       └── application.properties (Configuration)
│   └── test/
│       └── java/com/converter/
│           ├── controller/
│           │   └── TemperatureControllerTest.java
│           └── service/
│               └── TemperatureConverterTest.java
└── pom.xml (Maven configuration)
```

## Features

- ✅ **REST API** - Simple GET endpoint for temperature conversion
- ✅ **Input Validation** - Prevents temperatures below absolute zero
- ✅ **Error Handling** - Proper HTTP status codes and error messages
- ✅ **Unit Tests** - Comprehensive test coverage
- ✅ **Spring Boot** - Modern Java framework with minimal configuration
- ✅ **Maven** - Build automation and dependency management

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Installation

1. **Clone/Copy the project**
   ```bash
   cd CtoFconverterAPI
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

## Running the Application

### Option 1: Using Maven Spring Boot Plugin
```bash
mvn spring-boot:run
```

### Option 2: Building and Running JAR
```bash
mvn clean package
java -jar target/celsius-fahrenheit-api-1.0.0.jar
```

The API will be available at `http://localhost:8080`

## API Endpoints

### 1. Convert Celsius to Fahrenheit
**Endpoint:** `GET /api/convert`

**Parameters:**
- `celsius` (required): Temperature value in Celsius (double)

**Example Request:**
```bash
curl "http://localhost:8080/api/convert?celsius=0"
```

**Response (200 OK):**
```json
{
  "celsius": 0.0,
  "fahrenheit": 32.0
}
```

**Error Response (400 Bad Request):**
```json
{
  "error": "Temperature cannot be below absolute zero (-273.15°C)"
}
```

### 2. Health Check
**Endpoint:** `GET /api/health`

**Example Request:**
```bash
curl "http://localhost:8080/api/health"
```

**Response (200 OK):**
```
Temperature Converter API is running
```

## Example Conversions

| Celsius | Fahrenheit | Description |
|---------|-----------|-------------|
| 0       | 32.0      | Freezing point of water |
| 100     | 212.0     | Boiling point of water |
| 37      | 98.6      | Normal body temperature |
| -40     | -40.0     | Same in both scales |
| -273.15 | -459.67   | Absolute zero (limit) |

## Running Tests

### Run all tests
```bash
mvn test
```

### Run specific test class
```bash
mvn test -Dtest=TemperatureConverterTest
mvn test -Dtest=TemperatureControllerTest
```

### With coverage report
```bash
mvn clean test jacoco:report
```

## Test Coverage

- **TemperatureConverterTest**: Tests conversion logic with edge cases
  - Zero (freezing point)
  - Positive values
  - Negative values
  - Decimal precision
  - Absolute zero validation

- **TemperatureControllerTest**: Tests REST endpoint behavior
  - Valid conversions
  - Invalid input handling
  - HTTP status codes
  - Health check endpoint

## Conversion Formulas

**Celsius to Fahrenheit:**
```
F = (C × 9/5) + 32
```

**Fahrenheit to Celsius:**
```
C = (F - 32) × 5/9
```

## Dependencies

- **Spring Boot 3.2.2** - Web framework
- **Spring Test** - Testing utilities
- **JUnit 5** - Unit testing framework
- **Lombok** (optional) - Boilerplate reduction

## Configuration

Edit `src/main/resources/application.properties` to customize:

```properties
# Server port (default: 8080)
server.port=8080

# Logging level
logging.level.com.converter=DEBUG
```

## Error Handling

The API provides meaningful error messages:

- **400 Bad Request**: Invalid input or temperature below absolute zero
- **404 Not Found**: Endpoint does not exist
- **500 Internal Server Error**: Unexpected server error

## Future Enhancements

- [ ] Add Fahrenheit to Celsius conversion endpoint
- [ ] Add batch conversion support
- [ ] Add Swagger/OpenAPI documentation
- [ ] Add Kelvin temperature scale
- [ ] Implement caching for repeated conversions
- [ ] Add API rate limiting

## License

MIT License - Feel free to use and modify

## Author

Temperature Converter Service
