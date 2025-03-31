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
        inventoryEntities.add(new InventoryEntity(id, itemId, stock));
    }

    // Finds an InventoryEntity from the in-memory list by itemId
    // itemId로 메모리 리스트에서 InventoryEntity를 조회함
    @Override
    public @NotNull Optional<InventoryEntity> findByItemId(@NotNull String itemId) {
        return inventoryEntities.stream()
                .filter(entity -> entity.getItemId().equals(itemId))
                .findFirst();
    }

    // Stub method that returns 0 for stock decrease (not implemented)
    // 재고 차감 처리용 스텁 메서드이며, 아직 구현되지 않아 0을 반환함
    @Override
    public @NotNull Integer decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        return 0;
    }

    // Stub method that returns null for save operation (not implemented)
    // 저장 처리용 스텁 메서드이며, 아직 구현되지 않아 null을 반환함
    @Override
    public @NotNull InventoryEntity save(@NotNull InventoryEntity entity) {
        return null;
    }
}
