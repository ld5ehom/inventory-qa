package com.ld5ehom.inventoryapp.inventory.service;

import com.ld5ehom.inventoryapp.inventory.repository.InventoryJpaRepository;
import com.ld5ehom.inventoryapp.inventory.repository.entity.InventoryEntity;
import com.ld5ehom.inventoryapp.inventory.service.domain.Inventory;
import com.ld5ehom.inventoryapp.inventory.service.exception.InsufficientStockException;
import com.ld5ehom.inventoryapp.inventory.service.exception.InvalidDecreaseQuantityException;
import com.ld5ehom.inventoryapp.inventory.service.exception.InvalidStockException;
import com.ld5ehom.inventoryapp.inventory.service.exception.ItemNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Service class for handling inventory-related business logic
// 인벤토리 관련 비즈니스 로직을 처리하는 서비스 클래스
public class InventoryService {

    private final InventoryJpaRepository inventoryJpaRepository;

    // Constructor that injects the InventoryJpaRepository dependency
    // InventoryJpaRepository 의존성을 주입받는 생성자
    public InventoryService(InventoryJpaRepository inventoryJpaRepository) {
        this.inventoryJpaRepository = inventoryJpaRepository;
    }

    // Finds inventory by itemId and maps it to the domain model
    // itemId로 재고를 조회하고 도메인 모델로 매핑함
    public @Nullable Inventory findByItemId(@NotNull String itemId) {
        return inventoryJpaRepository.findByItemId(itemId)
                .map(this::mapToDomain)
                .orElse(null);
    }

    public @NotNull Inventory decreaseByItemId(@NotNull String itemId, @NotNull Long quantity) {
        // Throws exception if the decrease quantity is negative
        // 감소 수량이 음수이면 예외를 발생시킴
        if (quantity < 0) {
            throw new InvalidDecreaseQuantityException();
        }

        // Finds the inventory entity by itemId or throws if not found
        // itemId로 InventoryEntity를 조회하거나, 없으면 예외를 발생시킴
        final InventoryEntity inventoryEntity = inventoryJpaRepository.findByItemId(itemId)
                .orElseThrow(ItemNotFoundException::new);

        // Throws exception if requested quantity exceeds available stock
        // 요청한 수량이 보유 재고보다 많으면 예외를 발생시킴
        if (inventoryEntity.getStock() < quantity) {
            throw new InsufficientStockException();
        }

        // Attempts to decrease stock; throws if no entity was updated
        // 재고를 차감 시도하며, 변경된 엔티티가 없으면 예외를 발생시킴
        final Integer updateCount = inventoryJpaRepository.decreaseStock(itemId, quantity);
        if (updateCount == 0) {
            throw new ItemNotFoundException();
        }

        // Retrieves the updated entity to reflect the latest stock
        // 변경된 재고 상태를 반영하기 위해 다시 엔티티를 조회함
        final InventoryEntity updatedEntity = inventoryJpaRepository.findByItemId(itemId)
                .orElseThrow(ItemNotFoundException::new);

        // Converts and returns domain Inventory object
        // 도메인 Inventory 객체로 변환하여 반환함
        return mapToDomain(updatedEntity);
    }

    public @NotNull Inventory updateStock(@NotNull String itemId, @NotNull Long stock) {
        // Throws exception if new stock is negative
        // 수정하려는 재고가 음수이면 예외를 발생시킴
        if (stock < 0) {
            throw new InvalidStockException();
        }

        // Finds the entity by itemId or throws if not found
        // itemId로 엔티티를 조회하거나, 없으면 예외를 발생시킴
        final InventoryEntity entity = inventoryJpaRepository.findByItemId(itemId)
                .orElseThrow(ItemNotFoundException::new);

        // Updates the stock value of the entity
        // 조회된 엔티티의 재고 값을 수정함
        entity.setStock(stock);

        // Saves the updated entity and returns as a domain object
        // 수정된 엔티티를 저장한 후 도메인 객체로 변환하여 반환함
        return mapToDomain(inventoryJpaRepository.save(entity));
    }


    // Converts an InventoryEntity to a domain Inventory object
    // InventoryEntity를 도메인 Inventory 객체로 변환함
    private Inventory mapToDomain(InventoryEntity entity) {
        return new Inventory(entity.getItemId(), entity.getStock());
    }
}
