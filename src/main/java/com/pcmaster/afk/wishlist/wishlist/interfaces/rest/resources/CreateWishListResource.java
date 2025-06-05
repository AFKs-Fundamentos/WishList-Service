package com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources;

public record CreateWishListResource(
        Long userId,
        Long productId
) {
}
