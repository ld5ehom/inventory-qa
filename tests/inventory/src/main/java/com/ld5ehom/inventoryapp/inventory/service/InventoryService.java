package com.ld5ehom.inventoryapp.inventory.service;

import com.ld5ehom.inventoryapp.inventory.repository.InventoryJpaRepository;
import com.ld5ehom.inventoryapp.inventory.repository.entity.InventoryEntity;
import com.ld5ehom.inventoryapp.inventory.service.domain.Inventory;
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

    // Converts an InventoryEntity to a domain Inventory object
    // InventoryEntity를 도메인 Inventory 객체로 변환함
    private Inventory mapToDomain(InventoryEntity entity) {
        return new Inventory(entity.getItemId(), entity.getStock());
    }
}
