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
  - **Project Structure Setup for Service Unit Testing**
    - Created the tests/inventory module and added build.gradle.kts to configure a dedicated testing environment along with a separated main and test directory structure 
    - Structured the root package inventoryapp with sub-packages: inventory, common, and config to organize domain logic, shared utilities, and configuration files by purpose 
    - Created the InventoryServiceTest class under inventory/test/inventory/service to provide the foundation for writing unit tests for InventoryService 
    - Used @Nested classes within InventoryServiceTest to group tests by feature, and organized each group with separate success and failure test cases 
    - Implemented the FindByItemId test group to verify item lookup behavior by ID, ensuring that it returns null when not found and returns an Inventory object when found 
    - Implemented the DecreaseByItemId test group to validate various failure scenarios such as negative quantity, stock overflow, missing target entity, and update failures, as well as successful stock deduction 

### Task 2: Controller Layer Testing using WebMvcTest

### Task 3: Repository Validation with DataJpaTest

### Task 4: Integration Testing with SpringBootTest

### Task 5: End-to-End Testing Based on User Scenarios

### Task 6: Architecture Testing and Structural Refactoring

### Task 7: Performance Testing and Redis Caching

### Task 8: Performance Verification using k6

### Task 9: Event Messaging System and Test Configuration

### Task 10: Continuous Integration and Code Quality Management

