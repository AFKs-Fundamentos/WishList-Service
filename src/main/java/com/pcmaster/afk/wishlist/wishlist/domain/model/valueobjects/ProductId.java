package com.pcmaster.afk.wishlist.wishlist.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProductId(Long productId) {
    public ProductId {
        if (productId == null || productId < 0){
            throw new IllegalArgumentException("UserId cannot be null");
        }
    }
}
