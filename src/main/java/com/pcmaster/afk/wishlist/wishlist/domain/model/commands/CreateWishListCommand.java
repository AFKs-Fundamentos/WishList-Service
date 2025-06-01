package com.pcmaster.afk.wishlist.wishlist.domain.model.commands;

public record CreateWishListCommand(
        Long userId,
        Long productId
) {
}
