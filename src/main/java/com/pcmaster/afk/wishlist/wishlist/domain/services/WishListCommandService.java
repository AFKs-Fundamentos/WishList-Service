package com.pcmaster.afk.wishlist.wishlist.domain.services;

import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.CreateWishListCommand;
import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.DeleteProductOfWishListCommand;

public interface WishListCommandService {
    Long handle (CreateWishListCommand command);

    void handle(DeleteProductOfWishListCommand command);
}
