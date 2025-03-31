- **task-1-service** :
    - **Project Structure Setup for Service Unit Testing** [b44de3b](https://github.com/ld5ehom/inventory-qa/commit/b44de3bc604020350cb9dd00f2b48b8967d3fce5)
    - **Service 단위 테스트를 위한 프로젝트 구조 세팅** 
        - tests/inventory 모듈을 생성하고 build.gradle.kts를 추가하여, 테스트 전용 환경과 main, test 디렉토리 구조를 함께 구성함
        - inventoryapp 루트 패키지 아래에 inventory, common, config 패키지를 생성하여, 도메인 로직과 공통 유틸, 설정 파일을 목적에 따라 분리함
        - inventory/test/inventory/service 경로에 InventoryServiceTest 클래스를 생성하여, InventoryService의 단위 테스트 작성을 위한 기본 뼈대를 마련함
        - InventoryServiceTest 클래스를 생성하고, @Nested 클래스를 활용해 기능별로 테스트를 그룹화하며 실패 및 성공 케이스를 구분한 기본 테스트 구조를 설정함
        - FindByItemId 테스트 그룹을 작성하여, 상품 ID 기반 조회 시 실패 시 null 반환, 성공 시 Inventory 반환 여부를 검증할 수 있도록 테스트 뼈대를 구성함
        - DecreaseByItemId 테스트 그룹을 작성하여, 음수 입력, 재고 초과, 대상 미존재 등 예외 케이스와 정상 차감 처리까지 검증할 수 있도록 테스트 구조를 구성함
        - UpdateStock 테스트 그룹을 작성하여, 유효하지 않은 입력과 대상 미존재에 대한 예외 처리 및 정상 재고 수정 여부를 검증할 수 있도록 테스트 뼈대를 구성함
      - **Update FindByItemId test to include actual logic and assertions** :
          - InventoryJpaRepositoryStub 클래스를 생성하여 인메모리 저장소를 시뮬레이션하고, InventoryService의 단위 테스트를 외부 의존 없이 수행할 수 있도록 구성함 
          - addInventoryEntity() 메서드를 구현하여 테스트 실행 전 필요한 데이터를 사전에 주입할 수 있게 함 
          - InventoryService에 Stub 저장소를 주입하여 실제 서비스 로직과 독립된 테스트 환경을 구성함 
          - FindByItemId 테스트 그룹 내에서 공통 테스트 데이터(itemId, stock)를 클래스 필드로 선언하고, @BeforeEach에서 미리 데이터를 삽입하는 방식으로 구조 개선 
          - 기존에 예외만 던지던 테스트 메서드를 실제 로직을 호출하도록 변경하고, assertNull, assertNotNull, assertEquals 등으로 기대 결과를 검증함 
          - 조회 실패 시 null 반환, 조회 성공 시 Inventory 반환을 검증하는 단위 테스트 로직을 완성하여 실제 동작 기반 테스트로 전환함 
          - 테스트 구조와 로직 모두 명확히 구성하여, 안정성과 가독성을 개선함
      - **Define core inventory components to support testability** :
          - InventoryEntity, Inventory, InventoryService 등의 핵심 클래스들을 정의하고, 각 클래스가 도메인 설계 및 테스트 수행에 필요한 정보를 명확히 담도록 구성함  InventoryJpaRepository 인터페이스를 통해 findByItemId, decreaseStock, save 메서드를 선언하여 데이터 접근 계층의 역할을 분리함 
          - InventoryJpaRepositoryStub 클래스를 생성하여 테스트 전용 인메모리 저장소를 구현하고, 실제 DB 없이도 독립적으로 테스트를 수행할 수 있도록 설정함
          - InventoryServiceTest 내부에서 sut 구성, @Nested 테스트 그룹 분리, 테스트 대상에 맞는 입력 및 검증 방식을 통일하여 테스트 기반 구조를 설계함 
          - 서비스 레이어, 저장소 레이어, 도메인 모델 전반을 테스트 가능하도록 나누고 연결되도록 설계함으로써 전체 인벤토리 구조를 테스트 친화적으로 구성함



