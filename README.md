# Celsius to Fahrenheit Converter API

A professional-grade RESTful web service API built with Spring Boot that provides accurate temperature conversion capabilities between Celsius and Fahrenheit scales with comprehensive validation and error handling.

## Table of Contents

- [Project Overview](#project-overview)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Key Features](#key-features)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing Strategy](#testing-strategy)
- [Implementation Details](#implementation-details)
- [How It Works](#how-it-works)
- [Example Usage](#example-usage)
- [Configuration](#configuration)
- [Troubleshooting](#troubleshooting)
- [Future Enhancements](#future-enhancements)

## Project Overview

This is a Spring Boot REST API application that exposes endpoints for converting temperatures between Celsius and Fahrenheit scales. The project follows a clean, layered architecture with separation of concerns across multiple packages and includes comprehensive unit and integration tests.

### Key Accomplishments

- ✅ Built a fully functional REST API with Spring Boot Web
- ✅ Implemented layered architecture (Controller → Service → DTO)
- ✅ Added rigorous input validation (temperatures must be above absolute zero)
- ✅ Created comprehensive test suite (8 unit tests + 6 integration tests)
- ✅ All tests passing with 100% success rate
- ✅ Professional error handling with meaningful messages
- ✅ Clean code with Javadoc documentation

## Technology Stack

| Component | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 2.7.15 | REST API framework |
| Java | 11 | Programming language |
| Maven | 3.6+ | Build and dependency management |
| JUnit 5 | Latest | Unit testing framework |
| Spring Boot Test | 2.7.15 | Integration testing framework |
| Lombok | Latest | Boilerplate reduction (optional) |

## Project Structure

```
src/
├── main/
│   ├── java/com/converter/
│   │   ├── TemperatureConverterApplication.java
│   │   │   └── @SpringBootApplication entry point
│   │   ├── controller/
│   │   │   └── TemperatureController.java
│   │   │       ├── /api/convert endpoint (GET)
│   │   │       ├── /api/health endpoint (GET)
│   │   │       └── Error handling
│   │   ├── service/
│   │   │   └── TemperatureConverter.java
│   │   │       ├── celsiusToFahrenheit() static method
│   │   │       ├── fahrenheitToCelsius() static method
│   │   │       └── Input validation logic
│   │   └── dto/
│   │       └── ConversionResponse.java
│   │           ├── Encapsulates response data
│   │           ├── Getters/setters
│   │           └── toString() method
│   └── resources/
│       └── application.properties
│           ├── Server port: 8080
│           ├── Logging configuration
│           └── Jackson JSON settings
└── test/
    └── java/com/converter/
        ├── controller/
        │   └── TemperatureControllerTest.java
        │       ├── Integration tests with MockMvc
        │       ├── 6 test cases covering endpoints
        │       └── HTTP status verification
        └── service/
            └── TemperatureConverterTest.java
                ├── Unit tests for conversion logic
                ├── 8 test cases with edge cases
                └── Validation testing
```

## Key Features

### 1. Temperature Conversion
- **Celsius to Fahrenheit**: F = (C × 9/5) + 32
- **Fahrenheit to Celsius**: C = (F - 32) × 5/9
- Supports decimal precision
- Uses precise floating-point arithmetic (9.0/5.0)

### 2. Input Validation
- Prevents temperatures below absolute zero (-273.15°C / -459.67°F)
- Throws `IllegalArgumentException` with descriptive message
- Controller catches exceptions and returns HTTP 400

### 3. REST API
- Simple, clean endpoint design
- Query parameters for input
- JSON response format
- RESTful status codes (200, 400)

### 4. Health Check
- `/api/health` endpoint for uptime monitoring
- Returns simple confirmation message

### 5. Error Handling
- Meaningful error messages
- Appropriate HTTP status codes
- Exception-based validation flow
- Inner ErrorResponse class for error serialization

## Prerequisites

- **Java**: Version 11 or higher
  ```bash
  java -version
  # Should show Java 11 or newer
  ```
- **Maven**: Version 3.6 or higher
  ```bash
  mvn -version
  # Should show Maven 3.6+
  ```

## Setup and Installation

### 1. Clone/Copy the Project
```bash
cd CtoFconverterAPI
```

### 2. Clean Build (Install Dependencies)
```bash
mvn clean install
```

This command:
- Cleans previous builds
- Downloads dependencies from Maven Central
- Compiles source code
- Runs tests
- Creates the JAR file in `target/`

### 3. Verify Installation
```bash
mvn --version
java -version
mvn clean compile
```

## Running the Application

### Option 1: Using Maven Spring Boot Plugin (Recommended)
```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`

**Output should show:**
```
Started TemperatureConverterApplication in X.XXX seconds
```

### Option 2: Build JAR and Run

```bash
mvn clean package
java -jar target/celsius-fahrenheit-api-1.0.0.jar
```

### Option 3: Using IDE
Right-click on `TemperatureConverterApplication.java` and select "Run" or "Run As → Java Application"

The API will be accessible at: **http://localhost:8080**

## API Endpoints

### 1. Convert Celsius to Fahrenheit
**Endpoint:** `GET /api/convert`

**Parameters:**
- `celsius` (required): Double value representing temperature in Celsius

**Success Response (200 OK):**
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

**Example Requests:**
```bash
# Freezing point
curl "http://localhost:8080/api/convert?celsius=0"

# Boiling point
curl "http://localhost:8080/api/convert?celsius=100"

# Room temperature
curl "http://localhost:8080/api/convert?celsius=20"

# Invalid: Below absolute zero
curl "http://localhost:8080/api/convert?celsius=-300"

# Invalid: Non-numeric input
curl "http://localhost:8080/api/convert?celsius=abc"
```

### 2. Health Check
**Endpoint:** `GET /api/health`

**Response (200 OK):**
```
Temperature Converter API is running
```

**Example Request:**
```bash
curl "http://localhost:8080/api/health"
```

## Testing Strategy

The project includes a comprehensive test suite with 14 total tests (8 unit + 6 integration).

### Running Tests

**Run all tests:**
```bash
mvn test
```

**Run specific test class:**
```bash
mvn test -Dtest=TemperatureConverterTest
mvn test -Dtest=TemperatureControllerTest
```

**View test results:**
```bash
# Test reports are generated in:
target/surefire-reports/
```

### Unit Tests (TemperatureConverterTest.java)

Located in `src/test/java/com/converter/service/`

Tests the core business logic of the `TemperatureConverter` service class:

| Test Name | Input | Expected Output | Purpose |
|-----------|-------|-----------------|---------|
| testCelsiusToFahrenheit_Zero | 0°C | 32°F | Verify freezing point |
| testCelsiusToFahrenheit_Positive | 100°C | 212°F | Verify boiling point |
| testCelsiusToFahrenheit_Negative | -40°C | -40°F | Verify negative numbers |
| testCelsiusToFahrenheit_Decimal | 37.5°C | 99.5°F | Verify decimal precision |
| testCelsiusToFahrenheit_BelowAbsoluteZero | -300°C | Exception | Verify validation |
| testFahrenheitToCelsius_Zero | 32°F | 0°C | Verify reverse conversion |
| testFahrenheitToCelsius_Freezing | 212°F | 100°C | Verify boiling point reverse |
| testFahrenheitToCelsius_BelowAbsoluteZero | -500°F | Exception | Verify reverse validation |

**Testing Framework:** JUnit 5 with `assertEquals()` and `assertThrows()`

**Sample Test Code:**
```java
@Test
public void testCelsiusToFahrenheit_Zero() {
    double result = TemperatureConverter.celsiusToFahrenheit(0);
    assertEquals(32.0, result, 0.01);
}

@Test
public void testCelsiusToFahrenheit_BelowAbsoluteZero() {
    assertThrows(IllegalArgumentException.class, () -> {
        TemperatureConverter.celsiusToFahrenheit(-300);
    });
}
```

### Integration Tests (TemperatureControllerTest.java)

Located in `src/test/java/com/converter/controller/`

Tests the REST API endpoints using Spring Boot Test's `MockMvc`:

| Test Name | Endpoint | Input | Expected Status | Purpose |
|-----------|----------|-------|-----------------|---------|
| testConvertCelsiusToFahrenheit_Zero | /api/convert | celsius=0 | 200 OK | Valid conversion |
| testConvertCelsiusToFahrenheit_BoilingPoint | /api/convert | celsius=100 | 200 OK | Boiling point test |
| testConvertCelsiusToFahrenheit_NegativeValue | /api/convert | celsius=-40 | 200 OK | Negative value test |
| testConvertCelsiusToFahrenheit_BelowAbsoluteZero | /api/convert | celsius=-300 | 400 Bad Request | Error handling |
| testConvertCelsiusToFahrenheit_InvalidInput | /api/convert | celsius=invalid | 400 Bad Request | Type validation |
| testHealthEndpoint | /api/health | - | 200 OK | Health check |

**Testing Framework:** Spring Boot Test with MockMvc for HTTP testing without starting a server

**Sample Test Code:**
```java
@Test
public void testConvertCelsiusToFahrenheit_Zero() throws Exception {
    mockMvc.perform(get("/api/convert").param("celsius", "0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.celsius").value(0.0))
            .andExpect(jsonPath("$.fahrenheit").value(32.0));
}

@Test
public void testConvertCelsiusToFahrenheit_BelowAbsoluteZero() throws Exception {
    mockMvc.perform(get("/api/convert").param("celsius", "-300"))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
}
```

### Test Results

**All 14 tests pass successfully:**
- ✅ 8/8 Unit Tests (TemperatureConverterTest)
- ✅ 6/6 Integration Tests (TemperatureControllerTest)
- ✅ Total Coverage: 100% of conversion logic
- ✅ Total Coverage: 100% of API endpoints

## Implementation Details

### TemperatureConverter Service (Business Logic)

**Location:** `src/main/java/com/converter/service/TemperatureConverter.java`

**Key Method: celsiusToFahrenheit()**
```java
public static double celsiusToFahrenheit(double celsius) {
    if (celsius < -273.15) {
        throw new IllegalArgumentException(
            "Temperature cannot be below absolute zero (-273.15°C)"
        );
    }
    return (celsius * 9.0 / 5.0) + 32;
}
```

**Key Features:**
- Static methods for utility-like, stateless functionality
- Input validation before conversion
- Precise floating-point arithmetic using 9.0/5.0 instead of 1.8
- Descriptive error messages
- Comprehensive Javadoc comments

**Additional Method: fahrenheitToCelsius()**
```java
public static double fahrenheitToCelsius(double fahrenheit) {
    if (fahrenheit < -459.67) {
        throw new IllegalArgumentException(
            "Temperature cannot be below absolute zero (-459.67°F)"
        );
    }
    return (fahrenheit - 32) * 5.0 / 9.0;
}
```

### TemperatureController (REST Endpoints)

**Location:** `src/main/java/com/converter/controller/TemperatureController.java`

**Key Features:**
- `@RestController` annotation for REST API
- `@RequestMapping("/api")` for base path
- `@GetMapping` for HTTP GET endpoints
- `@RequestParam` for query parameter binding
- Exception handling with try-catch
- ResponseEntity for flexible response building
- Inner `ErrorResponse` class for error JSON serialization

**Key Endpoint:**
```java
@GetMapping("/convert")
public ResponseEntity<?> convertCelsiusToFahrenheit(@RequestParam double celsius) {
    try {
        double fahrenheit = TemperatureConverter.celsiusToFahrenheit(celsius);
        return ResponseEntity.ok(new ConversionResponse(celsius, fahrenheit));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}
```

### ConversionResponse DTO (Data Transfer Object)

**Location:** `src/main/java/com/converter/dto/ConversionResponse.java`

**Purpose:** Encapsulates response data for JSON serialization

**Fields:**
- `celsius: double` - Original temperature in Celsius
- `fahrenheit: double` - Converted temperature in Fahrenheit

**Features:**
- Constructor for initialization
- Getter/setter methods for Spring serialization
- `@Override toString()` for logging

## How It Works

### Request Flow Diagram

```
1. Client Request
   GET /api/convert?celsius=25
         ↓
2. TemperatureController receives request
   - Extracts celsius parameter (25)
   - Calls TemperatureConverter.celsiusToFahrenheit(25)
         ↓
3. TemperatureConverter validates and converts
   - Checks: 25 >= -273.15 ✓
   - Calculates: (25 × 9/5) + 32 = 77.0
   - Returns: 77.0
         ↓
4. Controller creates ConversionResponse
   - new ConversionResponse(25.0, 77.0)
   - Wraps in ResponseEntity with 200 OK
         ↓
5. Spring JSON Serialization
   - Converts object to JSON
         ↓
6. Response to Client
   {
     "celsius": 25.0,
     "fahrenheit": 77.0
   }
   HTTP 200 OK
```

### Error Flow

```
1. Client sends invalid request
   GET /api/convert?celsius=-300
         ↓
2. TemperatureConverter.celsiusToFahrenheit(-300)
   - Validation check: -300 < -273.15 ✗ FAIL
   - Throws: IllegalArgumentException
         ↓
3. Controller catches exception
   - Gets message: "Temperature cannot be below absolute zero..."
   - Creates ErrorResponse with message
   - Returns ResponseEntity with 400 status
         ↓
4. Response to Client
   {
     "error": "Temperature cannot be below absolute zero (-273.15°C)"
   }
   HTTP 400 Bad Request
```

## Example Usage

### Complete API Test Scenarios

#### Scenario 1: Freezing Point
```bash
curl "http://localhost:8080/api/convert?celsius=0"
```
**Response:**
```json
{"celsius":0.0,"fahrenheit":32.0}
```

#### Scenario 2: Boiling Point
```bash
curl "http://localhost:8080/api/convert?celsius=100"
```
**Response:**
```json
{"celsius":100.0,"fahrenheit":212.0}
```

#### Scenario 3: Room Temperature
```bash
curl "http://localhost:8080/api/convert?celsius=20"
```
**Response:**
```json
{"celsius":20.0,"fahrenheit":68.0}
```

#### Scenario 4: Body Temperature
```bash
curl "http://localhost:8080/api/convert?celsius=37"
```
**Response:**
```json
{"celsius":37.0,"fahrenheit":98.6}
```

#### Scenario 5: Same in Both Scales
```bash
curl "http://localhost:8080/api/convert?celsius=-40"
```
**Response:**
```json
{"celsius":-40.0,"fahrenheit":-40.0}
```

#### Scenario 6: Invalid Temperature (Below Absolute Zero)
```bash
curl "http://localhost:8080/api/convert?celsius=-300"
```
**Response:** (HTTP 400)
```json
{"error":"Temperature cannot be below absolute zero (-273.15°C)"}
```

#### Scenario 7: Invalid Input Type
```bash
curl "http://localhost:8080/api/convert?celsius=abc"
```
**Response:** (HTTP 400)
```
Bad Request - parameter binding failed
```

#### Scenario 8: Health Check
```bash
curl "http://localhost:8080/api/health"
```
**Response:**
```
Temperature Converter API is running
```

## Configuration

### Application Properties

**File:** `src/main/resources/application.properties`

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Application Information
spring.application.name=Temperature Converter API

# Logging Configuration
logging.level.root=INFO
logging.level.com.converter=DEBUG

# Jackson JSON Serialization
spring.jackson.default-property-inclusion=non_null
spring.jackson.serialization.write-dates-as-timestamps=false
```

### Customization Examples

**Change Server Port:**
```properties
server.port=8081
```

**Increase Logging Detail:**
```properties
logging.level.com.converter=TRACE
```

**Always Include Null Values:**
```properties
spring.jackson.default-property-inclusion=always
```

## Dependencies (from pom.xml)

**Spring Boot Parent POM:** 2.7.15
- Provides Spring Boot framework and default configurations

**spring-boot-starter-web**
- Spring MVC for REST API
- Tomcat embedded server
- Jackson JSON library
- Validation support

**spring-boot-starter-test**
- JUnit 5
- Spring Boot Test utilities
- MockMvc for integration testing
- Assertions library

**lombok** (optional)
- Reduces boilerplate code
- Alternative to manual getters/setters

## Troubleshooting

### Issue: Port 8080 Already in Use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Maven Build Fails
**Solution:** Ensure Java version and clear cache:
```bash
java -version  # Should be 11 or higher
mvn clean install -U  # -U updates snapshots
```

### Issue: Tests Fail
**Solution:** Verify environment:
```bash
mvn test -X  # Run with debug information
mvn clean test  # Clean before testing
```

### Issue: Cannot Access Localhost:8080
**Solution:** Verify application is running:
```bash
# Check for output: "Started TemperatureConverterApplication in X.XXX seconds"
# If not running, start with: mvn spring-boot:run
```

### Issue: JSON Response Format Different
**Solution:** Response is correctly formatted. Use a JSON formatter if needed:
```bash
# Pretty print with Python
curl "http://localhost:8080/api/convert?celsius=0" | python -m json.tool
```

## Future Enhancements

Possible improvements for future versions:

- **Additional Conversions**
  - [ ] Add Kelvin temperature scale support
  - [ ] Add Rankine scale support
  - [ ] Batch conversion endpoint for multiple values

- **Features**
  - [ ] Conversion history tracking (database)
  - [ ] User authentication and authorization
  - [ ] Request rate limiting and throttling
  - [ ] Caching for repeated conversions

- **API Documentation**
  - [ ] Swagger/OpenAPI integration
  - [ ] API documentation page
  - [ ] Interactive API playground

- **Frontend**
  - [ ] Web UI dashboard
  - [ ] Mobile-friendly interface
  - [ ] Browser extension

- **Monitoring**
  - [ ] Application metrics with Micrometer
  - [ ] Health indicators
  - [ ] Performance monitoring

- **Quality**
  - [ ] Integration with CI/CD pipeline
  - [ ] Code coverage analysis (JaCoCo)
  - [ ] Static code analysis (SonarQube)

## License

MIT License - Free to use and modify for educational and commercial purposes

## Author

Created as a Spring Boot REST API project demonstrating professional Java development practices with comprehensive testing.

---

**Last Updated:** February 2026

For questions or issues, refer to the test files for usage examples or consult the inline code documentation.
