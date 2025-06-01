package com.pcmaster.afk.wishlist.wishlist.domain.services;

import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.CreateWishListCommand;

public interface WishListCommandService {
    Long handle (CreateWishListCommand command);
}
