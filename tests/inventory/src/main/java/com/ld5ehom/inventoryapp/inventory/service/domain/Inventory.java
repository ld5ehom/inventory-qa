package com.ld5ehom.inventoryapp.inventory.service.domain;

import org.jetbrains.annotations.NotNull;

// Domain model representing inventory information
// 인벤토리 정보를 나타내는 도메인 모델 클래스
public class Inventory {

    private @NotNull String itemId;
    private @NotNull Long stock;

    // Constructor to initialize itemId and stock
    // itemId와 stock 값을 초기화하는 생성자
    public Inventory(@NotNull String itemId, @NotNull Long stock) {
        this.itemId = itemId;
        this.stock = stock;
    }

    // Returns the itemId of the inventory
    // 인벤토리의 itemId 값을 반환함
    public @NotNull String getItemId() {
        return itemId;
    }

    // Returns the stock quantity of the inventory
    // 인벤토리의 재고 수량을 반환함
    public @NotNull Long getStock() {
        return stock;
    }
}
