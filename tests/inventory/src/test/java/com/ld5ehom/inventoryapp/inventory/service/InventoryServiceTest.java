package com.ld5ehom.inventoryapp.inventory.service;

import com.ld5ehom.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

// Nested는 별도의 테스트 환경을 만들어 준다. 독립된 공간을 만들어서 테스트함
public class InventoryServiceTest {
    // 상품의 현재 재고 확인 테스트 케이스 구현
    // 나열 조건은 코드상 위에서 부터 먼저 실패를 확인하는 케이스부터 작성함
    @Nested
    class FindByItemId { // 상품 item ID 로 조회하는 클래스

        // BeforeEach 는 모든 테스트에 영향을 준다.
        // Nested 안에 있으면 해당 Nested에만 영향을 준다.
        @BeforeEach
        void setUpAll() {

        }

        // 실패 케이스
        // itemID를 갖는 entity를 찾지 못하면, Null을 리턴한다.
        @DisplayName("Returns null if the entity with the given itemId is not found")
        @Test
        void test1() {
            // 테스트가 구현되지 않은 경우 exception을 throw함
            throw new NotImplementedTestException();
        }

        // 성공 케이스
        // itemId를 갖는 entity를 찾으면, inventory를 리턴한다
        @DisplayName("Returns inventory if the entity with the given itemId is found")
        @Test
        void test1000() {
            // 테스트가 구현되지 않은 경우 exception을 throw함
            throw new NotImplementedTestException();
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
