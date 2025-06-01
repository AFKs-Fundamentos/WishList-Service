package com.pcmaster.afk.wishlist.wishlist.interfaces.rest.transform;

import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.CreateWishListCommand;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources.CreateWishListResource;

public class CreateWishListCommandFromResourceAssembler {
    public static CreateWishListCommand toCommandFromResource(CreateWishListResource resource){
        return new CreateWishListCommand(
                resource.userId(),
                resource.productId()
        );
    }
}
