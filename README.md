# Smart Wealth Manager (Java 21 Edition)

A Clean Architecture financial portfolio application designed to demonstrate high-level competency in Modern Java (21+).

This project serves as a technical bridge for developers transitioning from C#/.NET to the Java Ecosystem, highlighting idiomatic differences in concurrency, data modeling, and stream processing.

## Architecture Overview

The application follows a modular Package-by-Feature architecture to ensure strict separation of concerns:

- **model**: Immutable Data Carriers using Java Records.
- **service**: Business logic implementations using the Streams API.
- **repository**: Data access layer using Java NIO and Jackson.
- **network**: External API integration via Asynchronous HTTP.

## Key Technical Pillars

### 1. Object Modeling (Immutability)
- **Feature**: Java 21 record types.
- **Rationale**: Ensures thread safety and eliminates boilerplate code for constructors, accessors, and equality contracts.
- **Implementation**: The Transaction record utilizes BigDecimal for monetary precision, avoiding the pitfalls of floating-point arithmetic.

### 2. Functional Logic (Streams)
- **Feature**: java.util.stream API.
- **Rationale**: Provides declarative processing of data collections, improving readability and maintainability.
- **Implementation**: Implements filtering, sorting, and complex reduction (balance calculation) without using mutable state or manual iteration loops.

### 3. Asynchronous Networking (Non-blocking)
- **Feature**: java.net.http.HttpClient and CompletableFuture.
- **Rationale**: Facilitates high-performance I/O by preventing thread blocking during external network calls.
- **Implementation**: Fetches live market data in parallel with local file I/O operations.

### 4. Modern Persistence (NIO)
- **Feature**: java.nio.file (NIO.2) and Jackson Databind.
- **Rationale**: Utilizes modern path handling and efficient, non-blocking string I/O for data serialization.
- **Implementation**: Handles JSON serialization with custom modules to support Java 8+ Temporal types.

---

## Technical Translations (For C# Developers)

| Concept | C# Equivalent | Java Implementation |
| :--- | :--- | :--- |
| Dependency Management | .csproj / NuGet | pom.xml (Maven) |
| Data Types | record / record struct | record (Java 14+) |
| Async Flow | async / await / Task | CompletableFuture / thenApply() |
| Collections | LINQ (Where, Select) | Stream API (filter, map) |
| File I/O | File.ReadAllText | Files.readString (NIO) |
| Precision Math | decimal | BigDecimal |

---

## Getting Started

### Prerequisites
- Java SDK 21 or higher.
- Maven 3.8 or higher.

### Build & Run
1. Compile and install dependencies:
   ```bash
   mvn clean install
