package com.ld5ehom.inventoryapp.inventory.service;

import com.ld5ehom.inventoryapp.inventory.repository.InventoryJpaRepositoryStub;
import com.ld5ehom.inventoryapp.inventory.service.domain.Inventory;
import com.ld5ehom.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


// Nested는 별도의 테스트 환경을 만들어 준다. 독립된 공간을 만들어서 테스트함
public class InventoryServiceTest {
    InventoryService sut; // system under test

    InventoryJpaRepositoryStub inventoryJpaRepository;

    // BeforeEach 는 모든 테스트에 영향을 준다.
    // Nested 안에 있으면 해당 Nested에만 영향을 준다.
    @BeforeEach
    void setUpAll() {
        inventoryJpaRepository = new InventoryJpaRepositoryStub();
        sut = new InventoryService(inventoryJpaRepository);
    }

    // 상품의 현재 재고 확인 테스트 케이스 구현
    // 나열 조건은 코드상 위에서 부터 먼저 실패를 확인하는 케이스부터 작성함
    @Nested
    class FindByItemId { // 상품 item ID 로 조회하는 클래스

        // Declared common itemId and stock values for reuse in tests
        // 테스트 전용 itemId 및 stock 값을 필드로 상단에 선언하여 재사용
        final String existingItemId = "1";
        final Long stock = 10L;

        // Inserts inventory data into the stub repository before each test in this group
        // 각 테스트 실행 전에 Stub에 inventory 데이터를 미리 추가함
        @BeforeEach
        void setUp() {
            inventoryJpaRepository.addInventoryEntity(existingItemId, stock);
        }

        @DisplayName("Returns null if the entity with the given itemId is not found")
        @Test
        void test1() {
            final String nonExistingItemId = "2";
            final Inventory result = sut.findByItemId(nonExistingItemId);
            assertNull(result);
        }

        @DisplayName("Returns inventory if the entity with the given itemId is found")
        @Test
        void test1000() {

            // Reused shared itemId and stock defined at the class level instead of redefining
            // 기존에 재정의하던 itemId, stock 값을 상단 필드 값으로 대체함
            final Inventory result = sut.findByItemId(existingItemId);

            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(stock, result.getStock());
        }
    }

    // 재고 차감 test
    @Nested
    class DecreaseByItemId {
        // quantity가 음수라면, Exception을 throw한다.
        @DisplayName("Throws an exception if the quantity is negative")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        // itemId를 갖는 entity를 찾지 못하면, Exception을 throw한다
        @DisplayName("Throws an exception if the entity with the given itemId is not found")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }

        // quantity가 stock 보다 크면, Exception을 throw한다.
        @DisplayName("Throws an exception if the quantity exceeds the current stock")
        @Test
        void test3() {
            throw new NotImplementedTestException();
        }

        // 변경된 entity가 없다면, Exception을 throw한다.
        @DisplayName("Throws an exception if no entity was updated")
        @Test
        void test4() {
            throw new NotImplementedTestException();
        }

        // 성공 케이스
        // itemId를 갖는 entity를 찾으면, stock을 빼고 inventory를 반환한다.
        @DisplayName("Returns inventory after subtracting stock if the entity with the given itemId is found")
        @Test
        void test1000() {
            throw new NotImplementedTestException();
        }

    }

    // 재고 수정
    @Nested
    class UpdateStock {
        // 수정할 stock이 유효하지 않는다면, Exception을 throw한다.
        @DisplayName("Throws an exception if the new stock value is invalid")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        // itemId를 갖는 entity를 찾지 못하면, Exception을 throw한다.
        @DisplayName("Throws an exception if the entity with the given itemId is not found")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }

        // 성공 케이스
        // itemId를 갖는 entity를 찾으면, stock을 수정하고 inventory를 반환한다.
        @DisplayName("Returns inventory after updating stock if the entity with the given itemId is found")
        @Test
        void test1000() {
            throw new NotImplementedTestException();
        }
    }
}
