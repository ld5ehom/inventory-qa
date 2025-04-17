package com.ld5ehom.inventoryapp.inventory.repository;

import com.ld5ehom.inventoryapp.inventory.repository.entity.InventoryEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

// Stub implementation of InventoryJpaRepository for testing purposes
// 테스트용으로 구현된 InventoryJpaRepository의 Stub 클래스
public class InventoryJpaRepositoryStub implements InventoryJpaRepository {

    private final List<InventoryEntity> inventoryEntities = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Adds a new InventoryEntity to the in-memory list with a generated ID
    // ID를 자동 생성하여 메모리 리스트에 InventoryEntity를 추가함
    public void addInventoryEntity(@NotNull String itemId, @NotNull Long stock) {
        final Long id = idGenerator.getAndIncrement();
        final InventoryEntity inventoryEntity = new InventoryEntity(id, itemId, stock);
        inventoryEntities.add(inventoryEntity);
    }

    // Finds an InventoryEntity from the in-memory list by itemId
    // itemId로 메모리 리스트에서 InventoryEntity를 조회함
    @Override
    public @NotNull Optional<InventoryEntity> findByItemId(@NotNull String itemId) {
        return internalFindByItemId(itemId);
    }

    // Decreases the stock of the InventoryEntity with the given itemId
    // 주어진 itemId를 가진 InventoryEntity의 재고를 차감함
    @Override
    public @NotNull Integer decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        final Optional<InventoryEntity> optionalEntity = internalFindByItemId(itemId);

        // No matching entity found; return 0
        // 해당 entity를 찾지 못한 경우 0 반환
        if (optionalEntity.isEmpty()) {
            return 0;
        }

        final InventoryEntity entity = optionalEntity.get();
        final Long newStock = entity.getStock() - quantity;

        // Updates the stock value   재고 수치를 갱신함
        entity.setStock(newStock);

        // Return 1 to indicate successful update
        // 정상적으로 차감되었음을 나타내기 위해 1 반환
        return 1;
    }

    // Saves the InventoryEntity to the list, updating if it already exists
    // InventoryEntity를 리스트에 저장하며, 이미 존재할 경우 갱신함
    @Override
    public @NotNull InventoryEntity save(@NotNull InventoryEntity inventoryEntity) {
        final Long entityId = inventoryEntity.getId();
        final Optional<InventoryEntity> optionalEntity = inventoryEntities.stream()
                .filter(entity -> entity.getId() != null && entity.getId().equals(entityId))
                .findFirst();

        InventoryEntity entity;

        if (optionalEntity.isPresent()) {
            // If found, update stock
            // 이미 존재하는 경우 재고만 갱신함
            entity = optionalEntity.get();
            entity.setStock(inventoryEntity.getStock());
        } else {
            // If not found, add as new entry with generated ID
            // 존재하지 않으면 ID를 생성하여 새로 추가함
            final Long id = idGenerator.getAndIncrement();
            entity = new InventoryEntity(id, inventoryEntity.getItemId(), inventoryEntity.getStock());
            inventoryEntities.add(entity);
        }

        // Return the saved or updated entity
        // 저장되거나 갱신된 entity를 반환함
        return entity;
    }

    // Internal utility to find InventoryEntity by itemId
    // itemId로 InventoryEntity를 찾는 내부 유틸리티 메서드
    private Optional<InventoryEntity> internalFindByItemId(String itemId) {
        return inventoryEntities.stream()
                .filter(entity -> entity.getItemId().equals(itemId))
                .findFirst();
    }
}
