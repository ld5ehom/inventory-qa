package com.ld5ehom.inventoryapp.inventory.service;

import com.ld5ehom.inventoryapp.inventory.repository.InventoryJpaRepositoryStub;
import com.ld5ehom.inventoryapp.inventory.service.domain.Inventory;
import com.ld5ehom.inventoryapp.inventory.service.exception.InsufficientStockException;
import com.ld5ehom.inventoryapp.inventory.service.exception.InvalidDecreaseQuantityException;
import com.ld5ehom.inventoryapp.inventory.service.exception.InvalidStockException;
import com.ld5ehom.inventoryapp.inventory.service.exception.ItemNotFoundException;
import com.ld5ehom.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    @InjectMocks
    InventoryService sut; // system under test

    @Spy
    InventoryJpaRepositoryStub inventoryJpaRepository;

    // 상품의 현재 재고 확인 테스트 케이스 구현
    // 나열 조건은 코드상 위에서 부터 먼저 실패를 확인하는 케이스부터 작성함
    @Nested
    class FindByItemId {

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
            // Attempts to find nonexistent itemId
            // 존재하지 않는 itemId 조회 시도
            final Inventory result = sut.findByItemId(nonExistingItemId);
            assertNull(result);
        }

        @DisplayName("Returns inventory if the entity with the given itemId is found")
        @Test
        void test1000() {
            final Inventory result = sut.findByItemId(existingItemId);
            // Checks that found inventory matches expected values
            // 조회된 inventory의 값이 기대값과 일치하는지 확인
            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(stock, result.getStock());
        }
    }

    // 재고 차감 test
    @Nested
    class DecreaseByItemId {
        final String existingItemId = "1";
        final Long stock = 10L;

        @BeforeEach
        void setUp() {
            inventoryJpaRepository.addInventoryEntity(existingItemId, stock);
        }

        @DisplayName("Throws an exception if the quantity is negative")
        @Test
        void test1() {
            final Long quantity = -1L;
            // Throws exception if quantity is negative
            // 수량이 음수일 경우 예외 발생
            assertThrows(InvalidDecreaseQuantityException.class, () -> {
                sut.decreaseByItemId(existingItemId, quantity);
            });
        }

        @DisplayName("Throws an exception if the entity with the given itemId is not found")
        @Test
        void test2() {
            final String nonExistingItemId = "2";
            final Long quantity = 10L;
            // Throws exception if itemId does not exist
            // itemId가 존재하지 않을 경우 예외 발생
            assertThrows(ItemNotFoundException.class, () -> {
                sut.decreaseByItemId(nonExistingItemId, quantity);
            });
        }

        @DisplayName("Throws an exception if the quantity exceeds the current stock")
        @Test
        void test3() {
            final Long quantity = stock + 1;
            // Throws exception if quantity exceeds available stock
            // 요청 수량이 재고보다 많을 경우 예외 발생
            assertThrows(InsufficientStockException.class, () -> {
                sut.decreaseByItemId(existingItemId, quantity);
            });
        }

        @DisplayName("Throws an exception if no entity was updated")
        @Test
        void test4() {
            final Long quantity = 10L;

            // Forces decreaseStock to return 0 using stubbing
            // decreaseStock이 0을 반환하도록 강제로 설정
            doReturn(0).when(inventoryJpaRepository).decreaseStock(existingItemId, quantity);

            // Throws exception because update count is 0
            // 업데이트된 엔티티가 없으므로 예외 발생
            assertThrows(ItemNotFoundException.class, () -> {
                sut.decreaseByItemId(existingItemId, quantity);
            });

            // Verifies that decreaseStock was called with correct arguments
            // decreaseStock이 올바르게 호출되었는지 확인
            verify(inventoryJpaRepository).decreaseStock(existingItemId, quantity);
        }

        @DisplayName("Returns inventory after subtracting stock if the entity with the given itemId is found")
        @Test
        void test1000() {
            final Long quantity = 10L;
            final Inventory result = sut.decreaseByItemId(existingItemId, quantity);
            // Validates updated inventory after stock decrease
            // 재고 차감 후 업데이트된 inventory 검증
            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(stock - quantity, result.getStock());
        }
    }

    // 재고 수정
    @Nested
    class UpdateStock {
        final String existingItemId = "1";
        final Long stock = 10L;

        @BeforeEach
        void setUp() {
            inventoryJpaRepository.addInventoryEntity(existingItemId, stock);
        }

        @DisplayName("Throws an exception if the new stock value is invalid")
        @Test
        void test1() {
            final Long stock = -1L;
            // Throws exception if new stock is negative
            // 새로운 재고가 음수일 경우 예외 발생
            assertThrows(InvalidStockException.class, () -> {
                sut.updateStock(existingItemId, stock);
            });
        }

        @DisplayName("Throws an exception if the entity with the given itemId is not found")
        @Test
        void test2() {
            final String nonExistingItemId = "2";
            final Long stock = 10L;
            // Throws exception if itemId does not exist
            // 존재하지 않는 itemId일 경우 예외 발생
            assertThrows(ItemNotFoundException.class, () -> {
                sut.updateStock(nonExistingItemId, stock);
            });
        }

        @DisplayName("Returns inventory after updating stock if the entity with the given itemId is found")
        @Test
        void test1000() {
            final Long newStock = 20L;
            final Inventory result = sut.updateStock(existingItemId, newStock);
            // Checks that inventory was updated with new stock value
            // 새로운 재고 값으로 inventory가 업데이트되었는지 확인
            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(newStock, result.getStock());
        }
    }
}

