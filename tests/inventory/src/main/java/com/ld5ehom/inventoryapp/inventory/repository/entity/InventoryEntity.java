package com.ld5ehom.inventoryapp.inventory.repository.entity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Represents an inventory item with itemId and stock count
// itemId와 재고 수량(stock)을 포함하는 재고 엔티티 클래스
public class InventoryEntity {

    private @Nullable Long id;
    private @NotNull String itemId;
    private @NotNull Long stock;

    // Constructor to create an InventoryEntity with id, itemId, and stock
    // id, itemId, stock 값을 받아 InventoryEntity 객체를 생성하는 생성자
    public InventoryEntity(@Nullable Long id, @NotNull String itemId, @NotNull Long stock) {
        this.id = id;
        this.itemId = itemId;
        this.stock = stock;
    }

    // Returns the unique identifier of the inventory entity
    // 재고 엔티티의 고유 식별자(id)를 반환함
    public Long getId() {
        return id;
    }

    // Returns the itemId associated with the inventory
    // 재고와 연결된 itemId 값을 반환함
    public @NotNull String getItemId() {
        return itemId;
    }

    // Returns the current stock quantity
    // 현재 재고 수량을 반환함
    public @NotNull Long getStock() {
        return stock;
    }

    // Updates the stock quantity to the given value
    // 재고 수량을 전달받은 값으로 수정함
    public void setStock(@NotNull Long stock) {
        this.stock = stock;
    }
}
