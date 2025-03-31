package com.ld5ehom.inventoryapp.inventory.repository;

import com.ld5ehom.inventoryapp.inventory.repository.entity.InventoryEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

// Interface for performing database operations related to inventory
// 인벤토리 관련 데이터베이스 작업을 처리하는 인터페이스
public interface InventoryJpaRepository {

    // Finds an inventory entity by its itemId
    // itemId로 InventoryEntity를 조회함
    @NotNull Optional<InventoryEntity> findByItemId(@NotNull String itemId);

    // Decreases the stock of a specific item by the given quantity
    // 지정된 itemId의 재고를 주어진 quantity만큼 차감함
    @NotNull Integer decreaseStock(@NotNull String itemId, @NotNull Long quantity);

    // Saves the given InventoryEntity to the database
    // InventoryEntity 객체를 데이터베이스에 저장함
    @NotNull InventoryEntity save(@NotNull InventoryEntity entity);
}
