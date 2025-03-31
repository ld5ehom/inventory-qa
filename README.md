# Inventory System Test-Driven Development

##  Project Overview
- Backend: Java, Spring Boot, Spring Data JPA, Spring Data Redis, Spring Cloud Stream
- Testing: JUnit 5, Mockito, Spring Boot Test, WebMvcTest, DataJpaTest, Test Binder
- Performance & Quality: JMeter, k6, Jacoco, SonarQube
- CI/CD & Tools: GitHub Actions

-----
## ## Reference Site
- hexagonal-architecture: https://alistair.cockburn.us/hexagonal-architecture/
- junit: https://junit.org/junit5/
- assertj: https://assertj.github.io/doc/
- mockito: https://github.com/mockito/mockito

-----
## Tasks
### Task 1: Service Layer Implementation with Unit Tests
- **Issues** : [task-1-service](https://github.com/ld5ehom/inventory-qa/tree/task-1-service)
- **Details** :
  - **Project Structure Setup for Service Unit Testing** : [b44de3b](https://github.com/ld5ehom/inventory-qa/commit/b44de3bc604020350cb9dd00f2b48b8967d3fce5)
    - Created the tests/inventory module and added build.gradle.kts to configure a dedicated testing environment along with a separated main and test directory structure 
    - Structured the root package inventoryapp with sub-packages: inventory, common, and config to organize domain logic, shared utilities, and configuration files by purpose 
    - Created the InventoryServiceTest class under inventory/test/inventory/service to provide the foundation for writing unit tests for InventoryService 
    - Used @Nested classes within InventoryServiceTest to group tests by feature, and organized each group with separate success and failure test cases 
    - Implemented the FindByItemId test group to verify item lookup behavior by ID, ensuring that it returns null when not found and returns an Inventory object when found 
    - Implemented the DecreaseByItemId test group to validate various failure scenarios such as negative quantity, stock overflow, missing target entity, and update failures, as well as successful stock deduction 
  - **Update FindByItemId test to include actual logic and assertions** :
    - Created the InventoryJpaRepositoryStub class to simulate an in-memory repository, enabling unit tests for InventoryService without external dependencies 
    - Implemented the addInventoryEntity() method to preload test data before each test execution 
    - Injected the stub repository into InventoryService to construct a fully isolated test environment 
    - Refactored the FindByItemId test group by moving shared test data (itemId, stock) to class-level fields and inserting data via @BeforeEach 
    - Replaced placeholder test methods that only threw exceptions with actual logic that calls sut.findByItemId() and verifies results using assertNull, assertNotNull, and assertEquals 
    - Covered both failure (returns null when the item does not exist) and success (returns Inventory when the item exists) scenarios 
    - Improved test clarity and stability by ensuring the test logic reflects actual service behavior
  - **Define core inventory components to support testability** :
    - Defined core classes such as InventoryEntity, Inventory, and InventoryService to encapsulate essential domain logic and support unit testing 
    - Declared the InventoryJpaRepository interface with findByItemId, decreaseStock, and save methods to separate data access responsibilities 
    - Created the InventoryJpaRepositoryStub class as an in-memory implementation to support testing without relying on a real database 
    - Structured InventoryServiceTest with clearly defined sut, organized test groups using @Nested, and consistent input and verification for each test scenario 
    - Designed the service layer, repository layer, and domain models with clear separation and interconnection to ensure the overall inventory structure is testable and maintainable

### Task 2: Controller Layer Testing using WebMvcTest

### Task 3: Repository Validation with DataJpaTest

### Task 4: Integration Testing with SpringBootTest

### Task 5: End-to-End Testing Based on User Scenarios

### Task 6: Architecture Testing and Structural Refactoring

### Task 7: Performance Testing and Redis Caching

### Task 8: Performance Verification using k6

### Task 9: Event Messaging System and Test Configuration

### Task 10: Continuous Integration and Code Quality Management

